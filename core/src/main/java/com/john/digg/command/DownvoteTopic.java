package com.john.digg.command;

import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by changjohn on 2017/4/27.
 */

public class DownvoteTopic extends UseCase<DownvoteTopic.RequestValues,DownvoteTopic.ResponseValue> {
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        try {
            Topic topic = checkNotNull(requestValues.getTopic(), "The topic should not be null");
            topic.downvotes();
            TopicRepository.getInstance().updateTopic(topic);
            mCallback.onSuccess(new ResponseValue());
        } catch (NullPointerException e) {
            mCallback.onError();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private Topic mTopic;
        public RequestValues(Topic topic) {
            mTopic = topic;
        }

        public Topic getTopic() {
            return mTopic;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {}
}
