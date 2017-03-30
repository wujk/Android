package com.example.txbydev4.helloword;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by txbydev4 on 17/3/21.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("MyView++++++++","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
        //return true;
        //return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyView++++++++","onTouchEvent");
        return super.onTouchEvent(event);
        //return true;
        //return false;
    }
}
