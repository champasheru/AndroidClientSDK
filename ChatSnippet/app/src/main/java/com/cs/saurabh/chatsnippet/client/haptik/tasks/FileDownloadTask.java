package com.cs.saurabh.chatsnippet.client.haptik.tasks;

import android.os.Handler;
import android.util.Log;

import com.cs.saurabh.chatsnippet.client.Task;
import com.cs.saurabh.chatsnippet.client.haptik.service.HaptikService;

/**
 * Created by saurabhATchampasheruDOTbuild on 17/5/16.
 * The task for downloading the file from the cloud.
 * TODO: Refer Task class - fork out a subclass called ProgressTask. All tasks which need to show the definite/in-definite progress
 * shall extend from this ProgressTask, for e.g. this FileDownloadTask.
 */
public class FileDownloadTask extends Task {
    private String fileDownloadURL;
    private String localFilepath;

    public FileDownloadTask(String fileDownloadURL, String localFilepath){
        this.localFilepath = localFilepath;
        this.fileDownloadURL = fileDownloadURL;
    }

    @Override
    protected void executeInternal() {
        Log.i("_#_FileDwTask", "executeInternal Start");
        final boolean status =  ((HaptikService) getClient().getService()).downloadFile(fileDownloadURL, localFilepath);
        //TODO:Not optimized for now, we can scale the Bitmap according to the needs & give it back.
        Log.i("_#_FileDwTask", "executeInternal End");
        Handler handler = new Handler(getClient().getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                getTaskEventListener().onFinish(status, null);
            }
        });
    }
}
