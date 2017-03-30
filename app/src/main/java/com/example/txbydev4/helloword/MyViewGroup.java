package com.example.txbydev4.helloword;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by txbydev4 on 17/3/21.
 */

public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed)
            layout(l,t,r,b);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyViewGroup++++++++", "dispatchTouchEvent");
        //return super.dispatchTouchEvent(ev);
        //return true;
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("MyViewGroup++++++++","onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
        //return true;
        //return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyViewGroup++++++++","onTouchEvent");
        return super.onTouchEvent(event);
        //return true;
        //return false;
    }
}
