package com.cs.saurabh.chatsnippet;

import android.app.Application;

import com.cs.saurabh.chatsnippet.client.haptik.HaptikClient;
import com.cs.saurabh.chatsnippet.client.haptik.HaptikClientImpl;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 *
 * Keep the globally required fields here: some may need to be singletons, like Client others may not.
 */
public class CSApplication extends Application{
    private static HaptikClient clientInstance;

    public static HaptikClient getClientInstance(){
        synchronized (HaptikClient.class){
            if (clientInstance == null){
                clientInstance = new HaptikClientImpl();
            }
            return clientInstance;
        }
    }
}
