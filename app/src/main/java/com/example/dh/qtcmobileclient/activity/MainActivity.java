package com.example.dh.qtcmobileclient.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dh.qtcmobileclient.R;
import com.example.dh.qtcmobileclient.fragment.HomeFragment;
import com.example.dh.qtcmobileclient.fragment.MsgFragment;
import com.example.dh.qtcmobileclient.fragment.News_Fragment;
import com.example.dh.qtcmobileclient.fragment.UserFragment;
import com.example.dh.qtcmobileclient.fragment.ZuiXinFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadio;
    private Fragment showfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void initview() {
        mRadio = (RadioGroup) findViewById(R.id.main_radio);
        mRadio.setOnCheckedChangeListener(this);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        showfragment = new HomeFragment();
        transaction.add(R.id.fragment_parent, showfragment, HomeFragment.TAG);
        transaction.commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (id) {
            case R.id.btn_menubar_home:
                transaction.hide(showfragment);
                showfragment=manager.findFragmentByTag(HomeFragment.TAG);
                if (showfragment!=null) {
                    transaction.show(showfragment);
                }else {
                    showfragment=new HomeFragment();
                    transaction.add(R.id.fragment_parent, showfragment, HomeFragment.TAG);
                }
                break;
            case R.id.btn_menubar_news:
                transaction.hide(showfragment);
                showfragment=manager.findFragmentByTag(News_Fragment.TAG);
                if (showfragment!=null) {
                    transaction.show(showfragment);
                }else {
                    showfragment=new News_Fragment();
                    transaction.add(R.id.fragment_parent, showfragment, News_Fragment.TAG);
                }
                break;
            case R.id.btn_menubar_zuinew:
                transaction.hide(showfragment);
                showfragment=manager.findFragmentByTag(ZuiXinFragment.TAG);
                if (showfragment!=null) {
                    transaction.show(showfragment);
                }else {
                    showfragment=new ZuiXinFragment();
                    transaction.add(R.id.fragment_parent, showfragment, ZuiXinFragment.TAG);
                }
                break;
            case R.id.btn_menubar_msg:
                transaction.hide(showfragment);
                showfragment=manager.findFragmentByTag(MsgFragment.TAG);
                if (showfragment!=null) {
                    transaction.show(showfragment);
                }else {
                    showfragment=new MsgFragment();
                    transaction.add(R.id.fragment_parent, showfragment, MsgFragment.TAG);
                }
                break;
            case R.id.btn_menubar_user:
                transaction.hide(showfragment);
                showfragment=manager.findFragmentByTag(UserFragment.TAG);
                if (showfragment!=null) {
                    transaction.show(showfragment);
                }else {
                    showfragment=new UserFragment();
                    transaction.add(R.id.fragment_parent, showfragment, UserFragment.TAG);
                }
                break;
        }
        transaction.commit();
    }

    //双击退出
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
