package com.example.lolita.acitvities;

import android.service.notification.ConditionProviderService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lolita.R;
import com.example.lolita.adapters.MusicListAdapter;

public class AlbumListActivity extends BaseActivity {

     private RecyclerView mRvList;
     private MusicListAdapter mListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }
    private void initView(){
        initNavBar(true,"专辑列表");

        mRvList = findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mListAdapter = new MusicListAdapter( this, null );
        mRvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRvList.setAdapter(mListAdapter);

    }
}
