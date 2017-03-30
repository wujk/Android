package com.example.txbydev4.helloword.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;

/**
 * Created by txbydev4 on 17/3/24.
 */

public class MyServiceTest extends Service {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println(msg.obj);
            Message msgToClient = Message.obtain(msg);//返回给客户端的消息

            try{
                Thread.sleep(2000);
                Bundle bundle = new Bundle();
                bundle.putString("msg", "who are you");
                msgToClient.setData(bundle);
                msg.replyTo.send(msgToClient);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };

    private Messenger messenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
