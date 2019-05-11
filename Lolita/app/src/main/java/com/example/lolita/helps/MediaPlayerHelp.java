package com.example.lolita.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;


public class MediaPlayerHelp {
    public static MediaPlayerHelp instance;
    private Thread playMusicThread;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayerHelp getInstance(Context context){
/**
 *
 */
        if(instance == null){
            synchronized (MediaPlayerHelp.class){
                if(instance == null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }
    private MediaPlayerHelp(Context context){
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }
    /**
     * 1.setPath:播放音乐的路径
     * 2.start: 播放音乐
     * 3.pause:暂停播放
     * 4.上一首
     * 5。下一首
     */

    /**
 * 返回当前播放的音乐路径
 */
    public String getPath(){
        return mPath;
    }

    public void setPath (String path){
        /**
         * 1.音乐正在播放，重置音乐播放状态
         * 2.设置播放音乐路径
         * 3.准备播放
         */
        mPath = path;
//        音乐重置
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }
//        设置播放路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        准备播放
        /**
         * prepare()同步执行
         * prepareAsync()异步加载
         */

        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
               if(onMediaPlayerHelperListener != null){
                   onMediaPlayerHelperListener.onPrepared(mp);
               }
            }
        });
    }
   /**       播放音乐

    */
    public void start(){
        if(mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    /**    暂停播放
     */
    public void pause(){
            mMediaPlayer.pause();
    }

    public interface OnMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }


}
