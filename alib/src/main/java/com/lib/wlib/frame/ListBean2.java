package com.lib.wlib.frame;


import com.lib.wlib.frame.listview.inter.IAdapter;
import com.lib.wlib.frame.listview.inter.IProvider;

public class ListBean2 implements IAdapter {
	String name;
	int photo;
	public ListBean2(String name, int photo) {
		super();
		this.name = name;
		this.photo = photo;
	}

	@Override
	public Class<? extends IProvider> getProviderClass() {

		return ListAdapter2.class;
	}

}
