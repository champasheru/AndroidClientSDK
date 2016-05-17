package com.cs.saurabh.chatsnippet.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.saurabh.chatsnippet.CSApplication;
import com.cs.saurabh.chatsnippet.R;
import com.cs.saurabh.chatsnippet.client.TaskEventListener;
import com.cs.saurabh.chatsnippet.client.haptik.tasks.GetAllNewChatMessagesTask;
import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 */
public class ChatMessagesListAdapter extends BaseAdapter{
    private ArrayList<ChatMessage> chatMessages;
    Context context;
    DateFormat dateFormat;
    ImageCache imageCache;
    private static final String CACHE_DIR_NAME = "imgche";

    private static class ViewHolder{
        ImageView imageView;
        TextView userDisplayName;
        TextView message;
        TextView timestamp;
    }

    public ChatMessagesListAdapter(Context context){
        chatMessages = new ArrayList<>();
        dateFormat = DateFormat.getTimeInstance();
        this.context = context;
        imageCache = new ImageCache(context.getDir(CACHE_DIR_NAME, Context.MODE_PRIVATE).getPath());
    }


    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_chat_message_row, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            viewHolder.userDisplayName = (TextView)convertView.findViewById(R.id.userDisplayName);
            viewHolder.message = (TextView)convertView.findViewById(R.id.message);
            viewHolder.timestamp = (TextView)convertView.findViewById(R.id.timestamp);
            convertView.setTag(viewHolder);
        }

        ChatMessage chatMessage = chatMessages.get(position);

        viewHolder = (ViewHolder)convertView.getTag();
        imageCache.setImage(viewHolder.imageView, chatMessage.getImageURL());
        viewHolder.userDisplayName.setText(chatMessage.getUserDisplayName());
        viewHolder.message.setText(chatMessage.getMessage());
        viewHolder.timestamp.setText(dateFormat.format(chatMessage.getTimestamp()));
        return convertView;
    }

    void loadChatMessages(){
        GetAllNewChatMessagesTask getAllNewChatMessagesTask = CSApplication.getClientInstance().getAllNewChatMessagesTask();
        Log.i("_#_Adapter", "Task = "+getAllNewChatMessagesTask);
        getAllNewChatMessagesTask.execute(new TaskEventListener() {
            @Override
            public void onFinish(Object result, Exception exception) {
                Log.i("_#_Adapter", "Response received = "+result+"\nException encountered = "+exception);
                chatMessages = (ArrayList<ChatMessage>)result;
                notifyDataSetChanged();
            }
        });
    }
}
