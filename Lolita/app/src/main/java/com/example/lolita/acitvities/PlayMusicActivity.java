package com.example.lolita.acitvities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;
import com.example.lolita.adapters.MusicDetailAdapter;
import com.example.lolita.helps.MediaPlayerHelp;
//import com.example.lolita.views.PlayMusicView;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends AppCompatActivity {

    private ImageView mIvBg;
    private final String TAG = "PlayMusicActivity";
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
    private int status;
    private Message message = new Message();
    private String mPath;
   // private View mView;
    private ImageView mIvIcon, mNeedle, mIvPlay ;
    private FrameLayout mPlayMusic;
    private MediaPlayerHelp mMeidaPlayerHelp;
    private TextView mTvSingerName, mTvMusicName;
    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    private int position;

    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private MyConnection  myConnection;

    private int musicListId;
    private final int ORDER_PLAY = 1;
    private final int RADOM_PLAY = 2;
    private final int SINGLE_PLAY = 3;

    private updateSeekbar update;
    public static Handler handler;
    private View contentView;
    private Context mContext;

    private ListView listView;
    private ArrayList<String> list_song_name = new ArrayList<>();
    private PopupWindow popupWindow;

    private Handler threadHandler;
    private boolean ismPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
       //隐藏statusBar
         mContext = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

               bindMusicService();
                initView();
                list_song_name.add("22");
                list_song_name.add("begin again");
                list_song_name.add("red");

    }
    @Override
    public void onResume() {

        super.onResume();

    }
    @Override
    public void onStop() {
        super.onStop();
        unBindMusicService();
        update.cancel(true);
        update = null;
//        unBindMusicService();
    }

  class MyConnection implements ServiceConnection{
      private  final String CONTAG = "playMusicConnection";

      @SuppressLint("HandlerLeak")
      @Override
      public void onServiceConnected(ComponentName name, IBinder service) {
           Log.i(CONTAG,"onServiceConnected");
//           获取binder的对象，调用binder的自定义方法，获取Service对象
           mBinder = (MusicServices.MusicBinder) service;
           myService = mBinder.getMusicService();
          musicListId = myService.getMusicId();
          index = myService.getIndex();
//          播放音乐
          myService.startMusic(index);

          PlayStyle = myService.getPlayMode();
          setMusicName(music_music_name.get(index));
          setSingerName(music_singer_name.get(index));
          isPlay = true;


          setMusicIcon(music_icon.get(index));
//          Log.d("index",index + "");
          //activity的背景图
          setIvBg(music_icon.get(index));
//          new MusicThread().start();
//          if(!myService.isMediaPlayerNull())
              mSeekBar.setMax(myService.getDuration());
           update = new updateSeekbar();
          update.execute(1000);



          handler = new Handler() {
              @Override
              public void handleMessage(Message msg) {

                  if(msg.arg1 == 100){
                      index = myService.getIndex();
                      setIvBg(music_icon.get(index));
                      setMusicIcon(music_icon.get(index));
                      setSingerName(music_singer_name.get(index));
                      setMusicName(music_music_name.get(index));
                  }
                  super.handleMessage(msg);

              }

          };

      }


      @Override
      public void onServiceDisconnected(ComponentName name) {
            Log.i(CONTAG,"onServiceDisconnect");
      }

      @Override
      public void onBindingDied(ComponentName name) {

      }

      @Override
      public void onNullBinding(ComponentName name) {

      }
  }
     private void bindMusicService(){
           Intent intent = new Intent(PlayMusicActivity.this, MusicServices.class);
           myConnection = new MyConnection();
           bindService(intent,myConnection,BIND_AUTO_CREATE);

     }
     private void unBindMusicService(){
       unbindService(myConnection);
       myService = null;
     }
