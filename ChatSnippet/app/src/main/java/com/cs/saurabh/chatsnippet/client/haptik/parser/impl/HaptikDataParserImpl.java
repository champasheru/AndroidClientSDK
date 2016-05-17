package com.cs.saurabh.chatsnippet.client.haptik.parser.impl;

import android.util.JsonReader;
import android.util.Log;

import com.cs.saurabh.chatsnippet.client.haptik.parser.HaptikDataParser;
import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabhATchampasheruDOTbuild on 16/5/16.
 */
public class HaptikDataParserImpl implements HaptikDataParser {

    @Override
    public List<ChatMessage> parseGetAllNewChatMessagesResponse(InputStream inputStream) {
        List<ChatMessage> chatMessages = null;

        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            jsonReader.beginObject();
            while (jsonReader.hasNext()){
                String nextName = jsonReader.nextName();
                Log.i("_#_HaptikJSONParsImpl", "nextName =" + nextName);

                if(nextName.equals("messages")){
                    Log.i("_#_HaptikJSONParsImpl", "nextName =" + nextName);
                    //Start parsing the array of message objects.
                    jsonReader.beginArray();
                    chatMessages = new ArrayList<>();

                    while (jsonReader.hasNext()){
                        Log.i("_#_HaptikJSONParsImpl", "MessageObjectStart");
                        ChatMessage chatMessage = new ChatMessage();
                        chatMessage.readJSON(jsonReader);
                        chatMessages.add(chatMessage);
                        Log.i("_#_HaptikJSONParsImpl", "ChatMsgList =" + chatMessages);
                    }
                    jsonReader.endArray();
                }else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            jsonReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("_#_HaptikJSONParserImpl", "Exc = "+e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("_#_HaptikJSONParserImpl", "Exc = " + e);
        }

        Log.i("_#_HaptikJSONParserImpl", "ChatMessagesListSize ="+chatMessages);
        return chatMessages;
    }

    @Override
    public boolean parseDownloadFileResponse(InputStream inputStream, String localFilepath) {
        byte[] buffer = new byte[1024];
        FileOutputStream fos = null;
        boolean status = true;
        try {
            File file = new File(localFilepath);
            if (!file.exists()){
                fos = new FileOutputStream(file);
                int numBytesRead;
                while ((numBytesRead = inputStream.read(buffer)) != -1){
                    fos.write(buffer, 0, numBytesRead);
                }
            }
        } catch (IOException e) {
            status = false;
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }
}
