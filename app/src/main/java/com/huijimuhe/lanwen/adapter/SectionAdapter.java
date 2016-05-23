package com.huijimuhe.lanwen.adapter;

import android.annotation.TargetApi;
import android.view.ViewGroup;

import com.huijimuhe.lanwen.adapter.base.AbstractRender;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractViewHolder;
import com.huijimuhe.lanwen.adapter.render.ImageSectionRender;
import com.huijimuhe.lanwen.adapter.render.TextSectionRender;
import com.huijimuhe.lanwen.model.SectionBean;

import java.util.ArrayList;

public class SectionAdapter extends AbstractRenderAdapter<SectionBean> {
    public static final int RENDER_TYPE_TEXT = 0;
    public static final int RENDER_TYPE_IMAGE = 1;

    public static final int BTN_CLICK_ITEM = 101;
    public static final int BTN_CLICK_IMAGE = 102;

    public SectionAdapter(ArrayList<SectionBean> statues) {
        this.mDataset = statues;
    }

    @TargetApi(4)
    public AbstractViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //header view 的判断
        AbstractViewHolder holder = super.onCreateViewHolder(viewGroup, viewType);
        if (holder != null) {
            return holder;
        }

        switch (viewType) {
            case RENDER_TYPE_TEXT: {
                TextSectionRender head = new TextSectionRender(viewGroup, this);
                AbstractViewHolder headHolder=head.getReusableComponent();
                headHolder.itemView.setTag(android.support.design.R.id.list_item,head);
                return headHolder;
            }
            case RENDER_TYPE_IMAGE: {
                ImageSectionRender head = new ImageSectionRender(viewGroup, this);
                AbstractViewHolder headHolder=head.getReusableComponent();
                headHolder.itemView.setTag(android.support.design.R.id.list_item,head);
                return headHolder;
            }
            default:
                return null;
        }
    }

    @TargetApi(4)
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        AbstractRender render = (AbstractRender) holder.itemView.getTag(android.support.design.R.id.list_item);
        render.bindData(position);
    }

    @Override
    public int getItemViewType(int position) {
        int type = getItem(position).getType();
        switch (type) {
            case 2:
                return RENDER_TYPE_TEXT;
            case 3:
                return RENDER_TYPE_IMAGE;
            default:
                return 0;
        }
    }
}
