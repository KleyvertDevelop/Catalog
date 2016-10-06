package com.momentoustech.catalog.domain.usecase;


/**
 * Created by Kleyvert on 10/3/16.
 */
public abstract class UseCase<T> {

    /**
     * Abstract method for execute a command
     * @return {@link T}
     */
    public abstract T Execute();
}
