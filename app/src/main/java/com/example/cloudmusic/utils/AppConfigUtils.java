package com.example.cloudmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cloudmusic.App;

/**
 * Created by 若希 on 2017/6/7.
 */


/**
 * app配置信息类
 */
public class AppConfigUtils {
    static String IS_FIRST="isfirst";
    //私有化构造方法 持有本类的应用 提供一个静态的刚发，获取本类对象
    private AppConfigUtils(){}
    private static AppConfigUtils mConfigUtils;
    public static final AppConfigUtils getInstance(){
        if (mConfigUtils==null){
            mConfigUtils=new AppConfigUtils();

        }
        return mConfigUtils;
    }
    static SharedPreferences sp;
    public  boolean getIsFirst(Context context){
        return SpUtils.getBoolean(context,IS_FIRST);
    }
    public  void setIsFirst(boolean isFirst,Context context){
        SpUtils.putBoolean(context,isFirst,IS_FIRST);
    }
}
