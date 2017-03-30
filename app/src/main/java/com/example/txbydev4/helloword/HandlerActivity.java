package com.example.txbydev4.helloword;

import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.wlib.http.okhttp.OkHttp;

/**
 * Created by txbydev4 on 17/3/27.
 */

public class HandlerActivity extends AppCompatActivity {
    private EditText et_url;
    private TextView tv_show;
    private Button btn_load;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String result = (String) msg.obj;
            tv_show.setText(result);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_activity);
        et_url = (EditText) findViewById(R.id.et_url);
        tv_show = (TextView) findViewById(R.id.tv_show);
        btn_load = (Button) findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = et_url.getText().toString().trim();
                        try{
                            OkHttp ok = new OkHttp(url);
                            ok.get();
                            Message msg = Message.obtain();
                            msg.obj = ok.getBody();
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
