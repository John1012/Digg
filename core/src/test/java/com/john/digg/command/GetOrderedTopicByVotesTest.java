package com.john.digg.command;

import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;
import com.john.digg.usecase.UseCaseHandler;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by changjohn on 2017/4/27.
 */

public class GetOrderedTopicByVotesTest {

    @Test
    public void testGetTopic() {
        UseCase useCase = new GetOrderedTopicByVotes(TopicRepository.getInstance());
        UseCaseHandler handler = UseCaseHandler.getInstance();
        handler.execute(useCase, new GetOrderedTopicByVotes.RequestValues(0, 20), new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess(Object response) {
                fail("It's should not success");
            }

            @Override
            public void onError() {

            }
        });
    }
}
