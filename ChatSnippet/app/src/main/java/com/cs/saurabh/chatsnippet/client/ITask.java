package com.cs.saurabh.chatsnippet.client;

/**
 * Created by saurabhATchampasheruDOTbuild on 15/5/16.
 * Any work/operation that needs to be carried out (& most probably on secondary threads, because those are either
 * computationally intensive or do network/file i/o) by the Client for it's consumer.
 * Tasks can be anything like - web service call, large file i/o, Bitmap processing etc.
 *
 * One shall implement this interface or provide concrete implementation of abstract class - Task
 * by overriding the execute() method - it shall have the task specific code.
 */
public interface ITask {
    public void execute(TaskEventListener taskEventListener);
}
