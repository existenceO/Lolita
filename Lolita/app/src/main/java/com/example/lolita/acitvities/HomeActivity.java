package com.example.lolita.acitvities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;
import com.example.lolita.utils.UserUtils;

public class HomeActivity extends BaseActivity {
    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private MyConnection myConnection;
    private final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        bindMusicService();
    }
    @Override
    protected void onStop() {
        super.onStop();
        unBindMusicService();
    }
    private void initView(){
        initNavBar(true, "个人中心");
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
        Intent intent = new Intent(HomeActivity.this, MusicServices.class);
        myConnection = new MyConnection();
        bindService(intent,myConnection,BIND_AUTO_CREATE);

    }
    private void unBindMusicService(){
        unbindService(myConnection);
        myService = null;
    }
    /*
    * 修改密码
    * */
    public void onChangeClick(View v){
       startActivity(new Intent(this, ChangePasswdActivity.class));
    }
    /*退出登录*/
    // TODO 销毁session，android应该有类似的吧。。。
    public void onLogoutClick(View v){
//        跳转到登录页面
        UserUtils.logout(this);
    }
}
