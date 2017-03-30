package com.lib.wlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lib.jlib.util.ObjUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txbydev4 on 17/1/23.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected List<T> items;
    protected Context context;
    protected int layout;
    protected LayoutInflater inflater;

    public CommonAdapter(Context context, List<T> items, int layout) {
        this.items = ObjUtil.isEmpty(items) ? new ArrayList<T>() : items;
        this.context = context;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getInstance(context, convertView, parent, position, layout);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item);
}
