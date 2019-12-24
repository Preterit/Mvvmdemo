package com.mvvm.mvvmdemo.application;

import com.billy.cc.core.component.CC;
import com.kingja.loadsir.core.LoadSir;
import com.mvvm.base.BaseApplication;
import com.mvvm.base.loadsir.CustomCallback;
import com.mvvm.base.loadsir.EmptyCallback;
import com.mvvm.base.loadsir.ErrorCallback;
import com.mvvm.base.loadsir.LoadingCallback;
import com.mvvm.base.loadsir.TimeoutCallback;
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

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();

        CC.enableDebug(BuildConfig.DEBUG);
        CC.enableVerboseLog(BuildConfig.DEBUG);
        CC.enableRemoteCC(BuildConfig.DEBUG);
    }
}
