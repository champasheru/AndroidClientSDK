package com.cs.saurabh.chatsnippet.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.cs.saurabh.chatsnippet.R;

public class MainActivity extends AppCompatActivity {
    private ListView chatMsgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatMsgList = (ListView)findViewById(R.id.chatMsgList);
        ChatMessagesListAdapter chatMessagesListAdapter = new ChatMessagesListAdapter(this);
        chatMsgList.setAdapter(chatMessagesListAdapter);
        chatMessagesListAdapter.loadChatMessages();
    }
}
