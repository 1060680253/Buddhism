package com.yuanming.buddhism.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.compat.BuildConfig;

import com.yuanming.buddhism.constant.Constants;
import com.yuanming.buddhism.util.SPUtils;

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
