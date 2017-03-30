package com.lib.wlib.customview;

import java.lang.reflect.Method;


import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lib.wlib.R;
import com.lib.wlib.viewhelper.attrbuset.EditViewAttrbuteSet;


public class SearchView extends FrameLayout implements CustomView, OnClickListener{
	private EditViewAttrbuteSet eidtattrbuteSet = new EditViewAttrbuteSet();
	private Context context;
	private EditText editText;
	private ImageView cancel;
	private Class<?> c;
	private Object o;

	public SearchView(Context context) {
		this(context, null);
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.search_view);
		eidtattrbuteSet.textChange = a.getString(R.styleable.search_view_textChange);
		a.recycle();
		initView(context);
	}

	@Override
	public void initView(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.searchview, null, false);
		editText  = (EditText) view.findViewById(R.id.search_view);
		cancel = (ImageView) view.findViewById(R.id.iv_right);
		addTextChangeListener();
		cancel.setOnClickListener(this);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(view);
	}
	
	/**
	 * 通过配置文件为edittext设置文本改变监听器
	 */
	private void addTextChangeListener(){
		if(eidtattrbuteSet.textChange == null || "".equals(eidtattrbuteSet.textChange)){
			return;
		}
		try {
			c = Class.forName(eidtattrbuteSet.textChange);
			o = c.getConstructor(Context.class).newInstance(context);
			addTextChangeListener((TextWatcher)o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过代码为edittext设置文本改变监听器
	 */
	public void addTextChangeListener(TextWatcher  o){
			editText.addTextChangedListener(o);
	}
	
	public void setData(Object object){
		setData(object.getClass(), object);
	}
	
	public void setData(Class<?> classType, Object object){
		try {
			Method method = c.getMethod("setData", new Class[]{classType});
			if(method != null){
				method.invoke(o, new Object[]{object});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View arg0) {
		editText.setText(null);
	}

}
