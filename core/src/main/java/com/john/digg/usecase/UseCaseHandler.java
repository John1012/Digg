package com.john.digg.usecase;


/**
 * Created by changjohn on 2017/4/27.
 */

public class UseCaseHandler {
    private static UseCaseHandler INSTANCE;

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler();
        }
        return INSTANCE;
    }

    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            final UseCase<T,R> useCase, T values, UseCase.UseCaseCallback<R> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(callback);
        useCase.run();
    }
}
