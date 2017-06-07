package com.example.cloudmusic.utils;

/**
 * Created by 若希 on 2017/6/5.
 */

public class AppStringUtil {
    public static boolean checkUserName(String userName){
        if (userName==null){
            return false;
        }
        if (userName.equals("")){
            return false;
        }
        if (userName.length()<3||userName.length()>10){
            return false;
        }
        return true;
    }
    public static boolean checkPassword(String password){
        if (password==null){
            return false;
        }
        if (password.equals("")){
            return false;
        }
        if (password.length()<3||password.length()>10){
            return false;
        }
        return true;
    }
}
