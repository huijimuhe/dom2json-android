package com.huijimuhe.lanwen.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Huijimuhe on 2016/5/23.
 * This is a part of Lanwen
 * enjoy
 */
public class ArticleBean implements Parcelable {

    /**
     * id : 2
     * title : fdfd
     * text : [{"object":"text1","type":2},{"object":"http:\/\/gitwiduu.u.qiniudn.com\/lanwen_14637283563254.jpg","type":3}]
     * author : 作者名称
     * created_at : 2016-05-20 15:12:38
     * updated_at : 2016-05-20 15:12:38
     */

    private int id;
    private String title;
    private String text;
    private String author;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.author);
    }

    public ArticleBean() {
    }

    private ArticleBean(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.text = in.readString();
        this.author = in.readString();
    }

    public static final Parcelable.Creator<ArticleBean> CREATOR = new Parcelable.Creator<ArticleBean>() {
        public ArticleBean createFromParcel(Parcel source) {
            return new ArticleBean(source);
        }

        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };
}
