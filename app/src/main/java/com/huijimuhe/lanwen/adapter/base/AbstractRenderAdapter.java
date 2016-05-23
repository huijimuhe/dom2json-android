package com.huijimuhe.lanwen.adapter.base;

import android.annotation.TargetApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRenderAdapter<T> extends RecyclerView.Adapter<AbstractViewHolder> {

    public static final int BTN_CLICK_ITEM = 0;

    public ArrayList<T> mDataset;
    public onItemClickListener mOnItemClickListener;
    protected View mHeaderView;

    @TargetApi(4)
    public AbstractViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDataset.size() : mDataset.size() + 1;
    }

    public List<T> getList() {
        return this.mDataset;
    }

    public int getRealPosition(int position) {
        return mHeaderView == null ? position : position - 1;
    }

    public T getItem(int position) {
        return mDataset.get(getRealPosition(position));
    }

    public void setOnItemClickListener(onItemClickListener l) {
        mOnItemClickListener = l;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int postion, int type);
    }

    public void setHeaderView(View view) {
        mHeaderView = view;
    }

    public View getHeaderView() {
        return mHeaderView;
    }
}
