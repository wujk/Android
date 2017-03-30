package com.lib.wlib.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lib.wlib.R;


/**
 * 点击动画
 * @author wjk
 *
 */
public class ClickEffectView extends View {
	private Paint paint;
	private float x, y,radius;
	private boolean isDraw = false;

	public ClickEffectView(Context context) {
		super(context);
		init();
	}

	public ClickEffectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.MyView);
		radius=a.getDimension(R.styleable.MyView_radius, 20);
		a.recycle();
		init();
	}

	private void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	public void setXY(float x, float y) {
		this.x = x;
		this.y = y;
		isDraw = true;
	}

	public void recyle() {
		isDraw = false;
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (isDraw) {
			paint.setColor(Color.RED);
		} else {
			paint.setColor(Color.TRANSPARENT);
		}
		canvas.drawCircle(x,y, radius, paint);
	}

}
