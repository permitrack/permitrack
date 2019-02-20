package com.sehinc.common.event;

public interface JobCreator<T>
{
    EventRunnable createJob(T event);
}
