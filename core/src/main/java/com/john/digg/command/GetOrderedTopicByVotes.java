package com.john.digg.command;

import com.john.digg.data.Topic;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

import java.util.Comparator;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by changjohn on 2017/4/27.
 */

public class GetOrderedTopicByVotes
        extends UseCase<GetOrderedTopicByVotes.RequestValues,GetOrderedTopicByVotes.ResponseValue>{

    private TopicRepository mRepository = null;
    private VotedComparator mComparator = null;
    public GetOrderedTopicByVotes(TopicRepository repository) {
        mRepository = repository;
        mComparator = new VotedComparator();
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        int start = requestValues.getStart();
        int end = requestValues.getEnd();
        try {
            List<Topic> topics = mRepository.readTopics(start,end);
            topics.sort(mComparator);
            mCallback.onSuccess(new ResponseValue(topics));
        } catch (IndexOutOfBoundsException e) {
            mCallback.onError();
        }
    }

    protected class VotedComparator implements Comparator<Topic> {

        @Override
        public int compare(Topic lhs, Topic rhs) {
            long lhsVotes = lhs.getVotes();
            long rhsVotes = rhs.getVotes();
            if (lhsVotes > rhsVotes) {
                return -1;
            } else if (lhsVotes < rhsVotes) {
                return 1;
            } else {
                return (lhs.getCreatedTimestamp() > rhs.getCreatedTimestamp()) ? 1 : -1;
            }
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
