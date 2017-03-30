package com.example.txbydev4.helloword;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.txbydev4.helloword.services.MyServiceTest;
import com.lib.wlib.intent.IntentTool;

/**
 * Created by txbydev4 on 17/3/24.
 */

public class MyServiceActivity extends AppCompatActivity {
    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    private TextView mTvState;
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serMessenger = new Messenger(service);
            mTvState.setText("connect");
            iscon = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serMessenger = null;
            mTvState.setText("disconnect");
            iscon = false;
        }
    };
    private boolean iscon = false;

    private Messenger serMessenger;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            TextView tv = new TextView(MyServiceActivity.this);
            tv.setText(msg.getData().getString("msg"));
            mLyContainer.addView(tv);
            super.handleMessage(msg);
        }
    };
    private Messenger messenger = new Messenger(handler);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mLyContainer = (LinearLayout) findViewById(R.id.id_ll_container);
        //开始绑定服务
        bindServiceInvoked();
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain(null, 1);
                Bundle bundle = new Bundle();
                bundle.putString("msg", "hello, service");
                msg.setData(bundle);
                TextView tv = new TextView(MyServiceActivity.this);
                tv.setText(msg.getData().getString("msg"));
                mLyContainer.addView(tv);
                msg.replyTo = messenger;
                if (iscon) {
                    try{
                        serMessenger.send(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void bindServiceInvoked() {
        Intent intent = IntentTool.makeIntent(this, MyServiceTest.class);

        bindService(intent, con, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(con);
    }
}
