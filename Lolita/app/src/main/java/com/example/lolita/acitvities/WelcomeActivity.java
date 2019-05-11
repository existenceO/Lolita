package com.example.lolita.acitvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lolita.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {
   private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //延时2秒
        init();
        //跳转到main页面
    }
    private void init(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
               // Log.e("Welcome activity ","当前线程: "+ Thread.currentThread());
               // toMain();
                toLogin();
            }
        },2 * 1000);
    }

    /*
    * 跳转到MainAcitivity*/
    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    /*
     * 跳转到LoginAcitivity*/
    private void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

}
