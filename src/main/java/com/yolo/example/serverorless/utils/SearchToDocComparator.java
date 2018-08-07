package com.yolo.example.serverorless.utils;

import com.yolo.example.serverorless.model.DocDescriptor;
import com.yolo.example.serverorless.model.SearchRequest;

public class SearchToDocComparator {

    public boolean matchesQuery(DocDescriptor doc, SearchRequest srch) {

        // do an OR for freeform
        int ffScore = 0;
        if ( srch.getFreeForm() != null && srch.getFreeForm().size() > 0 ) {
            for (String k : srch.getFreeForm().keySet()) {
                if (doc.getFreeFormMetadata().containsKey(k)) {
                    String dV = doc.getFreeFormMetadata().get(k);
                    String sV = srch.getFreeForm().get(k);
                    if (dV.equals(sV)) {
                        ffScore++;
                    } else {
                        ffScore = 0;
                        break;
                    }
                }
            }
            if ( ffScore > 0) {
                return true;
            }
            return false;
        }

        // do an AND for indexed fields
        return true;
    }
}
