package com.john.digg.middle.mapper;

import com.john.digg.data.Topic;
import com.john.digg.middle.model.TopicModel;

import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by changjohn on 2017/5/3.
 */

public class TopicMapper {

    public static TopicModel transform(Topic topic) {
        checkNotNull(topic,"Cannot transform null object");

        final TopicModel topicModel = new TopicModel.Builder()
                .setId(topic.getId())
                .setPublisher(topic.getPublisher())
                .setContent(topic.getContent())
                .setCreatedTimestamp(topic.getCreatedTimestamp())
                .setVoted(topic.getVotes())
                .create();

        return topicModel;
    }
    public static List<TopicModel> transform(List<Topic> topics) {
        checkNotNull(topics,"Cannot transform null object");

        List<TopicModel> models;
        if (!topics.isEmpty()) {
            models = new LinkedList<TopicModel>();
            for (Topic topic : topics) {
                models.add(transform(topic));
            }
        } else {
            models = new LinkedList<TopicModel>();
        }

        return models;
    }
}
