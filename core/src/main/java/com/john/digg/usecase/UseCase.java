package com.john.digg.usecase;

/**
 * Created by changjohn on 2017/4/27.
 */

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValue> {
    private Q mRequestValues;
    protected UseCaseCallback<P> mCallback;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public void setUseCaseCallback(UseCaseCallback<P> callback) {
        mCallback = callback;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mCallback;
    }

    void run() {
        executeUseCase(mRequestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public interface RequestValues {}
    public interface ResponseValue {}
    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}
