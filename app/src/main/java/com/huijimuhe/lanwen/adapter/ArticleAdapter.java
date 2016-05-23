package com.huijimuhe.lanwen.adapter;

import android.annotation.TargetApi;
import android.view.ViewGroup;

import com.huijimuhe.lanwen.adapter.base.AbstractRender;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractViewHolder;
import com.huijimuhe.lanwen.adapter.render.ArticleListRender;
import com.huijimuhe.lanwen.model.ArticleBean;

import java.util.ArrayList;

public class ArticleAdapter extends AbstractRenderAdapter<ArticleBean> {

    public ArticleAdapter(ArrayList<ArticleBean> statues) {
        this.mDataset = statues;
    }

    @TargetApi(4)
    public AbstractViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //header view 的判断
        AbstractViewHolder holder = super.onCreateViewHolder(viewGroup, viewType);
        if (holder != null) {
            return holder;
        }

        ArticleListRender head = new ArticleListRender(viewGroup, this);
        AbstractViewHolder headHolder = head.getReusableComponent();
        headHolder.itemView.setTag(android.support.design.R.id.list_item, head);
        return headHolder;
    }

    @TargetApi(4)
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        AbstractRender render = (AbstractRender) holder.itemView.getTag(android.support.design.R.id.list_item);
        render.bindData(position);
    }

}
