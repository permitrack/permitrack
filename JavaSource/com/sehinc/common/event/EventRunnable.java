package com.sehinc.common.event;

public interface EventRunnable<T>
    extends Runnable
{
    void setContext(T event);
}
