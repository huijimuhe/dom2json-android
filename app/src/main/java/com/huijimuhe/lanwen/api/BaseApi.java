package com.huijimuhe.lanwen.api;

import com.huijimuhe.lanwen.R;
import com.huijimuhe.lanwen.core.AppContext;
import com.huijimuhe.lanwen.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class BaseApi {

    public static final String URL_DNS ="http://192.168.0.104/app-lanwen/";
    public static final String GET_ARTICLES =URL_DNS+ "articlelist/";
    public static final String GET_WORKS =URL_DNS+ "worklist/";
    public static final String GET_TOKEN =URL_DNS+ "token";
    public static final String POST_WORK =URL_DNS+ "postWork";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (!AppContext.getInstance().isConnected()) {
            ToastUtils.show(AppContext.getInstance(), "网络出错");
            return;
        }
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        if (!AppContext.getInstance().isConnected()) {
            ToastUtils.show(AppContext.getInstance(), "网络出错");
            return;
        }
        client.post(url, params, responseHandler);
    }
}
