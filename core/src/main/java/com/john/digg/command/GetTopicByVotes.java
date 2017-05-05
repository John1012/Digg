package com.john.digg.command;

import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by changjohn on 2017/4/27.
 */

public class GetTopicByVotes
        extends UseCase<GetTopicByVotes.RequestValues,GetTopicByVotes.ResponseValue>{

    private TopicRepository mRepository = null;
    public GetTopicByVotes(TopicRepository repository) {
        mRepository = repository;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        int start = requestValues.getStart();
        int end = requestValues.getEnd();
        try {
            List<Topic> topics = mRepository.readTopics(start,end);
            mCallback.onSuccess(new ResponseValue(topics));
        } catch (IndexOutOfBoundsException e) {
            mCallback.onError();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int mStart = 0;
        private int mEnd = 0;

        public RequestValues(int start,int end) {
            mStart = start;
            mEnd = end;
        }

        public int getStart() {
            return mStart;
        }

        public int getEnd() {
            return mEnd;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<Topic> mTopics;

        public ResponseValue(List<Topic> topics) {
            mTopics = checkNotNull(topics,"topics cannot be null!");
        }

        public List<Topic> getTopics() {
            return mTopics;
        }
    }
}
