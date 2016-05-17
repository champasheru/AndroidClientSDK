package com.cs.saurabh.chatsnippet.client.haptik.service;

import android.util.Log;

import com.cs.saurabh.chatsnippet.client.haptik.parser.HaptikDataParser;
import com.cs.saurabh.chatsnippet.client.haptik.parser.impl.HaptikDataParserImpl;
import com.cs.saurabh.chatsnippet.client.service.RESTService;
import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 */
public class HaptikRESTServiceImpl extends RESTService implements HaptikService {
    private HaptikDataParser jsonParser = new HaptikDataParserImpl();
    private String localFilepath;

    public ArrayList<ChatMessage> getAllNewChatMessages(){
        ArrayList<ChatMessage> chatMessages = null;
        try {
            chatMessages = (ArrayList<ChatMessage>)executeRequest(new URL("http://haptik.co/android/test_data/"), "getAllNewChatMessages");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            return chatMessages;
        }
    }


    public boolean downloadFile(String fileDownloadURL, String localFilepath){
        this.localFilepath = localFilepath;
        boolean status = false;
        try {
            status = (Boolean)executeRequest(new URL(fileDownloadURL), "downloadFile");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }finally {
            return status;
        }
    }


    private Object executeRequest(URL endpointURL, String endpointID){
        HttpURLConnection connection = null;
        Object response = null;
        try {
            connection = (HttpURLConnection) endpointURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Log.i("_#_HaptikRESTServImpl", "Response Code =" + connection.getResponseCode());

            switch (endpointID){
                case "getAllNewChatMessages":
                    response = jsonParser.parseGetAllNewChatMessagesResponse(connection.getInputStream());
                    break;

                case "downloadFile":
                    response = jsonParser.parseDownloadFileResponse(connection.getInputStream(), localFilepath);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }
            return response;
        }
    }
}

