package net.yr.mvvm.app.base;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import net.yr.mvvm.app.R;
import net.yr.mvvm.app.util.GlobalStation;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author: xiewenliang
 * @Filename:
 * @Description:
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/8/23 15:45
 */
public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        initClient();
    }

    /**
     * 初始化网络
     */
    private void initClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        okhttp3.OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        mBuilder.connectTimeout(10000L, TimeUnit.MILLISECONDS);
        mBuilder.readTimeout(10000L, TimeUnit.MILLISECONDS);
        if (GlobalStation.isDebugModel) {
            mBuilder.addInterceptor(new LoggerInterceptor(getString(R.string.app_name)));
        }
        OkHttpClient okHttpClient = mBuilder.build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
