package com.huijimuhe.lanwen.adapter.render;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huijimuhe.lanwen.R;
import com.huijimuhe.lanwen.adapter.SectionAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractRender;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractViewHolder;
import com.huijimuhe.lanwen.model.SectionBean;

public class TextSectionRender extends AbstractRender {

    private ViewHolder mHolder;
    private AbstractRenderAdapter mAdapter;

    public TextSectionRender(ViewGroup parent, AbstractRenderAdapter adapter) {
        this.mAdapter =adapter;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_text,parent,false);
        this.mHolder=new ViewHolder(v,adapter);
    }

    @Override
    public void bindData(int position) {
        SectionBean model=(SectionBean) mAdapter.getItem(position);
        mHolder.mTvContent.setText(model.getObject());
    }

    @Override
    public AbstractViewHolder getReusableComponent() {
        return this.mHolder;
    }

    public class ViewHolder extends AbstractViewHolder{
        public TextView mTvContent;

        public ViewHolder(View v,final AbstractRenderAdapter adapter) {
            super(v);
            mTvContent = (TextView) v.findViewById(R.id.item_text);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.mOnItemClickListener.onItemClick(v, getLayoutPosition(), SectionAdapter.BTN_CLICK_ITEM);
                }
            });
        }
    }
}
