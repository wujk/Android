package com.example.txbydev4.helloword;

import android.app.Notification;
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
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.txbydev4.helloword.services.MyService;
import com.lib.wlib.intent.IntentTool;

/**
 * Created by txbydev4 on 17/3/22.
 */

public class ServiceActivity extends AppCompatActivity {private static final String TAG = "MainActivity";
    private static final int MSG_SUM = 0x110;

    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    //显示连接状态
    private TextView mTvState;

    private Messenger mService;
    private boolean isConn;

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msgFromServer)
        {
            switch (msgFromServer.what)
            {
                case MSG_SUM:
                    TextView tv = (TextView) mLyContainer.findViewById(msgFromServer.arg1);
                    tv.setText(tv.getText() + "=>" + msgFromServer.arg2);
                    break;
            }
            super.handleMessage(msgFromServer);
        }
    };
    private Messenger mMessenger = new Messenger(handler);


    private ServiceConnection mConn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            mService = new Messenger(service);
            isConn = true;
            mTvState.setText("connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            mService = null;
            isConn = false;
            mTvState.setText("disconnected!");
        }
    };

    private int mA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        Log.d("222222", "onCreate");
        //开始绑定服务
        bindServiceInvoked();

        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mLyContainer = (LinearLayout) findViewById(R.id.id_ll_container);

        mBtnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    int a = mA++;
                    int b = (int) (Math.random() * 100);

                    //创建一个tv,添加到LinearLayout中
                    TextView tv = new TextView(ServiceActivity.this);
                    tv.setText(a + " + " + b + " = caculating ...");
                    tv.setId(a);
                    mLyContainer.addView(tv);
                    Message msgFromClient = Message.obtain(null, MSG_SUM, a, b);
                    msgFromClient.replyTo = mMessenger;
                    if (isConn)
                    {
                        //往服务端发送消息
                        mService.send(msgFromClient);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    private void bindServiceInvoked()
    {
        Intent intent = new Intent(this, MyService.class);
        //intent.setAction("com.zhy.aidl.calc");
        //intent.setPackage("com.example.txbydev4.helloword");
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
        Log.e(TAG, "bindService invoked !");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unbindService(mConn);
        Log.d("222222", "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("222222", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("222222", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("222222", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("222222", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("222222", "onStop");
    }
}
