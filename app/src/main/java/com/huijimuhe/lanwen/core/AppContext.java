package com.huijimuhe.lanwen.core;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import com.huijimuhe.lanwen.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class AppContext extends Application {

    // singleton
    private static AppContext AppContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static AppContext getInstance() {
        return AppContext;
    }

    public void restartApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void loadImg(ImageView v, String url) {
        String absoluteUrl = url;
        Picasso.with(this)
                .load(absoluteUrl)
                .placeholder(R.drawable.ic_upload)
                .error(R.drawable.ic_upload)
                .transform(new CropSquareTransformation())
                .into(v);
    }

    public class CropSquareTransformation implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "monolog";
        }
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager)
                this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 获取META-INF的标签
     *
     * @param key
     * @return
     */
    public String getMetaData(String key) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo.metaData.getString(key);
    }
}