package com.john.digg.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by changjohn on 2017/4/26.
 */

public class TopicLruCacheTest {
    private static final int MAX_ITEM = 6;
    private TopicLruCache mCache = null;
    @Before
    public void setup() {
        mCache = new TopicLruCache(MAX_ITEM);
    }

    @Test
    public void testFixedItems() throws CreatedException {
        for(int idx = 0 ;idx < 10; idx++) {
            Topic topic = Topic.CREATOR.newInstance("john" + idx, "This is content" + idx);
            mCache.put(topic);
        }
        assertEquals(mCache.size(),MAX_ITEM);
    }

    @Test
    public void testInsertOrder() throws CreatedException {
        Topic[] topics = new Topic[MAX_ITEM];
        for(int idx = 0 ;idx < MAX_ITEM; idx++) {
            topics[idx] = Topic.CREATOR.newInstance("john" + idx, "This is content" + idx);
            mCache.put(topics[idx]);
        }

        for(int idx = 0 ;idx < MAX_ITEM; idx++) {
            assertEquals("john"+idx,topics[idx].getPublisher());
        }
    }
}
