package com.example.dh.qtcmobileclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.dh.qtcmobileclient.activity.LoginActivity;
import com.example.dh.qtcmobileclient.activity.MainActivity;
import com.example.dh.qtcmobileclient.utils.ConfigMsg;

public class WelcomeActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGHT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (ConfigMsg.getIsLogin(WelcomeActivity.this)) {
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                }
                //启动意图
                startActivity(intent);
                //结束此界面
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);

    }
}
