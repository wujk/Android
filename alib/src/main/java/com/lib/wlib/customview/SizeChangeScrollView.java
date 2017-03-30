package com.lib.wlib.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class SizeChangeScrollView extends ScrollView {
	
	private SizeListener listener;

	public void setListener(SizeListener listener) {
		this.listener = listener;
	}

	public SizeChangeScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
        int i = h - oldh;
        if(i > 200){
        	listener.show(true);
        }
        if(i < -200){
        	listener.show(false);
        }
	}
	
	public interface SizeListener{
		 
		public void show(boolean isShow);
	}

}
