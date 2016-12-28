package com.example.dh.qtcmobileclient;

import android.app.Application;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by DH on 2016/12/28.
 */
public class QtcApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络数据工具
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        OkHttpUtils.initClient(client);
        //初始化毕加索图片加载工具
        Picasso builder = new Picasso.Builder(this)
                //调试信息，上线时改为false
                .loggingEnabled(true)
                //图片质量
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                //调试标记
                .indicatorsEnabled(true)
                .build();
        //xutils初始化
        x.Ext.init(this);
        //上线时改为false
        x.Ext.setDebug(true);
    }
}
