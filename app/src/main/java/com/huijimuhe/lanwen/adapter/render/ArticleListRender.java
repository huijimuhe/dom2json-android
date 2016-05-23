package com.huijimuhe.lanwen.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huijimuhe.lanwen.R;
import com.huijimuhe.lanwen.adapter.base.AbstractRender;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractViewHolder;
import com.huijimuhe.lanwen.model.ArticleBean;

/**
 * Created by Huijimuhe on 2016/5/23.
 * This is a part of Lanwen
 * enjoy
 */
public class ArticleListRender extends AbstractRender {

    private ViewHolder mHolder;
    private AbstractRenderAdapter mAdapter;

    public ArticleListRender(ViewGroup parent, AbstractRenderAdapter adapter) {
        this.mAdapter = adapter;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_article, parent, false);
        this.mHolder = new ViewHolder(v, adapter);
    }

    @Override
    public void bindData(int position) {
        ArticleBean model = (ArticleBean) mAdapter.getItem(position);
        mHolder.mIvTitle.setText(model.getTitle());
    }

    @Override
    public AbstractViewHolder getReusableComponent() {
        return this.mHolder;
    }

    public class ViewHolder extends AbstractViewHolder {
        public TextView mIvTitle;

        public ViewHolder(View v, final AbstractRenderAdapter adapter) {
            super(v);
            mIvTitle = (TextView) v.findViewById(R.id.item_text);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnItemClickListener.onItemClick(v, getLayoutPosition(), AbstractRenderAdapter.BTN_CLICK_ITEM);
                }
            });
        }
    }
}
