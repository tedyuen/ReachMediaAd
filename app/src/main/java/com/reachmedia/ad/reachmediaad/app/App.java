package com.reachmedia.ad.reachmediaad.app;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.reachmedia.ad.reachmediaad.network.cookie.PersistentCookieStore;
import com.reachmedia.ad.reachmediaad.network.http.AppApiService;
import com.reachmedia.ad.reachmediaad.utils.AppVersionHelper;
import com.reachmedia.ad.reachmediaad.utils.SharedPreferencesHelper;
import com.squareup.okhttp.OkHttpClient;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by tedyuen on 2017/2/6.
 */

public class App extends Application {

    private static Context sContext;

    private static AppApiService sAppApiService;//API 请求Service对象

    public static App app = null;

    public static App getIns() {
        return app;
    }

    public static Context getContext() {
        return sContext;
    }

    public static AppApiService getAppApiService() {
        return sAppApiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        app = this;
        setUpSharedPreferencesHelper(getApplicationContext());//初始化SharedPreferences
        SharedPreferencesHelper mSharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        initVersionInfo(mSharedPreferencesHelper);
        setUpApiService();//初始化APP API

    }

    public void initVersionInfo(SharedPreferencesHelper mSharedPreferencesHelper){
        try{
            String versionName = AppVersionHelper.getVersionName(getContext());
            String versionCode = String.valueOf(AppVersionHelper.getVersionCode(getContext()));
            mSharedPreferencesHelper.putString(AppSpContact.SP_KEY_VERSION_CODE,versionCode);
            mSharedPreferencesHelper.putString(AppSpContact.SP_KEY_VERSION_NAME,versionName);
        }catch (Exception e){
            e.printStackTrace();
            mSharedPreferencesHelper.putString(AppSpContact.SP_KEY_VERSION_CODE,"-1");
            mSharedPreferencesHelper.putString(AppSpContact.SP_KEY_VERSION_NAME,"0.1");
        }
    }

    public void setUpApiService() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateTypeAdapter()).create();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setReadTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setWriteTimeout(15, TimeUnit.SECONDS);
        okHttpClient.setCookieHandler(new CookieManager(
                new PersistentCookieStore(getApplicationContext()),
                CookiePolicy.ACCEPT_ALL));

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(AppApiContact.getApiHost())
                .setConverter(new GsonConverter(gson))
                .setClient(new OkClient(okHttpClient))
                .build();
        sAppApiService = restAdapter.create(AppApiService.class);
    }

    /**
     * 初始化SharedPreferences
     *
     * @param context 上下文
     */
    private void setUpSharedPreferencesHelper(Context context) {
        SharedPreferencesHelper sph = SharedPreferencesHelper.getInstance();
        sph.Builder(context);

    }
}
