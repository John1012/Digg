package com.john.digg.data;

import com.john.digg.data.Topic;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by changjohn on 2017/4/25.
 */
public class TopicLruCache {

    protected int mMaxItems = 0;
    protected LinkedList<Topic> mTopicQueue = null;

    public TopicLruCache(int maxItems) {
        mMaxItems = maxItems;
        mTopicQueue = new LinkedList<Topic>();
    }

    public void put(final Topic topic) {
        if(isExist(topic)) {
            mTopicQueue.add(mTopicQueue.indexOf(topic),topic);
        } else {
            mTopicQueue.addFirst(topic);
        }
        cleanup();
    }

    public void remove(final Topic topic) {
        if(isExist(topic)) {
            mTopicQueue.remove(topic);
            cleanup();
        } else {

        }
    }

    public List<Topic> get(final int start, final int end) {
        if(start < 0 || end > mTopicQueue.size())
            throw new IndexOutOfBoundsException();

        return new LinkedList<Topic>(mTopicQueue.subList(start, end));
    }

    public Topic get(final String id) {
        for(Topic topic : mTopicQueue) {
            if(topic.getId().equals(id))
                return topic;
        }
        return null;
    }

    public int size() {
        return mTopicQueue.size();
    }

    public boolean isExist(final Topic topic) {
        return mTopicQueue.contains(topic);
    }


    protected void cleanup() {
        if (size() > mMaxItems) {
            mTopicQueue.removeLast();
        }
    }
}
