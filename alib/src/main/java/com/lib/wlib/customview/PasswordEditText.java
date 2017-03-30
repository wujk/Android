package com.lib.wlib.customview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.lib.wlib.R;
import com.lib.wlib.frame.utils.DisplayUtils;


/**
 * Created by txbydev4 on 17/3/29.
 */

public class PasswordEditText extends AppCompatEditText {
    private Drawable drawable = null;
    int rightX = 0;
    boolean show  = true;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        drawable = context.getResources().getDrawable(R.drawable.arrow);
        setCompoundDrawablePadding(30);
        setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        Log.d("00000", getPaddingLeft()+"==="+ DisplayUtils.dip2px(context,10f));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            rightX = right;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float touchX = event.getX();
                if (touchX >= rightX - drawable.getIntrinsicHeight()) {
                    setTransformationMethod(show ? PasswordTransformationMethod.getInstance():HideReturnsTransformationMethod.getInstance());
                    show = !show;
                    setSelection(getText().toString().length());
                    return true;
                }

        }
        return super.onTouchEvent(event);
    }

}
