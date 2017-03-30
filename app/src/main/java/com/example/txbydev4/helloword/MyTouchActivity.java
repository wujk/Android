package com.example.txbydev4.helloword;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by txbydev4 on 17/3/21.
 */

public class MyTouchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch);
    }

    // true false 消费
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyTouchActivity++++++++","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
        //return true;
        //return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyTouchActivity++++++++","onTouchEvent");
        //return super.onTouchEvent(event);
        return true;
        //return false;
    }
}
