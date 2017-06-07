package com.example.cloudmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 若希 on 2017/6/7.
 */

public class SpUtils {
    //配置文件的名在
    static String SP_NAME="config";
    public static void putBoolean(Context context,boolean value,String key){
        //使用sp存储一个布尔类型的数据
        SharedPreferences sp=context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor et=sp.edit();
        et.putBoolean(key,value);
        et.commit();
    }
    public static boolean getBoolean(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        boolean value=sp.getBoolean(key,true);
        return value;
    }
}
