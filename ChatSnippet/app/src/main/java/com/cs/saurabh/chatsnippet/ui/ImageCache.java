package com.cs.saurabh.chatsnippet.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.cs.saurabh.chatsnippet.CSApplication;
import com.cs.saurabh.chatsnippet.R;
import com.cs.saurabh.chatsnippet.client.TaskEventListener;
import com.cs.saurabh.chatsnippet.client.haptik.tasks.FileDownloadTask;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by saurabhATchampasheruDOTbuild on 17/5/16.
 *
 * Provides an in memory cache for holding the images pertaining to the image view.
 * The local cached files/bitmaps are backed by the underlying file system.
 * //TODO: Improve it(specifically rebuild file cache from cache dir on subsequent usage).
 */
public class ImageCache {
    /**
     * As soon as the component/view using this instance is reclaimed from the memory, so does all the items
     * in this collection. So, when the convertView of the list adapter is reused, the entry no longer exists in
     * this weak hash map.
     */
    private WeakHashMap<ImageView, String> images;
    //Map maintains remote to local file paths.
    private LinkedHashMap<String, String> localFileCache;
    private String cacheDirPath;

    public ImageCache(String cacheDirPath){
        this.cacheDirPath = cacheDirPath;
        images = new WeakHashMap<>();
        //Maintains the LRU access order; every get/put affects it; can be handy when it's time for cache cleanup.
        localFileCache = new LinkedHashMap<>(5, 1.5f, true);
    }

    public void setImage(final ImageView imageView, final String remoteURL){
        if (remoteURL == null || remoteURL.equals("")){
            imageView.setImageResource(R.mipmap.ic_launcher);
            return;
        }


        images.put(imageView, remoteURL);

        //Check if the file is already downloaded & locally cached.
        if (localFileCache.get(remoteURL) == null){
            Log.i("_#_ImageCache", "remoteURL = "+remoteURL);

            imageView.setImageResource(R.mipmap.ic_launcher);

            //Trigger the image download.
            String filename = remoteURL.substring(remoteURL.lastIndexOf(File.separator)+1);
            Log.i("_#_ImageCache", "Filename = "+filename);
            final String localFilepath = cacheDirPath+File.separator+filename;
            Log.i("_#_ImageCache", "localFilepath = "+localFilepath);

            FileDownloadTask fileDownloadTask = CSApplication.getClientInstance().getFileDownloadTask(remoteURL, localFilepath);
            fileDownloadTask.execute(new TaskEventListener() {
                @Override
                public void onFinish(Object result, Exception exception) {
                    boolean status = (Boolean)result;
                    Log.i("_#_ImageCache", "Status = "+status);
                    //Update cache
                    localFileCache.put(remoteURL, localFilepath);
                    String existingRemoteURL = images.get(imageView);
                    //Only if the image view is still visible, then only update it's contents.
                    if (existingRemoteURL != null && existingRemoteURL.equals(remoteURL)) {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFilepath);
                        imageView.setImageBitmap(bitmap);
                    }
                }
            });
        }else{
            Log.i("_#_ImageCache", "Cached File Available, using it");
            String localFilepath = localFileCache.get(remoteURL);
            Bitmap bitmap = BitmapFactory.decodeFile(localFilepath);
            imageView.setImageBitmap(bitmap);
        }
    }
}
