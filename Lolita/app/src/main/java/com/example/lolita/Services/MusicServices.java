package com.example.lolita.Services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.example.lolita.acitvities.PlayMusicActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MusicServices extends Service {
    private ArrayList<String> musicPath = new ArrayList<>();
    private int mIndex = 0;
    //    private String mPath;
    private static final String TAG = "MediaService";
    //    初始化mediaPlayer
    public MediaPlayer mMediaPlayer;
    private MusicBinder musicBinder;
    private int musicId = 1;
    private int playMode = 1;//默认是顺序播放
    private final int SINGLE_PLAY = 3;
    private final int ORDER_PLAY = 1;
    private final int RANDOM_PLAY = 2;
    private final int INDEX_CHANGED = 100;
    private boolean isStart = false;

    public MusicServices() {

    }

    @Override
    public void onCreate() {
//        创建服务
        Log.i(TAG, "Service onCreate");
        super.onCreate();

        //初始化数据
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%2022.mp3");
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Begin%20Again.mp3");
        musicPath.add("http://192.168.180.83:8089/Taylor%20Swift%20-%20Red.mp3");
        mMediaPlayer = new MediaPlayer();
        musicBinder = new MusicBinder();
    }

    // TODO 后台开始播放，点击某一首歌曲的播放逻辑还没搞清楚
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public IBinder onBind(Intent intent) {
        //  Return the communication channel to the service.
        //mIndex = intent.getIntExtra("musicIndex",0);//如果获取不到，取0

        return musicBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

    public void onDestroy() {

        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }

    public void onRebind(Intent intent) {
        Log.i(TAG, "Service onRebind");
        super.onRebind(intent);
    }

    public class MusicBinder extends Binder {
        //             利用MusicBinder实现Binder，得到当前MusicService对象
        public MusicServices getMusicService() {

            Log.d(TAG, "得到当前MusicService实例");
            return MusicServices.this;
        }

    }
    /**to set the isStart true*/
    public void setIsStart(boolean isStart){
        this.isStart = isStart;
    }

    /** to see whether the mediaplayer object is null*/
     public boolean isMediaPlayerNull(){
         return mMediaPlayer == null;
     }

    /**
     * set the muisicId
     */
    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    /**
     * get the musicId
     */
    public int getMusicId() {
        return this.musicId;
    }

    /**
     * set the playMode
     */
    public void setPlayMode(int playMode) {
        this.playMode = playMode;
    }

    /**
     * get the playMode
     */
    public int getPlayMode() {
        return this.playMode;
    }

    /**
     * set the current index of the music
     */
    public void setIndex(int index) {
        mIndex = index;
    }

    /**
     * get the current index of the music
     */
    public int getIndex() {
        return mIndex;
    }

    /**
     * set the progress of music
     */
    public void seekTo(int mesc) {
        mMediaPlayer.seekTo(mesc);
    }

    /**
     * get the currentLength of the music
     */
    public int getCurPosition() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            return mMediaPlayer.getCurrentPosition();
        return 0;
    }

    /**
     * get the length of the music
     */
    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    /**
     * start to play music
     */
    public void startMusic(int index) {
        playMusic(index);
    }

    /**
     * continue to play music
     */
    public void play() {
        if (!mMediaPlayer.isPlaying())
            mMediaPlayer.start();
    }

    /**
     * pause playing music
     */
    public void pause() {
        if (mMediaPlayer.isPlaying())
            mMediaPlayer.pause();
    }

    /**
     * pre
     */
    public void preMusic() {
//         顺序播放
        if (mMediaPlayer != null ) {
            mMediaPlayer.reset();
            mIndex--;
            if (mIndex < 0) {
                mIndex = musicPath.size() - 1;
            }
            playMusic(mIndex);

        }
    }

    /**
     * next*
     */
    public void nextMusic() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mIndex++;
            if (mIndex > musicPath.size() - 1) {
                mIndex = 0;
            }
            playMusic(mIndex);

        }
    }

    /**
     * randomPlay music
     */
    public void randomPlay() {
        Random random = new Random();
        mIndex = random.nextInt(musicPath.size());//不包括size()s
        playMusic(mIndex);

    }

    /**
     * SequencePlay music
     */
    public void SequencePlay() {
        nextMusic();
    }

    /**
     * repeatedly play music
     */
    public void singlePlay() {
        playMusic(mIndex);

    }
    /***/

    /**
     * 将音乐的path添加到MediaPlayer中并且准备播放音频
     * */
    /**
     * 1.如果音乐正在播放，重置
     * 2.音乐还没播放，设置路径
     */
    private void playMusic(final int index) {



                if (null == mMediaPlayer) {
                    mMediaPlayer = new MediaPlayer();
                }
        /*if(mPath == musicPath.get(index)){
            return;//继续播放这首音乐
        }*/
                mMediaPlayer.reset();
                String mPath = musicPath.get(index);
                try {
//              设置音乐路径
                    mMediaPlayer.setDataSource(this, Uri.parse(mPath));
//                    mMediaPlayer.prepareAsync();，会报error(38,0)的错误
//                    create mediaPlayer时不能用prepare()
                    mMediaPlayer.prepare();

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Log.d(TAG, "设置资源出错");
                }

        new Thread() {

            public void run() {
                /**
                 * 监听是否加载完成
                 * */


                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mMediaPlayer.start();

                        }
                    });
                    /**
                     * 监听是否播放完*/
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
//                            Intent sendIntent = new Intent();
                            Message message = new Message();
                           if(playMode ==SINGLE_PLAY)
                                    singlePlay();
                           else if(playMode ==RANDOM_PLAY)
                                    randomPlay();
                           else if(playMode == ORDER_PLAY)
                                    SequencePlay();

                           message.arg1 = INDEX_CHANGED;
                           PlayMusicActivity.handler.sendMessage(message);


                            }


                    });

                Log.d("thread" ,"thread is running");
            }
        }.start();

    }
}
