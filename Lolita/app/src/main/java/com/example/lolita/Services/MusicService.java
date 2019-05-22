package com.example.lolita.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public class MusicBind extends Binder {

        /**
         * 设置音乐
         */
        public void setMusic () {

        }

        /**
         * 播放音乐
         */
        public void playMusic () {
        }
        }
}
