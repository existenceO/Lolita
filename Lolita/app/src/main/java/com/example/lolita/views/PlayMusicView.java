/*
package com.example.lolita.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lolita.R;
import com.example.lolita.helps.MediaPlayerHelp;

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private boolean isPlay;
    private String mPath;
    private View mView;
    private ImageView mIvIcon, mNeedle, mIvPlay,mIvPlaySequence,mIvLast,mIvNext, mIvPlayOrPause, mIvMusicList ;
    private FrameLayout mPlayMusic;
    private MediaPlayerHelp mMeidaPlayerHelp;
    private TextView mTvSingerName, mTvMusicName;



    private Animation mPlayMusicAnim, mPlayNeedleAnim, mStopNeedleAnim;
    public PlayMusicView(@NonNull Context context) {
        super(context);
        initView(context);

    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);

    }
    */
/**
     * Media player
     *//*


    private void initView(Context context){
          mContext = context;

        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);


        mIvIcon = mView.findViewById(R.id.iv_pic);
        mPlayMusic = mView.findViewById(R.id.fl_play_music);
     */
/*   mPlayMusic.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
              trigger();
            }
        });*//*

        mNeedle = mView.findViewById(R.id.needle);
        mIvPlay = mView.findViewById(R.id.iv_play);

       mTvMusicName = mView.findViewById(R.id.music_name);
       mTvSingerName = mView.findViewById(R.id.singer_name);

//        System.out.println(Integer.toString(R.id.iv_pic));
        */
/**
         * -定义执行的动画
         * 1.定义CD要执行的动画
         * 2.指针指向CD的动作
         * 3.指针离开CD的动画
         * -开始animation
         *//*

       mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
       mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
       mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);


        addView(mView);

        mMeidaPlayerHelp = MediaPlayerHelp.getInstance(mContext);
    }
  */
/*  *//*
*/
/**
     * 切换播放状态
     *//*
*/
/*
    public void trigger(){
       if(isPlay){
           stopMusic();
       }else {
           PlayMusic(mPath);
       }

    }
*//*
*/
/**
 * 播放音乐
 *//*
*/
/*

    public void PlayMusic(String path){
        mPath = path;
        isPlay = true;
      mIvPlay.setVisibility(View.GONE);
      mPlayMusic.startAnimation(mPlayMusicAnim);
      mNeedle.startAnimation(mPlayNeedleAnim);
        *//*
*/
/**
         * 1.判断当前音乐是否已经在播放
         * 2.是，执行start()
         * 3.否，播放的是其他音乐，重置media player，setPath()
         *//*
*/
/*
//        TODO //        启动服务 //        startMusicService();将下面部分搬到MusicService,extends service

//
          if( mMeidaPlayerHelp.getPath() != null
                  && mMeidaPlayerHelp.getPath().equals(path)){
              mMeidaPlayerHelp.start();
          }else {
              mMeidaPlayerHelp.setPath(path);
              mMeidaPlayerHelp.setOnMediaPlayerHelperListener(new MediaPlayerHelp.OnMediaPlayerHelperListener() {
                  @Override
                  public void onPrepared(MediaPlayer mp) {
                      mMeidaPlayerHelp.start();
                  }
             *//*
*/
/*     @Override
                  public void onCompletion(MediaPlayer mp) {
                      //监听音乐是否播放完
                  }*//*
*/
/*
              });
          }

    }
    *//*
*/
/**
     * 停止播放
     *//*
*/
/*
//    TODO 把CD上的暂停和播放取消放到下面的按钮里
    public void stopMusic(){
        isPlay = false;
        mIvPlay.setVisibility(View.VISIBLE);
        mPlayMusic.clearAnimation();
        mNeedle.startAnimation(mStopNeedleAnim);
        mMeidaPlayerHelp.pause();
    }

*//*
*/
/**
 * 设置CD中显示的音乐封面图片
 *//*
*/
/*
    public void setMusicIcon(String icon){
        //为mIvIcon引入图片
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }
   *//*
*/
/**
    * 设置歌曲名
    * *//*
*/
/*
   public void setMusicName(String name){
       mTvMusicName.setText(name);
   }

    *//*
*/
/**
     * 设置歌手名
    * *//*
*/
/*
    public void setSingerName(String name){
               mTvSingerName.setText(name);
    }*//*

}
*/
