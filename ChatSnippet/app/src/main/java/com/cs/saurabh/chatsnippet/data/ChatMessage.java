package com.cs.saurabh.chatsnippet.data;

import android.util.JsonReader;
import android.util.Log;

import com.cs.saurabh.chatsnippet.client.parser.JSONSerializable;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 */
public class ChatMessage implements JSONSerializable {
    private String message;
    private String username;
    private String userDisplayName;
    private String imageURL;
    private Date timestamp;

    public static final String MESSAGE_BODY = "body";
    public static final String MESSAGE_USERNAME = "username";
    public static final String MESSAGE_NAME = "Name";
    public static final String MESSAGE_IMAGE_URL = "image-url";
    public static final String MESSAGE_MESSAGE_TIME = "message-time";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public void readJSON(JsonReader jsonReader) {
        String nextName = null;
        try {
            jsonReader.beginObject();

            //Parse each field of the message object.
            while (jsonReader.hasNext()){
                nextName = jsonReader.nextName();
                Log.i("_#_ChatMessage", "nextName =" + nextName);
                if(nextName.equals(ChatMessage.MESSAGE_USERNAME)){
                    setUsername(jsonReader.nextString());
                }else if(nextName.equals(ChatMessage.MESSAGE_NAME)){
                    setUserDisplayName(jsonReader.nextString());
                }else if(nextName.equals(ChatMessage.MESSAGE_BODY)){
                    setMessage(jsonReader.nextString());
                }else if(nextName.equals(ChatMessage.MESSAGE_IMAGE_URL)){
                    setImageURL(jsonReader.nextString());
                }else if(nextName.equals(ChatMessage.MESSAGE_MESSAGE_TIME)){
                    try {
                        String dateString = jsonReader.nextString();
                        setTimestamp(dateFormat.parse(dateString));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            jsonReader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeJSON() {

    }
}