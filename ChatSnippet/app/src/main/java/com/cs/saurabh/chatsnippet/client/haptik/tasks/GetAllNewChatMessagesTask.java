package com.cs.saurabh.chatsnippet.client.haptik.tasks;

import android.os.Handler;
import android.util.Log;

import com.cs.saurabh.chatsnippet.client.Task;
import com.cs.saurabh.chatsnippet.client.haptik.service.HaptikService;
import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.util.ArrayList;

/**
 * Created by saurabhATchampasheruDOTbuild on 15/5/16.
 * Task to fetch/get all the messages from the cloud.
 */
public class GetAllNewChatMessagesTask extends Task {

    @Override
    protected void executeInternal() {
        Log.i("_#_GetMessagesTask", "executeInternal Start");
        final ArrayList<ChatMessage> chatMessages = ((HaptikService)getClient().getService()).getAllNewChatMessages();
        Log.i("_#_GetMessagesTask", "executeInternal End");
        Handler handler = new Handler(getClient().getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                getTaskEventListener().onFinish(chatMessages, null);
            }
        });
    }
}
