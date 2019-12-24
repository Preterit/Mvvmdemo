package com.mvvm.base;

import android.app.Application;

/**
 * @author :  lwb
 * Date: 2019/12/24
 * Desc:
 */
public class BaseApplication extends Application {

    public static BaseApplication mBaseContext;
    public static boolean sIsDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mBaseContext = this;
    }

    public static void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }
}
