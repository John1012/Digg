package com.john.digg.usecase;

import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by changjohn on 2017/4/28.
 */

public class UseCaseHandlerTest {
    public static class FakeUseCase extends UseCase<FakeUseCase.RequestValues,FakeUseCase.ResponseValue> {

        @Override
        protected void executeUseCase(RequestValues requestValues) {
            boolean isSuccess = requestValues.isSuccess();
            if(isSuccess) {
                mCallback.onSuccess(new ResponseValue());
            } else {
                mCallback.onError();
            }
        }

        public static final class RequestValues implements UseCase.RequestValues {
            private boolean mIsSuccess;
            public RequestValues(boolean isSuccess) {
                mIsSuccess = isSuccess;
            }

            public boolean isSuccess() {
                return mIsSuccess;
            }
        }
        public static final class ResponseValue implements UseCase.ResponseValue {}
    }

    @Test
    public void testInvokeCallback() {
        UseCase useCase = new FakeUseCase();
        UseCaseHandler handler = UseCaseHandler.getInstance();
        handler.execute(useCase, new FakeUseCase.RequestValues(true), new UseCase.UseCaseCallback() {
            @Override
            public void onSuccess(Object response) {

            }

            @Override
            public void onError() {
                fail();
            }
        });
    }
}
