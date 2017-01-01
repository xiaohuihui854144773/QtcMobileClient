package com.example.dh.qtcmobileclient.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.qtcmobileclient.R;
import com.example.dh.qtcmobileclient.utils.ConfigMsg;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String URL = ConfigMsg.LOGIN_WEB;
    private EditText mUserName;
    private EditText mPassWord;
    private TextView mNotLogin;
    private RadioGroup mLoginType;
    ProgressDialog progressDialog;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x100) {
                Toast.makeText(LoginActivity.this, "账号或密码错误...", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        initview();
    }

    private void initview() {
        mUserName = (EditText) findViewById(R.id.et_phone);
        mPassWord = (EditText) findViewById(R.id.et_password);
        mNotLogin = (TextView) findViewById(R.id.tv_register);

        mNotLogin.setOnClickListener(this);
        mNotLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//加下划线
        mNotLogin.getPaint().setAntiAlias(true);//抗锯齿

        mLoginType = (RadioGroup) findViewById(R.id.logintype);
    }

    public void UserLogin(View view) {
        //点击登录后的响应
        if (TextUtils.isEmpty(mUserName.getText().toString().trim()) && TextUtils.isEmpty(mPassWord.getText().toString().trim())) {
            Toast.makeText(LoginActivity.this, "填写不能为空...", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置风格为圆形进度条
            progressDialog.setMessage("正在登陆.....");
            progressDialog.setIndeterminate(true);// 设置进度条是否为不明确
            progressDialog.setCancelable(false);// 设置进度条是否可以按退回键取消
            progressDialog.show();
            // 设置点击进度对话框外的区域对话框不消失
            progressDialog.setCanceledOnTouchOutside(false);
            if (!ConfigMsg.isNetworkAvailable(LoginActivity.this)) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "当前网络不可用...", Toast.LENGTH_SHORT).show();
            } else {
                switch (mLoginType.getCheckedRadioButtonId()) {

                    case R.id.logintype_student:
                        //学生登录
//                        Toast.makeText(LoginActivity.this, "学生", Toast.LENGTH_SHORT).show();
                        StudentLogin();
                        break;
                    case R.id.logintype_teacher:
                        //辅导员登录
                        Toast.makeText(LoginActivity.this, "辅导员", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.logintype_administrator:
                        //管理员登录
                        Toast.makeText(LoginActivity.this, "管理员", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logintype_patriarch:
                        //家长登录
                        Toast.makeText(LoginActivity.this, "家长", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    }

    private void StudentLogin() {
        //学生登录的具体操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String name = mUserName.getText().toString();
                final String pwd = mPassWord.getText().toString();
                HttpClient mHttpClient = new DefaultHttpClient();
                HttpPost mPost = new HttpPost("http://jw.qtc.edu.cn/st/student/left.aspx");
                HttpPost request = new HttpPost(URL);
                request.setHeader("Accept", "text/html, application/xhtml+xml,*/*");
                request.setHeader("Accept-Language", "zh-CN");
                request.setHeader("User-Agent", "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Shuame");
                request.setHeader("Content-Type", "application/x-www-form-urlencoded");
                request.setHeader("Accept-Encoding", "gzip, deflate");
                request.setHeader("Host", "jw.qtc.edu.cn");
                request.setHeader("Connection", "Keep-Alive");
                request.setHeader("Cache-Control", "no-cache");
                List<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("__VIEWSTATE", "/wEPDwUJOTYxNDY3OTc0D2QWAgIBD2QWAgIHDxBkDxYBAgEWAQUJ6L6F5a+85ZGYZGQYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFDUJ1dHRvbl/nmbvpmYZ73fXoRy3hp/BnQssSTGfUwY7GhQ=="));
                params.add(new BasicNameValuePair("txt_卡学号", name));
                params.add(new BasicNameValuePair("txt_密码", pwd));
                params.add(new BasicNameValuePair("Rad_角色", "学生"));
                params.add(new BasicNameValuePair("Button_登陆.x", "49"));
                params.add(new BasicNameValuePair("Button_登陆.y", "31"));
                params.add(new BasicNameValuePair("__VIEWSTATEGENERATOR", "D2D9F043"));
                params.add(new BasicNameValuePair("__EVENTVALIDATION", "/wEWCQKT0r6vDALep8vjCAKu8uE5AvKm2soEAvLJzeAMAvWZ8TkC6fHPnw8CwsjN4QICyKiipgFJEp7O/mRe43nty8A9A2Z69PCCjw=="));
                try {
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    HttpResponse response0 = mHttpClient.execute(request);
                    HttpResponse response1 = mHttpClient.execute(mPost);
                    if (response1.getStatusLine().getStatusCode() == 200) {
                    }
                    String mString = EntityUtils.toString(
                            response1.getEntity(), "utf-8");
                    Document doc = Jsoup.parse(mString);
                    Element eles = doc.getElementById("Ibtn_个人风采");
                    String mSrc = eles.attr("src");
                    Log.e(TAG, "头像: " + "http://jw.qtc.edu.cn/st" + mSrc.substring(2));
                    List<Cookie> cookies = ((DefaultHttpClient) mHttpClient).getCookieStore().getCookies();
                    Log.e(TAG, "4444444444444444444444: " + cookies);
                    if (cookies.isEmpty()) {
                        Log.e(TAG, "cookies********为null: ");
                    }
                    if (cookies.size() < 2) {
                    } else {
                        progressDialog.dismiss();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                    handler.sendEmptyMessage(0x100);
                }
            }
        }).start();

    }

    @Override
    public void onClick(View view) {
        //点击以后登录
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("loginmsg", "");
        startActivity(intent);
        finish();
    }
}
