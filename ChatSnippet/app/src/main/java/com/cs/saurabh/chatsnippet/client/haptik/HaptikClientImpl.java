package com.cs.saurabh.chatsnippet.client.haptik;

import android.os.Looper;

import com.cs.saurabh.chatsnippet.client.Client;
import com.cs.saurabh.chatsnippet.client.haptik.service.HaptikRESTServiceImpl;
import com.cs.saurabh.chatsnippet.client.haptik.service.HaptikService;
import com.cs.saurabh.chatsnippet.client.haptik.tasks.FileDownloadTask;
import com.cs.saurabh.chatsnippet.client.haptik.tasks.GetAllNewChatMessagesTask;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 * Singleton & concrete implementation of the HaptikClient; this makes sure that there's a single instance throughout the lifetime
 * of the app.
 */
public class HaptikClientImpl extends Client implements HaptikClient {

    /**
     * Use the default implementation.
     */
    public HaptikClientImpl(){
        super();
        serviceImpl = new HaptikRESTServiceImpl();
    }

    protected HaptikClientImpl(HaptikService haptikService, Looper looper){
        super(haptikService, looper);
    }

    public GetAllNewChatMessagesTask getAllNewChatMessagesTask(){
        GetAllNewChatMessagesTask getAllNewChatMessagesTask = new GetAllNewChatMessagesTask();
        getAllNewChatMessagesTask.setClient(this);
        return getAllNewChatMessagesTask;
    }


    @Override
    public FileDownloadTask getFileDownloadTask(String fileDownloadURL, String localFilepath) {
        FileDownloadTask fileDownloadTask = new FileDownloadTask(fileDownloadURL, localFilepath);
        fileDownloadTask.setClient(this);
        return fileDownloadTask;
    }
}
