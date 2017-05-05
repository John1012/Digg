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
            String id = checkNotNull(requestValues.getId(),"The id should not be null");
            Topic topic = TopicRepository.getInstance().findById(id);
            topic.downvotes();
            checkNotNull(mCallback).onSuccess(new ResponseValue(topic.getVotes()));
        } catch (NullPointerException e) {
//            mCallback.onError();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String mId;
        public RequestValues(String id) {
            mId = id;
        }

        public String getId() {
            return mId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private long mVote;
        public ResponseValue(long vote) {
            mVote = vote;
        }

        public long getVote() {
            return mVote;
        }
    }
}
