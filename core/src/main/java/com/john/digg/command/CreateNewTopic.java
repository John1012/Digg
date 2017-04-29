package com.john.digg.command;

import com.john.digg.data.CreatedException;
import com.john.digg.source.TopicRepository;
import com.john.digg.usecase.UseCase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by changjohn on 2017/4/27.
 */

public class CreateNewTopic extends
        UseCase<CreateNewTopic.RequestValues,CreateNewTopic.ResponseValue> {

    private TopicRepository mRepository = null;

    public CreateNewTopic(TopicRepository repository) {
        mRepository = repository;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        try {
            String publisher = checkNotNull(requestValues.getPublisher(), "publisher should not be null!");
            String content = checkNotNull(requestValues.getContent(), "content should not be null!");
            mRepository.createTopic(publisher, content);
            mCallback.onSuccess(new ResponseValue());
        } catch (CreatedException e) {
            mCallback.onError();
        } catch (NullPointerException e) {
            mCallback.onError();
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String mPublisher;
        private String mContent;

        public RequestValues(String publisher, String content) {
            mPublisher = publisher;
            mContent = content;
        }

        public String getPublisher() {
            return mPublisher;
        }

        public String getContent() {
            return mContent;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {}
}
