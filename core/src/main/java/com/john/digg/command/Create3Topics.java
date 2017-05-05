package com.john.digg.command;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

/**
 * Created by changjohn on 2017/4/30.
 */

public class Create3Topics
        extends UseCase<Create3Topics.RequestValues,Create3Topics.ResponseValue> {

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        Topic[] topics = new Topic[3];
        try {
            topics[0] = TopicRepository.getInstance().createTopic("John","This is a day. People like to go outside and enjoy sunshine!");
            topics[1] = TopicRepository.getInstance().createTopic("John","Variety is the spice of life.");
            topics[2] = TopicRepository.getInstance().createTopic("John","Bad times make a good man.");
            topics[0].upvotes();
            topics[0].upvotes();
            topics[0].upvotes();
            topics[1].upvotes();
            topics[1].upvotes();
            topics[2].upvotes();
        } catch (CreatedException e) {
            e.printStackTrace();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {}
    public static final class ResponseValue implements UseCase.ResponseValue {}
}
