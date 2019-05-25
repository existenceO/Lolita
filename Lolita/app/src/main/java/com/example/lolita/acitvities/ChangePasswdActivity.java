package com.example.lolita.acitvities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;

public class ChangePasswdActivity extends BaseActivity {

    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private MyConnection  myConnection;
    private String TAG = "ChangePwd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwd);
        bindMusicService();

        initView();
    }
    private void initView(){
        initNavBar(true, "修改密码");
    }
    //TODO 接收原来的密码并校验，接收新修改的密码，确认两次输入是否一致

    @Override
    protected void onStop(){
        super.onStop();
        unBindMusicService();
    }

    class MyConnection implements ServiceConnection {
        private  final String CONTAG = "playMusicConnection";

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(CONTAG,"onServiceConnected");
//           获取binder的对象，调用binder的自定义方法，获取Service对象
            mBinder = (MusicServices.MusicBinder) service;
            myService = mBinder.getMusicService();

        }


        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }

        @Override
        public void onNullBinding(ComponentName name) {

        }
    }
    private void bindMusicService(){
        Intent intent = new Intent(ChangePasswdActivity.this, MusicServices.class);
        myConnection = new MyConnection();
        bindService(intent,myConnection,BIND_AUTO_CREATE);

    }
    private void unBindMusicService(){
        unbindService(myConnection);
        myService = null;
    }

}
