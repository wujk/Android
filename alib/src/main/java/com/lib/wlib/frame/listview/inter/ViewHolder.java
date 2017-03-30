package com.lib.wlib.frame.listview.inter;

import android.view.View;

public interface ViewHolder<T> {
	
	public  T getViewHolder();
	
	public  void initViewHolder(T t, View view);
      
}
