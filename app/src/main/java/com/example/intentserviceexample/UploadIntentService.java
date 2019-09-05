package com.example.intentserviceexample;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.ResultReceiver;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UploadIntentService  extends IntentService {
    ResultReceiver resultReceiver;
    public UploadIntentService() {
        super("UploadIntentService");
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onHandleIntent(Intent intent) {
        resultReceiver = intent.getParcelableExtra("receiver");
        if (intent != null && intent.getAction().equals(MainActivity.DOWNLOAD_ACTION)) {
            List<ModelExample> list = GlobalClass.getModelExampleList();
            for (int i =0; i< list.size();i++) {
                Log.e("khoado","begin");
                URL url;
                int count;
                HttpURLConnection con = null;
                InputStream is = null;
                FileOutputStream fos = null;
                try {
//                    url = new URL("https://www.tutorialspoint.com/ios/ios_tutorial.pdf");
                    url = new URL(list.get(i).getUrl());
                    try {
                        // Open connection
                        list.get(i).setRunning(true);
                        con = (HttpURLConnection) url.openConnection();
                        // read stream
                        is = con.getInputStream();
                        String pathr = url.getPath();
                        // output file path
                        String filename = pathr.substring(pathr.lastIndexOf('/') + 1);
                        String path = Environment.getExternalStorageDirectory() + "/edoc_download/" + filename+i;
                        //write to file
                        File file = new File(Environment.getExternalStorageDirectory() + "/edoc_download/");
                        file.mkdirs();
                        fos = new FileOutputStream(path);

                        int lenghtOfFile = con.getContentLength();
                        byte data[] = new byte[1024];
                        Bundle b = new Bundle();
                        long total=0;
                        while ((count = is.read(data)) != -1) {
                            total+=count;
                            // write data to file
                            fos.write(data, 0, count);
                            // send progress to MainActiivty through ResultReceiver
//                            b.putString("progress", (int)((total*100)/lenghtOfFile)+"");
                            b.putString("progress", (int)(((i+1)*100)/list.size())+"");
                            resultReceiver.send(MainActivity.RESULT_CODE, b);
                        }
                        fos.flush();
                        list.get(i).setRunning(false);
                        list.get(i).setDone(true);
                        Log.e("khoado","end");
                        EventBus.getDefault().post(new EventBusObject());
                    } catch (Exception e) {
                        e.printStackTrace();

                    } finally {
                        if (is != null)
                            try {
                                is.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        if (fos != null)
                            try {
                                fos.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        if (con!=null) {
                            con.disconnect();
                        }
                    }

                } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
}