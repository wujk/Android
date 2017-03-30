package com.lib.wlib.viewhelper.attrbuset;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

public class EditViewAttrbuteSet extends TextViewAttrbuSet{
	
	public int hint_resource;
	public String hint;
	public String textChange;
	public int inputType;
	public String focusChange;
	public String editAction;
	
	@Override
	public void setViewAttrbuteSet(Context context, View view) {
		super.setViewAttrbuteSet(context, view);
		if(hint_resource != 0){
			((EditText)view).setHint(hint_resource);
		}
		if(hint != null){
			((EditText)view).setHint(hint);
		}
		if(inputType != 0){
			((EditText)view).setInputType(inputType);
		}
	}
	
	
	
	
}
