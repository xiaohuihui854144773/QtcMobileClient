package com.example.dh.qtcmobileclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by DH on 2016/12/28.
 */
public class ConfigMsg {
    public static final String ZYXW_URL="http://www.qtc.edu.cn/qzxw/zyxw.htm";
    public static final String BASE_URL = "http://www.qtc.edu.cn/";
    public static final String APP_ID = "com.example.dh.qtcmobileclient";
    public static final String APPKEY_ISLOGIN = "login";
    public static final String LOGIN_WEB = "http://jw.qtc.edu.cn/st/login.aspx";

    public static boolean getIsLogin(Context context) {
        return context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).getBoolean(APPKEY_ISLOGIN, false);
    }

    public void EditIsLogin(Context context, boolean islogin) {
        SharedPreferences.Editor edit = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        edit.putBoolean(APPKEY_ISLOGIN, islogin);
        edit.apply();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
