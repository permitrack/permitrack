package com.sehinc.erosioncontrol.command;

public interface Command<T>
{
    T execute(T context);
}
