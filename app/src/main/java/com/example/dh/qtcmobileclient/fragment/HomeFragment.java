package com.example.dh.qtcmobileclient.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

import com.example.dh.qtcmobileclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    public static final String TAG = HomeFragment.class.getSimpleName();
    private ViewPager vp;
    private LinearLayout ll_points;
    private ListView Home_lv;
    private int[] images;
    private List<ImageView> list;// 存放要显示在ViewPager对象中的所有ImageView对象
    private int prevPosition = 0;
    private int prevIndex = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0x200) {
                // viewpager要切换到下一张图片
                int currentIndex = vp.getCurrentItem();
                currentIndex++;
                vp.setCurrentItem(currentIndex % list.size());
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_home, container, false);
        initview();
        // 初始化资源数组
        initRes();
        // 初始化集合，用一广告显示
        initList();
        return layout;
    }

    private void initList() {
        list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(images[i]);
            list.add(imageView);
            View view = new View(getActivity());
            view.setBackgroundResource(R.drawable.dot_normal);
            // 获取屏幕信息
            DisplayMetrics metrics = new DisplayMetrics();
            float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, metrics);
            float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 20, metrics);
            LinearLayout.LayoutParams params = new LayoutParams((int) width, (int) height);
            params.leftMargin = 5;
            view.setLayoutParams(params);
            // 把这个view加入到容器中
            ll_points.addView(view);
        }
    }

    private void initRes() {
        images = new int[] { R.mipmap.one, R.mipmap.two,R.mipmap.three,R.mipmap.four};
    }

    private void initview() {
        //加载视图】
        vp = (ViewPager) layout.findViewById(R.id.home_vp);
        ll_points = (LinearLayout) layout.findViewById(R.id.home_vpimg_parent);
        Home_lv = (ListView) layout.findViewById(R.id.zongyao_news);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_points.getChildAt(0).setBackgroundResource(R.drawable.dot_enable);
        // 做一个可以给ViewPager使用的adapter;
        ImageAdapter adapter = new ImageAdapter();
        vp.setAdapter(adapter);
        vp.addOnPageChangeListener(this);
        // 启动一个线程进行轮播操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(3000);
                    handler.sendEmptyMessage(0x200);
                }
            }
        }).start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        ll_points.getChildAt(prevIndex).setBackgroundResource(R.drawable.dot_normal);
        // 当前这个
        ll_points.getChildAt(arg0).setBackgroundResource(R.drawable.dot_enable);
        prevIndex = arg0;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ImageAdapter extends PagerAdapter {
        /**
         * 要对需要缓存的对象进行初始化，并把它加载到容器中
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }
        /**
         * PagerAdapter只能缓存三个对象，如果滑动的对象超过了三个，则要及时销毁相应的对象，销毁时会调用这个方法
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
        @Override
        public int getCount() {
            return list.size();
        }
        /**
         * 用于判断显示的是否是同一个对象
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }
}
