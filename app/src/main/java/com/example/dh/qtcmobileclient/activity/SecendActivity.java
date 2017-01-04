package com.example.dh.qtcmobileclient.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dh.qtcmobileclient.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;

public class SecendActivity extends AppCompatActivity {

    private static final String TAG = SecendActivity.class.getSimpleName();
    private String url_2;
    private TextView mTitle;
    private TextView mTime;
    private LinearLayout mImgs;
    private TextView mText;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0x100) {
                for (int i = 0; i < allURl.length; i++) {
                    //有图的话这里有图骗链接
                    ImageView imageView=new ImageView(SecendActivity.this);
                    Picasso.with(imageView.getContext()).load(allURl[i]).into(imageView);
                    // 获取屏幕信息
                    DisplayMetrics metrics = new DisplayMetrics();
                    float width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 350, metrics);
                    float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 350, metrics);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) width, (int) height);
                    params.gravity= Gravity.CENTER_HORIZONTAL;
                    imageView.setLayoutParams(params);
                    // 把这个view加入到容器中
                    mImgs.addView(imageView);
                }
                Random rand = new Random();
                mText.setText(concent);
                mTime.setText(time+rand.nextInt(10)+10);
                mTitle.setText(title);
            }

        }
    };
    private String[] allURl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secend);
        url_2 = getIntent().getStringExtra("url_2");
        initview();
        setpview(url_2);
    }

    private void initview() {
        mTitle = (TextView) findViewById(R.id.secend_title);
        mTime = (TextView) findViewById(R.id.secend_time);
        mImgs = (LinearLayout) findViewById(R.id.img_parent);
        mText = (TextView) findViewById(R.id.secend_text);
    }
    private String concent;
    private String time;
    private String title;
    private void setpview(final String url_2) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Document document = Jsoup.connect(url_2).timeout(10000).get();
                        Elements nei_title = document.select("div.nei_title");
//                    Log.e(TAG, nei_title.text());
                        title = nei_title.text();
                        Element nei_top = document.select("div.nei_top").first();
//                    Log.e(TAG, nei_top.text());
                        time = nei_top.text();
                        Element text = document.select("div#vsb_content_2.nei").first();
                        Elements p1 = text.select("p");
//                    Log.e(TAG, p1.toString());
                        Elements p = text.select("img");
//                    Log.e(TAG, p.toString());
                        allURl=new String[p.size()];
                        for (int i = 0; i < p.size(); i++) {
//                        Log.e(TAG, p.get(i).select("img[src]").attr("abs:src"));
                           allURl[i]=p.get(i).select("img[src]").attr("abs:src");
                        }
                        //这里是文本
//                        Log.e(TAG, p1.text());
                        concent = p1.text();
                        handler.sendEmptyMessage(0x100);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, e.getMessage().toString());
                    }
                }
            }).start();
        }
}
