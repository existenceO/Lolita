package com.example.lolita.acitvities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lolita.R;
import com.example.lolita.helps.MediaPlayerHelp;
import com.example.lolita.utils.UserUtils;
//import com.example.lolita.views.PlayMusicView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends AppCompatActivity {

    private ImageView mIvBg;
//    private PlayMusicView mPlayMusicView;
//    private Thread seekBarThread;
    private int PlayStyle;
    private int index = 0;//播放的索引
    private boolean flag = false;
    private ArrayList<String> music_list_name;//歌曲url
    private ArrayList<String> music_singer_name;//歌手名
    private ArrayList<String> music_music_name;
    private ArrayList<String> music_icon;//图片
    private ImageView mIvPlaySequence,mIvLast,mIvNext, mIvPlayOrPause, mIvMusicList ;
    private SeekBar mSeekBar;
    private boolean isPlay;
    private String mPath;
   // private View mView;
    private ImageView mIvIcon, mNeedle, mIvPlay ;
    private FrameLayout mPlayMusic;
    private MediaPlayerHelp mMeidaPlayerHelp;
    private TextView mTvSingerName, mTvMusicName;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private int duration;

   /* @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NotNull Message msg){
           // super.handleMessage(msg);
          //   int duration = mMeidaPlayerHelp.getMusicDuration();
           // int progress  =  100 * (msg.what / duration);
         //   System.out.println(duration);
//            mSeekBar.setProgress(progress);

                int position = msg.arg1;
                int duration = msg.arg2;
                System.out.println("d:" + duration + " p:" + position);
                mSeekBar.setProgress(50);

        }
    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
       //隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*new Thread() {
            public void run() {*/
          mMeidaPlayerHelp = MediaPlayerHelp.getInstance(this);
                initView();
    /*        }
        }.start();*/
        // new GetTimeThread().run();
     //   if(mMeidaPlayerHelp != null ){
  /*      new Thread(){
            @Override
            public void run(){
                while( isPlay && mMeidaPlayerHelp.isPlaying()){
                    int position = mMeidaPlayerHelp.getMusicPosition();
                  //  int duration = mMeidaPlayerHelp.getMusicDuration();
                    Message msg = new Message();
                      msg.what = 1;
                      msg.arg1 =position;
                      msg.arg2 = duration;
                      handler.sendMessage(msg);
//                     handler.sendEmptyMessage(position);
                  System.out.println("position1: "+ position);
                    try{
                        Thread.sleep(500);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }.start();
        System.out.println("线程启动");*/

    }
  /* class GetTimeThread implements Runnable{
        @Override
        public void run(){
            while(isPlay){
                int position = mMeidaPlayerHelp.getMusicPosition();
               // handler.sendEmptyMessage(position);
                System.out.println("position: "+ position);
                try{
                    Thread.sleep(500);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        }
    }*/
