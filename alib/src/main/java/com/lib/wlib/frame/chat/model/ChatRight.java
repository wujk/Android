package com.lib.wlib.frame.chat.model;

import com.lib.wlib.frame.chat.adapter.ChatRightAdapter;
import com.lib.wlib.frame.listview.inter.IAdapter;
import com.lib.wlib.frame.listview.inter.IProvider;

public class ChatRight implements IAdapter {
	private String content;

	public ChatRight(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public Class<? extends IProvider> getProviderClass() {
		return ChatRightAdapter.class;
	}

}
