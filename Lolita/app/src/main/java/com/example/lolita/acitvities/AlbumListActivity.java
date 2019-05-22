package com.example.lolita.acitvities;

import android.content.Intent;
import android.service.notification.ConditionProviderService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lolita.R;
import com.example.lolita.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

     private RecyclerView mRvList;
     private MusicListAdapter mListAdapter;
     private LinearLayout mPlayAllMusic, mCollectAlbum;
     private int ALBUM_LIST = 1;
     private int MY_CREATED_LIST = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }
    private void initView(){
        initNavBar(true,"歌单列表");

        mRvList = findViewById(R.id.rv_list);
        mCollectAlbum = findViewById(R.id.collect_list);
        mPlayAllMusic = findViewById(R.id.play_all_music);
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
                Intent intent = new Intent(AlbumListActivity.this, PlayMusicActivity.class);
                startActivity(intent);
            }
        });

    }
}
