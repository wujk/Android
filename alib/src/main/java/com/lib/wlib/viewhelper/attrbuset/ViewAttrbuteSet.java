package com.lib.wlib.viewhelper.attrbuset;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;


public class ViewAttrbuteSet implements Setting{
	public int width; //view的宽度
	public int height; //view的高度
	public int padding; //view的内部填充
	public int paddingLeft;
	public int paddingTop;
	public int paddingRight;
	public int paddingBottom;
	public int margin; //view的内部填充
	public int marginLeft;
	public int marginTop;
	public int marginRight;
	public int marginBottom;
	public int gravity=Gravity.CENTER;
	public int bg_resourceId; //背景资源id
	public int bg_color = Color.TRANSPARENT; //背景颜色
	public String onclick;
	
	@Override
	public void setViewAttrbuteSet(Context context, View view){
		MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
		if(mlp == null){
			mlp = new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}
		if(width != 0){
			mlp.width = width;
		}
		if(height != 0){
			mlp.height = height;
		}
		if(padding != 0){
			view.setPadding(padding, padding, padding, padding);
		}
		if(paddingLeft != 0){
			view.setPadding(paddingLeft, padding, padding, padding);
		}
		if(paddingTop != 0){
			view.setPadding(padding, paddingTop, padding, padding);
		}
		if(paddingRight != 0){
			view.setPadding(padding, padding, paddingRight, padding);
		}
		if(paddingBottom != 0){
			view.setPadding(padding, padding, padding, paddingBottom);
		}
		if(margin != 0){
			mlp.setMargins(margin, margin, margin, margin);
		}
		if(marginLeft != 0){
			mlp.leftMargin = marginLeft;
		}
		if(marginRight != 0){
			mlp.rightMargin = marginRight;
		}
		if(marginTop != 0){
			mlp.topMargin = marginTop;
		}
		if(marginBottom != 0){
			mlp.bottomMargin = marginBottom;
		}
		if(view instanceof TextView){
			((TextView)view).setGravity(gravity);
		}
		if(bg_color != 0){
			view.setBackgroundColor(bg_color);
		}
		if(bg_resourceId != 0){
			view.setBackgroundResource(bg_resourceId);
		}
		
	}

	
}
