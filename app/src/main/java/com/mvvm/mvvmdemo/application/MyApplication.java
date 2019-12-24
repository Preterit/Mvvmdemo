package com.mvvm.mvvmdemo.application;

import com.mvvm.base.BaseApplication;
import com.mvvm.mvvmdemo.BuildConfig;

/**
 * @author :  lwb
 * Date: 2019/12/24
 * Desc:
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        setIsDebug(BuildConfig.DEBUG);  // 要用当前app目录下的
    }
}
