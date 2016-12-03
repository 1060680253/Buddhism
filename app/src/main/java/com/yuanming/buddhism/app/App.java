package com.yuanming.buddhism.app;

import android.app.Application;
import android.content.Context;

import com.yuanming.buddhism.constant.Constants;
import com.yuanming.buddhism.http.img.PictureLoader;
import com.yuanming.buddhism.util.SPUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Administrator on 2016/11/2.
 */

public class App extends Application {

    private static App instance;
    public static synchronized App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                    .detectAll() .penaltyLog() .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectAll() .penaltyLog() .build());
//        }
        super.onCreate();
        instance = this;
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/PMingLiU.ttf").setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath).build());
        PictureLoader.getInstance().initImageLoader(instance);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("fonts/PMingLiU.ttf").setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath).build());
    }

    public Context getContext(){
        return instance;
    }

    public void setLogin(boolean isLogin){
        SPUtils.put(Constants.IS_LOGIN_TAG,isLogin);
    }

    public boolean islogin(){
        return (boolean)SPUtils.get(Constants.IS_LOGIN_TAG,false);
    }
}
