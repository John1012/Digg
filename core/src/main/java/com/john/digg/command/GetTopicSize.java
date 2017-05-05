package com.john.digg.command;

import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

/**
 * Created by changjohn on 2017/4/30.
 */

public class GetTopicSize extends
        UseCase<GetTopicSize.RequestValues,GetTopicSize.ResponseValue> {

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mCallback.onSuccess(new ResponseValue(TopicRepository.getInstance().size()));
    }

    public static final class RequestValues implements UseCase.RequestValues {}
    public static final class ResponseValue implements UseCase.ResponseValue {
        int mTopicSize = 0;
        public ResponseValue(int topicSize) {
            mTopicSize = topicSize;
        }

        public int getTopicSize() {
            return mTopicSize;
        }
    }
}
