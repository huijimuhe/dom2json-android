package com.huijimuhe.lanwen.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huijimuhe.lanwen.R;
import com.huijimuhe.lanwen.adapter.ArticleAdapter;
import com.huijimuhe.lanwen.adapter.SectionAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.core.AppContext;
import com.huijimuhe.lanwen.model.ArticleBean;
import com.huijimuhe.lanwen.model.SectionBean;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {

    private ArrayList<SectionBean> mDataset;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    protected AbstractRenderAdapter mAdapter;

    public static Intent newIntent(String text) {
        Intent intent = new Intent(AppContext.getInstance(),
                ArticleActivity.class);
        intent.putExtra("text",text);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        String mJsonStr=getIntent().getStringExtra("text");
        loadData(mJsonStr);

        initList();
    }

    private void initList() {

        //Adapter
        mAdapter = new SectionAdapter(mDataset);
        mAdapter.setOnItemClickListener(new AbstractRenderAdapter.onItemClickListener() {
                                            @Override
                                            public void onItemClick(View view, int postion, int type) {

                                            }
                                        }
        );

        //recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadData(String json){
        mDataset=new ArrayList<>();
        Gson gson=new Gson();
        mDataset=gson.fromJson(json, new TypeToken<ArrayList<SectionBean>>() {}.getType());
    }
}
