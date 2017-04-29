package com.john.digg.source;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;
import com.john.digg.data.TopicLruCache;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by changjohn on 2017/4/28.
 */

public class TopicRepositoryTest extends TopicRepository {

    public TopicLruCache getTopLruCache() {
        return mTopicLruCache;
    }

    public static TopicRepository getInstance() {
        INSTANCE = new TopicRepositoryTest();
        return INSTANCE;
    }

    @Test
    public void testCreateTopic() {
        try {
            TopicRepository instance = TopicRepositoryTest.getInstance();
            instance.createTopic("John", "http://www.carousell.com");
            TopicLruCache cache = ((TopicRepositoryTest) instance).getTopLruCache();
            assertEquals(1, cache.size());
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadTopics() {
        try {
            TopicRepository instance = TopicRepositoryTest.getInstance();
            instance.createTopic("John", "http://www.carousell.com");
            List<Topic> topics = instance.readTopics(0,1);
            assertEquals("John", topics.get(0).getPublisher());
            assertEquals("http://www.carousell.com", topics.get(0).getContent());
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateTopics() {
        try {
            TopicRepository instance = TopicRepositoryTest.getInstance();
            Topic topic = instance.createTopic("John", "http://www.carousell.com");
            topic.upvotes();
            topic.upvotes();
            topic.upvotes();
            topic.downvotes();
            topic.downvotes();
            instance.updateTopic(topic);
            List<Topic> topics = instance.readTopics(0,1);
            assertEquals(1, topics.get(0).getVotes());
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteTopics() {
        try {
            TopicRepository instance = TopicRepositoryTest.getInstance();
            Topic topic = instance.createTopic("John", "http://www.carousell.com");
            instance.deleteTopic(topic);
            List<Topic> topics = instance.readTopics(0,1);
            fail();
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }
}
