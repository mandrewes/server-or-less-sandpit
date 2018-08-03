package com.yolo.example.serverorless.services;

import com.yolo.example.serverorless.model.CollectionStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CollectionStatsRecorder {
    private Map<String,Map<String,AtomicLong>> stats = new HashMap<>();
    int values = 0;

    public CollectionStatistics getStats() {
        CollectionStatistics ret = new CollectionStatistics();
        ret.setDistinctCategories(stats.size());

        int ctr = 0;

        for (Map<String, AtomicLong> stringAtomicLongMap : stats.values()) {
            ctr += stringAtomicLongMap.size();
        }
        ret.setTotalNonDistinctValues(ctr);
        ret.setAvgValuesPerCategory(ctr/stats.size());

        for (String key : stats.keySet()) {
            ret.getCategoryCounts().put(key,stats.get(key).size());
        }

        ret.getCategoryCounts().put("totalValues", ctr);
        return ret;
    }

    public void submit(String category, String val) {

        Map<String,AtomicLong> m = stats.get(category);
        if ( m == null ) {
            m = new HashMap<>();
            stats.put(category,m);
        }

        AtomicLong lv = m.get(val);
        if ( lv == null ) {
            lv = new AtomicLong(0);
            m.put(val,lv);
        }
        lv.incrementAndGet();
    }
}
