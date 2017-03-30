package com.lib.wlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.jlib.util.ObjUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by txbydev4 on 17/3/2.
 */

public abstract class CommonRecyleAdapter<T> extends RecyclerView.Adapter<RecyleViewHolder> {
    protected List<T> items;
    protected Context context;
    protected int layout;
    protected LayoutInflater inflater;
    protected OnItemClickLitener itemClickLitener;
    protected OnItemLongClickListener itemLongClickLitener;

    public void addItemClickListener(OnItemClickLitener itemClickLitener) {
        this.itemClickLitener = itemClickLitener;
    }

    public void addItemLongClickLitener(OnItemLongClickListener itemLongClickLitener) {
        this.itemLongClickLitener = itemLongClickLitener;
    }

    public CommonRecyleAdapter(Context context, List<T> items, int layout) {
        this.items = ObjUtil.isEmpty(items) ? new ArrayList<T>() : items;
        this.context = context;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyleViewHolder(inflater.inflate(layout, parent, false), context, viewType);
    }

    @Override
    public void onBindViewHolder(final RecyleViewHolder holder, final int position) {
        convert(holder, items.get(position));
        if (!ObjUtil.isEmpty(itemClickLitener)) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickLitener.onItemClick(holder.getItemView(), holder.getLayoutPosition());
                }
            });
        }
        if (!ObjUtil.isEmpty(itemLongClickLitener)) {
            holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickLitener.onItemLongClick(holder.getItemView(), holder.getLayoutPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract void convert(RecyleViewHolder holder, T item);
}
