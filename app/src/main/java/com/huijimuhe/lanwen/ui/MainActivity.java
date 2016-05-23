package com.huijimuhe.lanwen.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huijimuhe.lanwen.R;
import com.huijimuhe.lanwen.adapter.ArticleAdapter;
import com.huijimuhe.lanwen.adapter.base.AbstractRenderAdapter;
import com.huijimuhe.lanwen.api.ArticleApi;
import com.huijimuhe.lanwen.model.ArticleBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOADMORE = 2;
    public static final int STATE_NOMORE = 3;

    private ArrayList<ArticleBean> mDataset;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected LinearLayoutManager mLayoutManager;
    protected AbstractRenderAdapter mAdapter;
    protected int mCurrentPage = 0;
    protected int lastVisibleItem = 0;
    public static int mState = STATE_NORMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
    }

    @Override
    public void onRefresh() {
        if (mState == STATE_REFRESH) {
            return;
        }
        loadData(true);
    }

    private void initList() {
        //swipe refresh layout
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.darker_gray);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //Adapter
        mDataset=new ArrayList<>();
        mAdapter = new ArticleAdapter(mDataset);
       mAdapter.setOnItemClickListener(new AbstractRenderAdapter.onItemClickListener() {
                                           @Override
                                           public void onItemClick(View view, int postion, int type) {
                                               ArticleBean model=(ArticleBean)mAdapter.getItem(postion);
                                               startActivity(ArticleActivity.newIntent(model.getText()));
                                           }
                                       }
       );
        //recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == mAdapter.getItemCount()) {
                    loadData(false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    public void loadData(boolean isStart) {
        if (isStart) {
            mCurrentPage = 0;
        }
        ArticleApi.getPage(String.valueOf(mCurrentPage), dataHandler);
    }

    protected void setSwipeRefreshLoading() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                    mSwipeRefreshLayout.setEnabled(false);
                }
            });
        }
    }

    protected void setSwipeRefreshNormal() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(false);
                    mSwipeRefreshLayout.setEnabled(true);
                }
            });
        }
    }

    TextHttpResponseHandlerEx dataHandler = new TextHttpResponseHandlerEx() {
        @Override
        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
            Log.d("lanwen","networking failed");
        }

        @Override
        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
            Gson gson = new Gson();
            ArrayList<ArticleBean> response = gson.fromJson(responseString, new TypeToken<ArrayList<ArticleBean>>() {}.getType());
            if (response != null && response.size() != 0) {
                mDataset.clear();
                mDataset.addAll(response);
                mAdapter.notifyDataSetChanged();
                mCurrentPage++;
            }
        }
    };

    public class TextHttpResponseHandlerEx extends com.loopj.android.http.TextHttpResponseHandler {
        @Override
        public void onStart() {
            super.onStart();
            mState = STATE_REFRESH;
            setSwipeRefreshLoading();
        }

        @Override
        public void onFinish() {
            super.onFinish();
            mState = STATE_NORMAL;
            setSwipeRefreshNormal();
        }

        @Override
        public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {

        }

        @Override
        public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {

        }
    }
}
