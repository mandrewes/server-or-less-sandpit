package com.yolo.example.serverorless.services;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.*;
import com.yolo.example.serverorless.model.DocDescriptor;
import com.yolo.example.serverorless.model.SearchRequest;
import com.yolo.example.serverorless.utils.AbstractDynamoBackedService;
import com.yolo.example.serverorless.utils.SearchToDocComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import tech.rsqn.useful.things.metrics.Metrics;

import javax.annotation.PostConstruct;
import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DocService extends AbstractDynamoBackedService {
    public static final String TMP_TABLE_NAME = "DocumentDescriptor";

    @PostConstruct
    public void init() {
        Metrics.startReporting();
        try {
            DescribeTableResult desc = dynamodb.describeTable(TMP_TABLE_NAME);
        } catch (ResourceNotFoundException rne) {
            CreateTableRequest req = mapper.generateCreateTableRequest(DocDescriptor.class);
            req.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));

            for (GlobalSecondaryIndex globalSecondaryIndex : req.getGlobalSecondaryIndexes()) {
                globalSecondaryIndex.setProvisionedThroughput(new ProvisionedThroughput(10L, 10L));
                Projection projection = new Projection();
                projection.setProjectionType(ProjectionType.ALL);
                globalSecondaryIndex.setProjection(projection);
            }

            dynamodb.createTable(req);
        }
    }

    public List<DocDescriptor> retrieveSomeDocs() {
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        ScanResultPage<DocDescriptor> l = mapper.scanPage(DocDescriptor.class, expression, mapperConfig);
        List<DocDescriptor> ret = l.getResults();
        return ret;
    }

    public DocDescriptor getDocDescriptor(String id) {
        DocDescriptor ret = mapper.load(DocDescriptor.class,id);
        return ret;
    }

    private void validateSearchRequest(SearchRequest req) {
        if (StringUtils.isBlank(req.getCustomerId())
                && StringUtils.isBlank(req.getGroupId())
                && StringUtils.isBlank(req.getPath())
                && StringUtils.isBlank(req.getFolder())
                ) {
            throw new RuntimeException("You must specify at least one indexed query field");
        }
    }


    public List<DocDescriptor> filterAndLimit( PaginatedQueryList<DocDescriptor> docs, SearchRequest req, int max ) {
        List<DocDescriptor> ret = new ArrayList<>();
        SearchToDocComparator comparator = new SearchToDocComparator();

        Iterator<DocDescriptor> it = docs.iterator();
        while ( it.hasNext() ) {
            DocDescriptor descriptor = it.next();
            if ( descriptor.getDocumentUrl() == null) {
                // assume index used is not projected
                // this will ramp up required read capacity
                descriptor = mapper.load(DocDescriptor.class,descriptor.getId());
            }
            if ( comparator.matchesQuery(descriptor,req)) {
                ret.add(descriptor);
            }

            if ( ret.size() == max) {
                break;
            }
        }
        return ret;
    }

    public List<DocDescriptor> searchDocuments(SearchRequest req) {
        // document high/medium/low category
        validateSearchRequest(req);

        DynamoDBQueryExpression<DocDescriptor> queryExpression = new DynamoDBQueryExpression<DocDescriptor>();
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();

        // use best filter - since we are not in SQL we need to have our own query plan

        if (StringUtils.isNotBlank(req.getGroupId())) {
            eav.put(":v1", new AttributeValue().withS(req.getGroupId()));
            queryExpression.withIndexName("GroupIndex");
            queryExpression.withKeyConditionExpression("groupId = :v1");
//            queryExpression.withKeyConditionExpression("PostedBy = :v1 and begins_with(Message, :v2)");
        } else if (StringUtils.isNotBlank(req.getFolder())) {
            eav.put(":v1", new AttributeValue().withS(req.getFolder()));
            queryExpression.withIndexName("FolderIndex");
            queryExpression.withKeyConditionExpression("folder = :v1");
        } else {
            throw new RuntimeException("Could not determine index to use");
        }

        queryExpression.withConsistentRead(false)
                .withExpressionAttributeValues(eav);

        PaginatedQueryList<DocDescriptor> docs = mapper.query(DocDescriptor.class, queryExpression, mapperConfig);


        List<DocDescriptor> ret = filterAndLimit(docs,req,1000);

        return ret;
    }
}
