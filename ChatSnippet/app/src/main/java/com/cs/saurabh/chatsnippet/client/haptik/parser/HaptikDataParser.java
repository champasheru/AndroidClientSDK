package com.cs.saurabh.chatsnippet.client.haptik.parser;

import com.cs.saurabh.chatsnippet.data.ChatMessage;

import java.io.InputStream;
import java.util.List;

/**
 * Created by saurabhATchampasheruDOTbuild on 16/5/16.
 * Specs out the contract for the Haptik specfic data parsing.
 */
public interface HaptikDataParser {

    List<ChatMessage> parseGetAllNewChatMessagesResponse(InputStream inputStream);

    boolean parseDownloadFileResponse(InputStream inputStream, String localFilepath);
}
