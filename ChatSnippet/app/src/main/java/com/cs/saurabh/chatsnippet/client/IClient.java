package com.cs.saurabh.chatsnippet.client;

import android.os.Looper;

import com.cs.saurabh.chatsnippet.client.service.IService;

/**
 * Created by saurabhATchampasheruDOTbuild on 15/5/16.
 * Very very generic contract for the what's called as a Client facade.
 * On framework side rather than app/domain side.
 */
public interface IClient {
    public IService getService();
    public Looper getLooper();
    public void submitTask(Task task);
}
