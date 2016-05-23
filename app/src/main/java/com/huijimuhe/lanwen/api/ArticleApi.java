package com.huijimuhe.lanwen.api;

import com.huijimuhe.lanwen.core.AppContext;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class ArticleApi {

    public static void getPage(String page,TextHttpResponseHandler responseHandler) {
        String url = BaseApi.GET_ARTICLES+page;
        RequestParams params = new RequestParams();
        BaseApi.get(url, params, responseHandler);
    }

    public static void postWork(String text, String title,String article_id, TextHttpResponseHandler responseHandler) {

        String url = BaseApi.POST_WORK;
        RequestParams params = new RequestParams();
        params.put("text", text);
        params.put("title", title);
        params.put("article_id", article_id);
        BaseApi.post(url, params, responseHandler);
    }

    public static void getOssToken(TextHttpResponseHandler responseHandler) {
        String url = BaseApi.GET_TOKEN;
        RequestParams params = new RequestParams();
        BaseApi.get(url, params, responseHandler);
    }

}
