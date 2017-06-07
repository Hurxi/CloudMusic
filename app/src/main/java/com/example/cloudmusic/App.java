package com.example.cloudmusic;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by 若希 on 2017/6/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化OKGO框架
        OkGo.init(this);
        //可以额外设置OKGO属性  比如 超时 缓存
    }
}
