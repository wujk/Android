package com.lib.wlib.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 禁止滚动GridView
 * @author wjk
 *
 */

public class NoScrollGridView extends GridView {
	
	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}

}
