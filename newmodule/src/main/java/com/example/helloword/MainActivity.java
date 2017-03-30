package com.example.helloword;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.txbydev4.helloword.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface face;
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("========");
            face = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            face = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    Toast.makeText(MainActivity.this, ""+ face.add(1,2), Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.txbydev4.helloword", "com.example.txbydev4.helloword.services.AidlService"));
        bindService(intent, con, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(con);
    }
}
