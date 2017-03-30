package com.lib.wlib.viewhelper.attrbuset;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class ImageViewAttrbuSet extends ViewAttrbuteSet {
	public int imageId; //图片资源id
	
    @Override
    public void setViewAttrbuteSet(Context context, View view) {
    	super.setViewAttrbuteSet(context, view);
    	if(imageId != 0){
    		((ImageView)view).setImageResource(imageId);
    	}
    }
    
    
}
