package com.example.txbydev4.helloword.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by txbydev4 on 17/3/22.
 */

public class MyService extends Service {
    private static final int MSG_SUM = 0x110;

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msgfromClient)
        {
            Toast.makeText(MyService.this, "服务端调用", Toast.LENGTH_SHORT).show();
            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what)
            {
                //msg 客户端传来的消息
                case MSG_SUM:
                    msgToClient.what = MSG_SUM;
                    try
                    {
                        //模拟耗时
                        Thread.sleep(2000);
                        msgToClient.arg2 = msgfromClient.arg1 + msgfromClient.arg2;
                        msgfromClient.replyTo.send(msgToClient);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }

            super.handleMessage(msgfromClient);
        }
    };

    //最好换成HandlerThread的形式
    private Messenger mMessenger = new Messenger(handler);

    @Override
    public IBinder onBind(Intent intent)
    {
        return mMessenger.getBinder();
    }
}
