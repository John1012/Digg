package com.john.digg.source;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;

import java.util.List;

/**
 * Created by changjohn on 2017/4/27.
 */

public interface TopicsDataSource {
    Topic createTopic(String publisher, String content) throws CreatedException;
    List<Topic> readTopics(int start, int end);
    void updateTopic(Topic topic);
    void deleteTopic(Topic topic);
    int size();
    Topic findById(String id);
}
