package com.lib.wlib.viewhelper.attrbuset;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class TextViewAttrbuSet extends ViewAttrbuteSet {
	public float textSize; // 字体大小
	public int textColor_resourceId; // 字体颜色资源id
	public int textColor_color = Color.BLACK; // 字体颜色十六进制默认黑色0x000000
	public int textId; // 文字资源id
	public String text; // 文字字符串
	public int drawableLeft; // 文字左边图片的资源id
	public int drawableRight;
	public int drawableTop;
	public int drawableBottom;

	@Override
	public void setViewAttrbuteSet(Context context, View view) {
		super.setViewAttrbuteSet(context, view);
		if(textSize != 0){
			((TextView)view).setTextSize(textSize);
		}
		if(textColor_resourceId != 0){
			textColor_color = context.getResources().getColor(textColor_resourceId);
		}
		((TextView)view).setTextColor(textColor_color);
		if(textId != 0){
			((TextView)view).setText(context.getString(textId));
		}
		if(text != null){
			((TextView)view).setText(text);
		} 
		((TextView)view).setCompoundDrawablePadding(2);
		((TextView)view).setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
		
	}
	
	

}
