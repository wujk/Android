package com.lib.wlib.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lib.wlib.R;
import com.lib.wlib.frame.utils.Tools;
import com.lib.wlib.viewhelper.attrbuset.EditViewAttrbuteSet;
import com.lib.wlib.viewhelper.attrbuset.TextViewAttrbuSet;


public class EditTextView extends FrameLayout {
	private Context context;
	private TextViewAttrbuSet textValue = new TextViewAttrbuSet();
	private EditViewAttrbuteSet editValue = new EditViewAttrbuteSet();
	private TextView textView;
	private EditText editText;
	private Class<?> click, textChange, focusChange;
	private Object o1, o2, o3;

	public EditTextView(Context context) {
		this(context, null);
	}

	public EditTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a= context.obtainStyledAttributes(attrs, R.styleable.all_myview);
		textValue.textId = a.getResourceId(R.styleable.all_myview_text, 0);
		textValue.text = a.getString(R.styleable.all_myview_text);
		textValue.gravity = Gravity.LEFT;
		editValue.hint_resource = a.getResourceId(R.styleable.all_myview_hint, 0);
		editValue.hint = a.getString(R.styleable.all_myview_hint);
		editValue.textChange = a.getString(R.styleable.all_myview_textchange);
		editValue.onclick = a.getString(R.styleable.all_myview_edit_click);
		editValue.gravity = Gravity.LEFT;
		editValue.focusChange = a.getString(R.styleable.all_myview_focus_change);
		String inputType = a.getString(R.styleable.all_myview_input_type);
		if("password".equals(inputType))
			editValue.inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
		else if("phone".equals(inputType))
			editValue.inputType = InputType.TYPE_CLASS_PHONE;
		else if("number".equals(inputType))
			editValue.inputType = InputType.TYPE_CLASS_NUMBER;
		a.recycle();
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater mInflater = LayoutInflater.from(context);
		View view = mInflater.inflate(R.layout.text_eidt_view, null);
		textView = (TextView) view.findViewById(R.id.title);
		textValue.setViewAttrbuteSet(context, textView);
		editText = (EditText) view.findViewById(R.id.content);
		editValue.setViewAttrbuteSet(context, editText);
		setOnClick();
		setTextWatcher();
		setFocusChange();
		addView(view);
	}
	
	private void setFocusChange() {
		if(!Tools.isEmpty(editValue.focusChange))
			return;
		try {
			focusChange = Class.forName(editValue.focusChange);
			o3 = focusChange.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
			editText.setOnFocusChangeListener((OnFocusChangeListener)o3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setOnClick(){
		if(!Tools.isEmpty(editValue.onclick))
			return;
		try {
			click = Class.forName(editValue.onclick);
			o1 = click.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
			editText.setOnClickListener((OnClickListener)o1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setTextWatcher(){
		if(!Tools.isEmpty(editValue.textChange))
			return;
		try {
			textChange = Class.forName(editValue.textChange);
			o2 = textChange.getDeclaredConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
			editText.addTextChangedListener((TextWatcher)o2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TextView getTextView() {
		return textView;
	}

	public EditText getEditText() {
		return editText;
	}

}
