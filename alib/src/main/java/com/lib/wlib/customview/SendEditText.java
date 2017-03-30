package com.lib.wlib.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.lib.wlib.R;
import com.lib.wlib.viewhelper.attrbuset.EditViewAttrbuteSet;


public class SendEditText extends FrameLayout implements CustomView{
	private EditViewAttrbuteSet eidtattrbuteSet = new EditViewAttrbuteSet();
	private Context context;
	private EditText editText;
	private Button send;
	private Class<?> c, c1;
	private Object o, o1;
	private SendListener listener;
	private String str;

	public SendEditText(Context context) {
		this(context, null);
	}

	public SendEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.all_myview);
		eidtattrbuteSet.textChange = a.getString(R.styleable.all_myview_textchange);
		eidtattrbuteSet.editAction = a.getString(R.styleable.all_myview_edit_action);
		str = a.getString(R.styleable.all_myview_send);
		a.recycle();
		initView(context);
	}

	@SuppressLint("InflateParams")
	@Override
	public void initView(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.send_edittext, null, false);
		editText  = (EditText) view.findViewById(R.id.edittext);
		send = (Button) view.findViewById(R.id.send);
		send.setEnabled(false);
		editText.setSingleLine(false);
		addTextChangeListener();
		send.setOnClickListener(new ClickListener());
		setListener();
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(view);
	}
	
	/**
	 * 通过配置文件为edittext设置文本改变监听器
	 */
	public void addTextChangeListener(){
		if(eidtattrbuteSet.textChange == null || "".equals(eidtattrbuteSet.textChange)){
			addTextChangeListener(new TextChange());
			return;
		}
		try {
			c = Class.forName(eidtattrbuteSet.textChange);
			o = c.getConstructor().newInstance();
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
	
	public void setListener(){
		if(str == null || "".equals(str)){
			return;
		}
		try {
			c1 = Class.forName(str);
			o1 = c1.getConstructor().newInstance();
			setListener((SendListener)o1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setListener(SendListener listener) {
		this.listener = listener;
	}
	
	final class TextChange implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			send.setEnabled(s.length() > 0);
		}
		
	}
	
	final class ClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(listener == null)
				return;
			if(listener.send(context, editText.getText().toString())){
				editText.setText(null);
			}
		}
		
	}
	
	public interface SendListener{
		public boolean send(Context context, String str);
	}

}
