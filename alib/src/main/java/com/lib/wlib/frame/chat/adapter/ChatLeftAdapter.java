package com.lib.wlib.frame.chat.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lib.wlib.R;
import com.lib.wlib.frame.chat.model.ChatItem;
import com.lib.wlib.frame.chat.model.ChatLeft;
import com.lib.wlib.frame.listview.abs.OneTypeAdapter;
import com.lib.wlib.frame.listview.inter.IAdapter;
import com.lib.wlib.frame.utils.Tools;

public class ChatLeftAdapter extends OneTypeAdapter<ChatItem> {

	public ChatLeftAdapter(Context context) {
		super(context);
	}

	@Override
	public ChatItem getViewHolder() {
		return new ChatItem();
	}

	@Override
	public void initViewHolder(ChatItem t, View view) {
		t.textView = (TextView) view.findViewById(R.id.chat_l);
	}

	@Override
	public int getLayoutId() {
		return R.layout.chat_l_item;
	}

	@Override
	public void showView(ChatItem t, IAdapter iAdapter) {
		ChatLeft chat = (ChatLeft) iAdapter;
		t.textView.setText(null);
		Tools.replaceFace(context, t.textView, chat.getContent());
	}

}
