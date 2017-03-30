package com.lib.wlib.frame;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lib.wlib.R;
import com.lib.wlib.frame.listview.abs.OneTypeAdapter;
import com.lib.wlib.frame.listview.inter.IAdapter;


public class ListAdapter1 extends OneTypeAdapter<ListItem1> {

	@Override
	public ListItem1 getViewHolder() {
		return new ListItem1();
	}

	@Override
	public void initViewHolder(ListItem1 t, View view) {
		t.textView = (TextView) view.findViewById(R.id.text);
		t.imageView = (ImageView) view.findViewById(R.id.image);
	}

	@Override
	public int getLayoutId() {
		return R.layout.list_item;
	}

	@Override
	public void showView(ListItem1 t, IAdapter iAdapter) {
		ListBean bean = (ListBean) iAdapter;
		t.textView.setText(bean.name);
		t.imageView.setImageResource(bean.photo);
	}

	

}
