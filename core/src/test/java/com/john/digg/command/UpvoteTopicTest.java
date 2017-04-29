package com.john.digg.command;

import com.john.digg.data.CreatedException;
import com.john.digg.data.Topic;
import com.john.digg.usecase.UseCase;
import com.john.digg.usecase.UseCaseHandler;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by changjohn on 2017/4/27.
 */

public class UpvoteTopicTest {

    private UseCase.UseCaseCallback<UpvoteTopic.ResponseValue> mCallback = new UseCase.UseCaseCallback() {
        @Override
        public void onSuccess(Object response) {

        }

        @Override
        public void onError() {

        }
    };

    @Test
    public void testUpvote() throws CreatedException {
        Topic topic = Topic.CREATOR.newInstance("John Chang","http://www.carousell.com");
        UseCase useCase = new UpvoteTopic();
        UseCaseHandler handler = UseCaseHandler.getInstance();
        handler.execute(useCase, new UpvoteTopic.RequestValues(topic), mCallback);
        handler.execute(useCase, new UpvoteTopic.RequestValues(topic), mCallback);
        handler.execute(useCase, new UpvoteTopic.RequestValues(topic), mCallback);
        handler.execute(useCase, new UpvoteTopic.RequestValues(topic), mCallback);
        handler.execute(useCase, new UpvoteTopic.RequestValues(topic), mCallback);
        assertEquals(5,topic.getVotes());
    }
}
