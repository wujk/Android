package com.lib.wlib.frame.listview.abs;

import java.util.List;

import android.content.Context;

import com.lib.wlib.frame.listview.model.ImageModel;
import com.lib.wlib.frame.utils.ImageTool;

public abstract class ImageAdapter<T, V extends ImageModel> extends BaseAdapterTool<T, V>{

	public ImageAdapter(Context context, List<V> list) {
		super(context, list);
	}
	
	public void clearImage(){
		for (int i = 0; i < list.size(); i++) {
			V v = list.get(i);
			int imageId_1 = ((ImageModel)v).imageId_1;
			int imageId_2 = ((ImageModel)v).imageId_2;
			int imageId_3 = ((ImageModel)v).imageId_3;
			if(imageId_1 != 0){
				ImageTool.removeCache(imageId_1);
			}
			if(imageId_2 != 0){
				ImageTool.removeCache(imageId_2);
			}
			if(imageId_3 != 0){
				ImageTool.removeCache(imageId_3);
			}		
		}
	}	

}
