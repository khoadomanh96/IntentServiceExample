package com.example.intentserviceexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressbar;
    public static String DOWNLOAD_ACTION = "com.example.dara.downloadprogressintentservice.action";
    public static int RESULT_CODE = 1;
    private ArrayList<String> listUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listUrl = new ArrayList<>();
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");
        listUrl.add("https://lifebox.vn/sapi/download/picture?action=get&userid=4145533235364342430eca20a50cab8e760cc8c6e48ebbd8a3e8e1ce4855a13e1f64a64755fb2e90139d1f7dab04c9527ced7c81bd2ff1d43b&id=171374970&dk=MTYwbGxtYXNlOXE1aS0xNzEzNzQ5NzAuZ2lmP2V4cGlyZXM9MTU2NzY1ODY3OCZzaWduYXR1cmU9VG9wQVpSSFU3Y3pJZ0VaUVdGMjBialhnQ0VFJTNE&node=app01");

        if (Build.VERSION.SDK_INT >= M) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.INTERNET, Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}
                    , 1);
        }
        progressbar = findViewById(R.id.progress);
        progressbar.setIndeterminate(false);
        textView = findViewById(R.id.txtstatus);
        findViewById(R.id.id_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setProgress(0);
                progressbar.setVisibility(ProgressBar.VISIBLE);
                Intent intent = new Intent(MainActivity.this, UploadIntentService.class);
                intent.setAction(DOWNLOAD_ACTION);
                intent.putExtra("receiver", new MyResultReceiver(new Handler()));
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listUrl",listUrl);
                intent.putExtra("bundle",bundle);
                startService(intent);
            }
        });
        findViewById(R.id.id_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainTwoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (GlobalClass.getModelExampleList().size() > 0) {
            int numberDone = 0;
            for (ModelExample model : GlobalClass.getModelExampleList()) {
                if (model.isDone())
                    numberDone++;
            }
            String process = (int)(((numberDone)*100)/listUrl.size())+"";
            textView.setText(process+"%");
            progressbar.setVisibility(ProgressBar.VISIBLE);
            progressbar.setProgress(Integer.valueOf(process));
        } else {
            for (String url : listUrl) {
                ModelExample modelExample = new ModelExample(url);
                GlobalClass.getModelExampleList().add(modelExample);
            }
        }
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onReceiverEventBus(EventBusObject object) {
        int numberDone = 0;
        for (ModelExample model : GlobalClass.getModelExampleList()) {
            if (model.isDone())
                numberDone++;
        }
        String process = (int)(((numberDone)*100)/listUrl.size())+"";
        textView.setText(process+"%");
        progressbar.setVisibility(ProgressBar.VISIBLE);
        progressbar.setProgress(Integer.valueOf(process));
    }
    @SuppressLint("RestrictedApi")
    class MyResultReceiver extends ResultReceiver {

        /**
         * Create a new ResultReceive to receive results.  Your
         * {@link #onReceiveResult} method will be called from the thread running
         * <var>handler</var> if given, or from an arbitrary thread if null.
         *
         * @param handler
         */
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == RESULT_CODE) {
                String message = resultData.getString("progress");
                Log.e("khoado"," "+message);
//                textView.setText(message+"%");
//                progressbar.setProgress(Integer.parseInt(message));
            }
        }
    }
}

