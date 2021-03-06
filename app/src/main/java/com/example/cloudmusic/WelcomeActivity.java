package com.example.cloudmusic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.cloudmusic.utils.AppConfigUtils;

public class WelcomeActivity extends AppCompatActivity {

    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        handler.postDelayed(runnable,2000);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //判断是否是第一次使用
            //使用SharedPreferences判断是否是第一次登陆
            boolean isfirst= AppConfigUtils.getInstance().getIsFirst(WelcomeActivity.this);
            if (!isfirst){
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));

            }
            else {
                startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
            }

            finish();
        }
    };

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
