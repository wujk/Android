package com.lib.wlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.lib.jlib.util.ObjUtil;

/**
 * Created by txbydev4 on 17/3/2.
 */

public class RecyleViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View itemView;
    private Context context;
    private int viewType;

    public RecyleViewHolder(View itemView, Context context, int viewType) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        views = new SparseArray<View>();
    }

    public View getItemView() {
        return itemView;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (ObjUtil.isEmpty(view)) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T)view;
    }

    public RecyleViewHolder setTextView(int viewId, String value) {
        TextView textView = getView(viewId);
        textView.setText(value);
        return this;
    }

    public RecyleViewHolder setTextView(int viewId, int stringId) {
        TextView textView = getView(viewId);
        textView.setText(context.getText(stringId));
        return this;
    }
}
