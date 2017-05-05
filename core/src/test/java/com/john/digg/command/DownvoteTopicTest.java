package com.john.digg.command;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;
import com.john.digg.usecase.UseCaseHandler;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by changjohn on 2017/4/28.
 */

public class DownvoteTopicTest {
    private UseCase.UseCaseCallback<DownvoteTopic.ResponseValue> mCallback = new UseCase.UseCaseCallback() {
        @Override
        public void onSuccess(Object response) {

        }

        @Override
        public void onError() {

        }
    };

    @Test
    public void testDownvote() throws CreatedException {
        Topic topic = TopicRepository.getInstance().createTopic("John Chang","http://www.carousell.com");
        UseCase useCase = new DownvoteTopic();
        UseCaseHandler handler = UseCaseHandler.getInstance();
        handler.execute(useCase, new DownvoteTopic.RequestValues(topic.getId()), mCallback);
        handler.execute(useCase, new DownvoteTopic.RequestValues(topic.getId()), mCallback);
        handler.execute(useCase, new DownvoteTopic.RequestValues(topic.getId()), mCallback);
        handler.execute(useCase, new DownvoteTopic.RequestValues(topic.getId()), mCallback);
        handler.execute(useCase, new DownvoteTopic.RequestValues(topic.getId()), mCallback);
        assertEquals(-5,topic.getVotes());
    }
}