@Override
  protected void onDestroy() {
        mMeidaPlayerHelp.stop();
      super.onDestroy();

  }
    private void initView(){
        mIvIcon = findViewById(R.id.iv_pic);
        mPlayMusic = findViewById(R.id.fl_play_music);

        mNeedle = findViewById(R.id.needle);
        mIvPlay =findViewById(R.id.iv_play);
        mIvBg = findViewById(R.id.iv_bg);


        mSeekBar = findViewById(R.id.seekBar);//进度条
        mIvPlaySequence = findViewById(R.id.play_sequence);
        mIvLast = findViewById(R.id.last_music);
        mIvNext = findViewById(R.id.next_music);
        mIvPlayOrPause = findViewById(R.id.play_or_pause);
        mIvMusicList = findViewById(R.id.music_list);
        mIvPlaySequence = findViewById(R.id.play_sequence);

        mTvMusicName = findViewById(R.id.music_name);
        mTvSingerName = findViewById(R.id.singer_name);

//        System.out.println(Integer.toString(R.id.iv_pic));
        /**
         * -定义执行的动画
         * 1.定义CD要执行的动画
         * 2.指针指向CD的动作
         * 3.指针离开CD的动画
         * -开始animation
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(this, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(this, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(this, R.anim.stop_needle_anim);





        /**
         *加载图片
         */
        music_icon = new ArrayList<>();
                music_icon.add("http://192.168.180.83:8089/goodday/image/photo/c2-s.jpg");
                music_icon.add("http://192.168.180.83:8089/goodday/image/photo/c3-s.jpg");
                music_icon.add("http://192.168.180.83:8089/goodday/image/photo/c1-s.jpg");
       setMusicIcon(music_icon.get(index));
        //activity的背景图
        setIvBg(music_icon.get(index));

        music_list_name = new ArrayList<>();
        music_list_name.add("http://192.168.180.83:8089/Taylor%20Swift%20-%2022.mp3");
        music_list_name.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Begin%20Again.mp3");
        music_list_name.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Red.mp3");

        music_music_name = new ArrayList<>();
        music_music_name.add("22");
        music_music_name.add("Begin Again");
        music_music_name.add("Red");

        music_singer_name = new ArrayList<>();
        music_singer_name.add("Taylor");
        music_singer_name.add("Tom");
        music_singer_name.add("Marry");


        /**播放音乐
         *
         */

                flag = true;
         PlayMusic(music_list_name.get(index));//uri
        setMusicName(music_music_name.get(index));
        setSingerName(music_singer_name.get(index));



//        监听顺序图标的变化
        mIvPlaySequenceListener();
//        playMusicSequence();

        mIvLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvPlayOrPause.setImageResource(R.mipmap.pause);
                if(PlayStyle == 0 || PlayStyle == 2)
                lastMusic();//上一首
                else if(PlayStyle == 1)
                 randomPlay();
            }
        });
        mIvPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();//播放或者暂停

            }
        });
        mIvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvPlayOrPause.setImageResource(R.mipmap.pause);
                if(PlayStyle == 0 || PlayStyle == 2)
                   nextMusic();
                else if(PlayStyle == 1)
                    randomPlay();
            }
        });
    }




    /**
     * 播放顺序的改变
     */
    public void playMusicSequence(){
        switch (PlayStyle){
            case 0:
                listCirclePlay();//列表循环
                break;
            case 1:
                randomPlay();//随机播放
                break;
            case 2:
                singlePlay();//单曲循环
                break;
        }
    }

    /**
     * 切换播放状态
     */
    public void trigger(){
        if(isPlay){
            mIvPlayOrPause.setImageResource(R.mipmap.play);
            stopMusic();

        }else {
            mIvPlayOrPause.setImageResource(R.mipmap.pause);
            PlayMusic(music_list_name.get(index));
        }

    }
    /**
     * 播放音乐
     */

    public void PlayMusic(String path) {
        mPath = path;
        mIvPlay.setVisibility(View.GONE);
        mPlayMusic.startAnimation(mPlayMusicAnim);
        mNeedle.startAnimation(mPlayNeedleAnim);
        /**
         * 1.判断当前音乐是否已经在播放
         * 2.是，执行start()
         * 3.否，播放的是其他音乐，重置media player，setPath()
         */
//        TODO //        启动服务 //        startMusicService();将下面部分搬到MusicService,extends service
//

        if (mMeidaPlayerHelp.getPath() != null
                && mMeidaPlayerHelp.getPath().equals(path) && !isPlay) {
            mMeidaPlayerHelp.start();
        } else {
            mMeidaPlayerHelp.setPath(path);

           mMeidaPlayerHelp.setOnMediaPlayerHelperListener(new MediaPlayerHelp.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMeidaPlayerHelp.start();
                }

                @Override
                public void onCompletion(MediaPlayer mp) {
                    //监听音乐是否播放完
                    //下一曲
                    playMusicSequence();
                }
            });

        }
        isPlay = true;
      //  duration = mMeidaPlayerHelp.getMusicDuration();
     //   System.out.println(duration);
       // int max = mMeidaPlayerHelp.getMusicDuration();
        //mSeekBar.setMax(max);


    }
    /**
     * 停止播放
     */
