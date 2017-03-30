package com.example.txbydev4.helloword;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;


/**
 * Created by txbydev4 on 17/3/7.
 */

public class MyTextView extends AppCompatTextView {
    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView, defStyleAttr, 0);
        int color = a.getColor(R.styleable.TextView_color_view, 0);
        float size = a.getDimension(R.styleable.TextView_size_view, 12);
        Log.d("=======", color+"=====" + size);
        a.recycle();
    }

}
