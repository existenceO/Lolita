package com.example.lolita.acitvities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.notification.ConditionProviderService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;
import com.example.lolita.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

     private RecyclerView mRvList;
     private MusicListAdapter mListAdapter;
     private LinearLayout mPlayAllMusic, mCollectAlbum;
     private int ALBUM_LIST = 1;
     private int MY_CREATED_LIST = 3;

    private MyConnection myConnection;
    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private final String TAG = "AlbumListActivity";

    private int musicListId = 0;
    private int songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }
    protected  void onDestroy() {
        unBindMusicService();
        super.onDestroy();
    }
    class MyConnection implements ServiceConnection {

        private  final String CONTAG = "playMusicConnection";

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(CONTAG,"onServiceConnected");
//           获取binder的对象，调用binder的自定义方法，获取Service对象
            mBinder = (MusicServices.MusicBinder) service;
            myService = mBinder.getMusicService();
            musicListId = myService.getMusicId();


//             给myService传递musicId的值

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
        Intent intent = new Intent(AlbumListActivity.this, MusicServices.class);
        myConnection = new MyConnection();
        bindService(intent,myConnection,BIND_AUTO_CREATE);
        Log.i(TAG,"mBinder" + mBinder);
    }
    private void unBindMusicService(){
        unbindService(myConnection);
        myService = null;
    }
    private void initView(){


        initNavBar(true,"歌单列表");

        mRvList = findViewById(R.id.rv_list);
        mCollectAlbum = findViewById(R.id.collect_list);
        mPlayAllMusic = findViewById(R.id.play_all_music);

        if(mBinder == null){
            bindMusicService();
        }

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new MusicListAdapter( this, null,ALBUM_LIST );
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvList.setAdapter(mListAdapter);
        Intent it = getIntent();
        int num = it.getIntExtra("listType",0);//获取失败，返回0
        System.out.println("num:" + num);
        if(num != 0){
            if(num == MY_CREATED_LIST){
                mCollectAlbum.setVisibility(View.GONE);
            }
        }

        mPlayAllMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myService.setMusicId(musicListId);
                Intent intent = new Intent(AlbumListActivity.this, PlayMusicActivity.class);
                startActivity(intent);
            }
        });

    }
}
