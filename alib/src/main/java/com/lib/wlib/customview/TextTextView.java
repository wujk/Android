package com.lib.wlib.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lib.wlib.R;
import com.lib.wlib.viewhelper.attrbuset.TextViewAttrbuSet;


public class TextTextView extends FrameLayout {
	private Context context;
	private TextViewAttrbuSet textvalueleft = new TextViewAttrbuSet();
	private TextView textViewleft;
	private EditText editText;
	private Class<?> click, textChange, focusChange;
	private Object o1, o2, o3;
	private TextView textViewright;

	public TextTextView(Context context) {
		this(context, null);
	}

	public TextTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a= context.obtainStyledAttributes(attrs, R.styleable.all_myview);
		textvalueleft.textId = a.getResourceId(R.styleable.all_myview_text, 0);
		textvalueleft.text = a.getString(R.styleable.all_myview_text);
		textvalueleft.gravity = Gravity.LEFT;
		a.recycle();
		initView(context);
	}

	@SuppressLint("InflateParams") 
	private void initView(Context context) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(R.layout.text_text_view, null);
		textViewleft = (TextView) view.findViewById(R.id.title);
		textvalueleft.setViewAttrbuteSet(context, textViewleft);
		textViewright = (TextView) view.findViewById(R.id.content);
		addView(view);
	}
	
	


	public TextView getTextViewleft() {
		return textViewleft;
	}

	public TextView getTextViewright() {
		return textViewright;
	}

}