//    TODO 把CD上的暂停和播放取消放到下面的按钮里
    public void stopMusic(){
        isPlay = false;
        mIvPlay.setVisibility(View.VISIBLE);
        mPlayMusic.clearAnimation();
        mNeedle.startAnimation(mStopNeedleAnim);
        mMeidaPlayerHelp.pause();
    }

    /**
     * 设置CD中显示的音乐封面图片
     */
    public void setMusicIcon(String icon){
        //为mIvIcon引入图片
        Glide.with(this)
                .load(icon)
                .into(mIvIcon);
    }
    /**
     * 设置歌曲名
     * */
    public void setMusicName(String name){
        mTvMusicName.setText(name);
    }

    /**
     * 设置歌手名
     * */
    public void setSingerName(String name){
        mTvSingerName.setText(name);
    }
    /**
 * 播放顺序图标的监听事件
 */
  public void mIvPlaySequenceListener(){
      mIvPlaySequence.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      PlayStyle++;
                      if(PlayStyle > 2){
                          PlayStyle = 0;
                      }
                      switch (PlayStyle){
                          case 0:
                              mIvPlaySequence.setImageResource(R.mipmap.xunhuan);
                              Toast.makeText(PlayMusicActivity.this, "列表循环",
                                      Toast.LENGTH_SHORT).show();

                              break;
                          case 1:
                              mIvPlaySequence.setImageResource(R.mipmap.suiji);
                              Toast.makeText(PlayMusicActivity.this, "列表随机",
                                      Toast.LENGTH_SHORT).show();
                              break;
                          case 2:
                              mIvPlaySequence.setImageResource(R.mipmap.danqu);
                              Toast.makeText(PlayMusicActivity.this, "单曲循环",
                                      Toast.LENGTH_SHORT).show();
                              break;
                      }
                  }
              }
      );
  }


    // TODO 用array_list or other 来接收数据库歌曲的各种信息，然后path赋给path，上下首改变path的值
    /**
     * 要点，改变path*/

    /**
     * 上一首
     * */
    public void lastMusic() {
        index--;
        if (index < 0) {
            index = music_list_name.size() - 1;
        }

        PlayMusic(music_list_name.get(index));
        setMusicName(music_music_name.get(index));
        setSingerName(music_singer_name.get(index));
//        playmusicView的CD中间照片
         setMusicIcon(music_icon.get(index));
//        activity的背景
        setIvBg(music_icon.get(index));
    }

    /**
     * 下一首
     * */
    public void nextMusic() {
        index++;
        if (index > music_list_name.size() - 1) {
            index = 0;
        }
        PlayMusic(music_list_name.get(index));
        setMusicName(music_music_name.get(index));
        setSingerName(music_singer_name.get(index));
        setMusicIcon(music_icon.get(index));

        setIvBg(music_icon.get(index));
    }

    /**
     * 播放或暂停
     * */
    public void setIvBg(String path){
        Glide.with(this)
                .load(path)
                //radius为25，处理后图片宽高为原来的1/10
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))//transform封装到一个requestOption的对象中
                .into(mIvBg);
    }
    /**
     * 单曲循环播放
    * */
   private void singlePlay(){
        PlayMusic(music_list_name.get(index));
    }
    /**
     * 列表循环播放
     * */
    private void listCirclePlay(){
        nextMusic();
    }
    /***
     * 随机播放
     */
    private void randomPlay(){
        Random random = new Random();
        index =  random.nextInt(music_list_name.size());
      // index %= music_list_name.size();
        PlayMusic(music_list_name.get(index));
        setMusicName(music_music_name.get(index));
        setSingerName(music_singer_name.get(index));
        setMusicIcon(music_icon.get(index));

        setIvBg(music_icon.get(index));

    }

/**
 * 后退按钮点击事件
 */
    public void onBackClick(View v){
        onBackPressed();
    }
}
