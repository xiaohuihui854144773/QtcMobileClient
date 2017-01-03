package com.example.dh.qtcmobileclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.qtcmobileclient.R;
import com.example.dh.qtcmobileclient.adapter.FgVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DH on 2017/1/1.
 */
public class News_Fragment extends BaseFragment {

    public static final String TAG = News_Fragment.class.getSimpleName();
    private TabLayout mNewsTab;
    private ViewPager mNewsVp;
    private String []tabtile={"重要新闻","通知公告","综合新闻","媒体看职院"};
    private List<Fragment>fglist;
    private FgVpAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_news, container, false);
        initview();
        return layout;
    }

    private void initview() {
        //初始化视图
        mNewsTab = (TabLayout) layout.findViewById(R.id.news_tab);
        mNewsVp = (ViewPager) layout.findViewById(R.id.news_vp);
        adapter = new FgVpAdapter(getChildFragmentManager(),null);
        mNewsVp.setAdapter(adapter);

        mNewsVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mNewsTab));
        mNewsTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mNewsVp));
        AddTabTitle();
        setpview();
    }

    private void AddTabTitle() {
        fglist=new ArrayList<>();
        for (int i = 0; i < tabtile.length; i++) {
            mNewsTab.addTab(mNewsTab.newTab().setText(tabtile[i]));
            fglist.add(new NewsChildFragment());
        }
        adapter.updateRes(fglist);
    }

    private void setpview() {
        //为视图添加数据
    }
}
