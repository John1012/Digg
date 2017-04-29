package com.john.digg.data;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by changjohn on 2017/4/25.
 */

public class TopicTest {
    protected static final String publisher = "john";
    protected static final String content = "This is content";

    @Test
    public void testNormalConstructor() {
        Topic topic = null;
        try {
            topic = Topic.CREATOR.newInstance(publisher,content);
            assertNotNull(topic);
            assertEquals(topic.getPublisher(),publisher);
            assertEquals(topic.getContent(),content);
            assertNotEquals(topic.getCreatedTimestamp(),0);
            assertEquals(topic.getVotes(),0);
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CreatedException.class)
    public void testContentOverLimitation() throws CreatedException {
        Topic topic = null;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < Topic.MAX_CONTENT_CHAR+1; i++) {
            builder.append('a');
        }
        String content256 = builder.toString();
        topic = Topic.CREATOR.newInstance(publisher,content256);
    }

    @Test
    public void testEquals() {
        Topic topic1 = null;
        Topic topic2 = null;
        try {
            topic1 = Topic.CREATOR.newInstance(publisher,content);
            topic2 = Topic.CREATOR.newInstance(publisher,content);
            assertEquals(topic1,topic2);
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }
}