@Override
  protected void onDestroy() {
//        mMeidaPlayerHelp.stop();

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


        mIvPlay.setVisibility(View.GONE);
        mPlayMusic.startAnimation(mPlayMusicAnim);
        mNeedle.startAnimation(mPlayNeedleAnim);


//        监听顺序图标的变化
        mIvPlaySequenceListener();
//        playMusicSequence();
        mIvMusicList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initPopWindow();

                setBackgroudAlpha(0.7f);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        setBackgroudAlpha(1f);
                    }
                });

            }
        });

        mIvLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvPlayOrPause.setImageResource(R.mipmap.pause);
                lastMusic();

            }
        });
        mIvPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();//播放或者暂停
                /*message.arg2= status;
                threadHandler.sendMessage(message);
                message = threadHandler.obtainMessage();
*/
            }
        });
        mIvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvPlayOrPause.setImageResource(R.mipmap.pause);
                nextMusic();

            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    myService.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

     private void initPopWindow(){
         contentView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_list_detail,null);
         ListView listView = contentView.findViewById(R.id.music_list_detail);
         MusicDetailAdapter adapter = new MusicDetailAdapter(mContext,music_music_name,music_singer_name);
         listView.setAdapter(adapter);
         popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        popupWindow.setBackgroundDrawable(contentView.getResources().getDrawable(android.R.color.transparent));
         popupWindow.setBackgroundDrawable(new BitmapDrawable(contentView.getResources(), (Bitmap) null));



     }
    /**
     设置弹窗之后的背景变暗
     */
    private void setBackgroudAlpha(float alpha){
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = alpha; //0.0-1.0
        getWindow().setAttributes(layoutParams);

    }

         class updateSeekbar extends AsyncTask<Integer, Integer, String>{

        protected void onPostExecute(String result){

        }
        protected void onProgressUpdate(Integer ...progress){
            if(isCancelled()){
                return;
            }
            mSeekBar.setProgress(progress[0]);
            Log.d("tag",PlayMusicActivity.this.myService.getCurPosition()+"");
        }

          @SuppressLint("HandlerLeak")
          @Override
          protected String doInBackground(Integer... params) {


              while(
                      !PlayMusicActivity.this.myService.isMediaPlayerNull()) {

                  if (isCancelled()) {  //通过isCancelled()判断cancel(true)是否成功
                      break;
                  }
                  if (PlayMusicActivity.this.isPlay) {
                      try {
                          Thread.sleep(params[0]);
                      } catch (InterruptedException ex) {
                          ex.printStackTrace();
                      }
                      position = PlayMusicActivity.this.myService.getCurPosition();
                  }

                  publishProgress(position);


              }

              return null;
          }
          @Override
          protected void onCancelled() {
              Log.d(TAG,"seekBar update thread onCancelled");
              super.onCancelled();
          }
      }


    /**
     * 切换播放状态
     */
    public void trigger(){
        if(isPlay){
            mIvPlayOrPause.setImageResource(R.mipmap.play);
            mIvPlay.setVisibility(View.VISIBLE);
            mPlayMusic.clearAnimation();
            mNeedle.startAnimation(mStopNeedleAnim);
//            mMeidaPlayerHelp.pause();
            myService.pause();
//            myService.seekTo(mposition);
            isPlay = false;
             status = 0;

        }else {
            mIvPlayOrPause.setImageResource(R.mipmap.pause);
            mIvPlay.setVisibility(View.GONE);
            mPlayMusic.startAnimation(mPlayMusicAnim);
            mNeedle.startAnimation(mPlayNeedleAnim);
            myService.play();
            isPlay = true;
            status = 1;

        }


    }

    /**
     * 停止播放
     */

    /**i
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

      new Thread() {

          public void run() {
              mIvPlaySequence.setOnClickListener(
                      new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              PlayStyle++;
                              if (PlayStyle > 3) {
                                  PlayStyle = 1;
                                  myService.setPlayMode(ORDER_PLAY);
                              }
                              switch (PlayStyle) {
                                  case 1:
                                      mIvPlaySequence.setImageResource(R.mipmap.xunhuan);
                                      Toast.makeText(PlayMusicActivity.this, "列表循环",
                                              Toast.LENGTH_SHORT).show();
                                      myService.setPlayMode(ORDER_PLAY);

                                      break;
                                  case 2:
                                      mIvPlaySequence.setImageResource(R.mipmap.suiji);
                                      Toast.makeText(PlayMusicActivity.this, "列表随机",
                                              Toast.LENGTH_SHORT).show();
                                      myService.setPlayMode(RADOM_PLAY);
                                      break;
                                  case 3:
                                      mIvPlaySequence.setImageResource(R.mipmap.danqu);
                                      Toast.makeText(PlayMusicActivity.this, "单曲循环",
                                              Toast.LENGTH_SHORT).show();
                                      myService.setPlayMode(SINGLE_PLAY);
                                      break;
                              }
                          }
                      }
              );
          }
      }.start();
  }

/**
     * 上一首
     * */

    public void lastMusic() {

        PlayStyle = myService.getPlayMode();
        if(PlayStyle == 1 || PlayStyle == 3)
            myService.preMusic();//上一首
        else if(PlayStyle == 2)
            myService.randomPlay();

        index = myService.getIndex();
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
        PlayStyle = myService.getPlayMode();
        if(PlayStyle == 1|| PlayStyle == 3)
            myService.nextMusic();
        else if(PlayStyle == 2)
            myService.randomPlay();

        index = myService.getIndex();
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
 * 后退按钮点击事件
 */
    public void onBackClick(View v){
        onBackPressed();
    }
}
