package com.example.lolita.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lolita.R;
import com.example.lolita.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends AppCompatActivity {

    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;
    private Thread playMusicThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
       //隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }
    private void initView(){

//        glide-transformations
//       TODO 未实现多张图片及多首音乐的加载
        mIvBg = findViewById(R.id.iv_bg);
        Glide.with(this)
                .load("http://192.168.180.83:8089/goodday/image/photo/a2-s.jpg")
                //radius为25，处理后图片宽高为原来的1/10
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10 )))//transform封装到一个requestOption的对象中
                .into(mIvBg);

        mPlayMusicView = findViewById(R.id.play_music_view);
        mPlayMusicView.setMusicIcon("http://192.168.180.83:8089/goodday/image/photo/a2-s.jpg");

        //播放音乐
        playMusicThread = new Thread(new Runnable(){
            @Override
            public void run() {
                mPlayMusicView.PlayMusic("http://192.168.180.83:8089/Taylor%20Swift%20-%2022.mp3");
                Log.e("playMusicThread","is running");//不是错误，只是打印log证明是线程启动
            }
        });
        playMusicThread.start();

    }
/**
 * 后退按钮点击事件
 */
    public void onBackClick(View v){
        onBackPressed();
    }
}
