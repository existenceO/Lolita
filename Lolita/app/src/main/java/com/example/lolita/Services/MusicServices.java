package com.example.lolita.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MusicServices extends Service {
    private ArrayList<String> musicPath= new ArrayList<>();
    private int mIndex = 0;
    private String mPath;
    private static final String TAG = "MediaService";
    //    初始化mediaPlayer
    public MediaPlayer mMediaPlayer = new MediaPlayer();
    private MusicBinder musicBinder = new MusicBinder();
    public MusicServices() {
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%2022.mp3");
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Begin%20Again.mp3");
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Red.mp3");
        initMediaPlayerPath(mIndex);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return musicBinder;
    }
    public class MusicBinder extends Binder implements MediaPlayer.OnCompletionListener, OnPreparedListener{

        public MusicBinder(){

        }


        /**
         * 播放音乐*/
        public void playMusic() {
            if(!mMediaPlayer.isPlaying())
                mMediaPlayer.start();
        }
        /**
         * 暂停播放*/
        public void pauseMusic(){
            if(mMediaPlayer.isPlaying())
                mMediaPlayer.pause();
        }
        /**
         * 上一首*/
        public void lastMusic(){
//         顺序播放
            if(mMediaPlayer != null ){
                mMediaPlayer.reset();
                mIndex--;
                if(mIndex < 0){
                    mIndex = musicPath.size()-1;
                }
                initMediaPlayerPath(mIndex);
                playMusic();
            }
        }
        /**
         * 下一首*
         * */
        public void nextMusic(){
            if(mMediaPlayer != null){
                mMediaPlayer.reset();
                mIndex++;
                if(mIndex > musicPath.size()-1) {
                    mIndex = 0;
                }
                initMediaPlayerPath(mIndex);
                playMusic();
            }
        }
           /**随机播放*/
              public void randomPlay(){

              }

              /**
                * 单曲循环
               * */
           public void singlePlay(){
               initMediaPlayerPath(mIndex);
               playMusic();
           }
           /***/
        /**
         *
         * 监听是否准备好*/
        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.d(TAG, "播放完毕");
            nextMusic();
        }
        /**
         * 监听是否播放完
         * */
        @Override
        public void onPrepared(MediaPlayer mp){

            Log.d(TAG,"准备完毕");
            playMusic();
        }

        }



    /**
     * 将音乐的path添加到MediaPlayer中并且准备播放音频
     * */
    /**
     * 1.如果音乐正在播放，重置
     * 2.音乐还没播放，设置路径
     * */
    private void initMediaPlayerPath(int index){

        try{
//              设置音乐路径
            mMediaPlayer.setDataSource(musicPath.get(index));
            mMediaPlayer.prepareAsync();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
            Log.d(TAG,"设置资源出错");
        }

    }
}