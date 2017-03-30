package com.lib.wlib.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib.jlib.util.ObjUtil;

/**
 * Created by txbydev4 on 17/1/23.
 */

public class ViewHolder {
    private Context context;
    private SparseArray<View> views;
    private View convertView;
    private int position;

    private ViewHolder(Context context, ViewGroup parent, int layout, int position) {
        this.context = context;
        views = new SparseArray<View>();
        convertView = LayoutInflater.from(context).inflate(layout, parent, false);
        convertView.setTag(this);
    }

    public View getConvertView() {
        return convertView;
    }

    public int getPosition() {
        return position;
    }

    public static ViewHolder getInstance(Context context, View convertView, ViewGroup parent, int position, int layout) {
        if (ObjUtil.isEmpty(convertView)) {
            return new ViewHolder(context, parent, layout, position);
        } else {
            return (ViewHolder) convertView.getTag();
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (ObjUtil.isEmpty(view)) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T)view;
    }

    public ViewHolder setTextView(int viewId, String value) {
        TextView textView = getView(viewId);
        textView.setText(value);
        return this;
    }

    public ViewHolder setTextView(int viewId, int stringId) {
        TextView textView = getView(viewId);
        textView.setText(context.getText(stringId));
        return this;
    }

}
