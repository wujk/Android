package com.lib.wlib.frame;


import com.lib.wlib.frame.listview.inter.IAdapter;
import com.lib.wlib.frame.listview.inter.IProvider;

import java.io.Serializable;

public class ListBean implements IAdapter, Serializable{
	
	private static final long serialVersionUID = 1L;
	String name;
	int photo;
	

	public ListBean(String name, int photo) {
		super();
		this.name = name;
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	@Override
	public Class<? extends IProvider> getProviderClass() {
		
		return ListAdapter1.class;
	}

}
