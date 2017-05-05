package com.john.digg.source;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;
import com.john.digg.data.TopicLruCache;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkPositionIndexes;

/**
 * Created by changjohn on 2017/4/27.
 */

public class TopicRepository implements TopicsDataSource {

    protected static TopicRepository INSTANCE = null;

    protected TopicLruCache mTopicLruCache = null;

    protected TopicRepository() {
        mTopicLruCache = new TopicLruCache(1000);
    }

    public static TopicRepository getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TopicRepository();
        }
        return INSTANCE;
    }

    @Override
    public Topic createTopic(String publisher, String content) throws CreatedException {
        checkNotNull(publisher);
        checkNotNull(content);
        Topic newTopic = null;
        newTopic = Topic.CREATOR.newInstance(publisher,content);
        mTopicLruCache.put(newTopic);
        return newTopic;
    }

    @Override
    public List<Topic> readTopics(int start, int end) {
        int size = mTopicLruCache.size();
        checkPositionIndexes(start,end,size);
        return mTopicLruCache.get(start,end);
    }


    @Override
    public void updateTopic(Topic topic) {
        checkNotNull(topic);
        if(mTopicLruCache.isExist(topic)) {
            mTopicLruCache.put(topic);
        }
    }

    @Override
    public void deleteTopic(Topic topic) {
        checkNotNull(topic);
        mTopicLruCache.remove(topic);
    }


    @Override
    public int size() {
        return mTopicLruCache.size();
    }

    @Override
    public Topic findById(String id) {
        return mTopicLruCache.get(id);
    }
}
