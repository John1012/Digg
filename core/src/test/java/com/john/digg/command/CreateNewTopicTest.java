package com.john.digg.command;

import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;
import com.john.digg.usecase.UseCaseHandler;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by changjohn on 2017/4/27.
 */

public class CreateNewTopicTest {

    UseCase.UseCaseCallback<CreateNewTopic.ResponseValue> mCallback = new UseCase.UseCaseCallback<CreateNewTopic.ResponseValue>() {
        @Override
        public void onSuccess(CreateNewTopic.ResponseValue response) {
         }

        @Override
        public void onError() {
        }
    };

    @Test
    public void testCreateNewTopic() {
        UseCase useCase = new CreateNewTopic(TopicRepository.getInstance());
        UseCaseHandler handler = UseCaseHandler.getInstance();
        handler.execute(useCase,
                new CreateNewTopic.RequestValues("john","http://www.carousell.com"),
                mCallback);
        List<Topic> topics = TopicRepository.getInstance().readTopics(0,1);
        assertEquals(1, topics.size());
    }
}
