package com.cs.saurabh.chatsnippet.client.haptik;

import com.cs.saurabh.chatsnippet.client.haptik.tasks.FileDownloadTask;
import com.cs.saurabh.chatsnippet.client.haptik.tasks.GetAllNewChatMessagesTask;

/**
 * Created by saurabhATchampasheruDOTbuild on 15/5/16.
 * Specs out the contract for the behaviour for Haptik client.
 */
public interface HaptikClient {
    /**
     * @return the task that will fetch all the chat messages corresponding to the currently logged in user.
     */
    public GetAllNewChatMessagesTask getAllNewChatMessagesTask();

    public FileDownloadTask getFileDownloadTask(String fileDownloadURL, String localFilepath);
}
