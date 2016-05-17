package com.cs.saurabh.chatsnippet.client.haptik.service;

import com.cs.saurabh.chatsnippet.client.service.IService;
import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.util.ArrayList;

/**
 * Created by saurabhATchampasheruDOTbuild on 15/5/16.
 * Specs out the contract for the Haptik specfic service endpoints.
 */
public interface HaptikService extends IService {
    public ArrayList<ChatMessage> getAllNewChatMessages();

    public boolean downloadFile(String fileDownloadURL, String localFilepath);
}
