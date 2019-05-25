package com.example.lolita.views;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;
import com.example.lolita.adapters.MusicListAdapter;

import java.util.Objects;

public class MainHomeFragment extends Fragment {
    private int MY_COLLECTED_ALBUM = 2;
    private int MY_CREATED_LIST = 3;
    View rootView;//要绑定的layout
     private RecyclerView mCollectedAlbum;
     private RecyclerView mCreatedAlbeum;
     private LinearLayout mTabCollectedAlbum, mTabCreatedAlbum;
     private ImageView mArrowCollected, mArrowCreated;
    private MusicListAdapter mCollectedListAdapter,mCreatedListAdapter;
    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private MyConnection myConnection;
    private final String TAG = "MainMusicFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mainhome, container, false);
        initView();
        bindMusicService();
        return rootView;
    }
    public  void onDestroyView() {

        super.onDestroyView();
        unBindMusicService();
    }
    private boolean isCreatedVisible = true;
    private boolean isCollectedVisible = true;
        private void initView(){
        mCollectedAlbum = rootView.findViewById(R.id.my_collected_list);
        mCreatedAlbeum = rootView.findViewById(R.id.my_created_list);
        mTabCollectedAlbum = rootView.findViewById(R.id.tabMyCollectedAlbum);
        mTabCreatedAlbum = rootView.findViewById(R.id.tabMyCreatedAlbum);
        mArrowCollected = rootView.findViewById(R.id.CollectedArrow);
        mArrowCreated = rootView.findViewById(R.id.CreatedArrow);
//        收藏歌单的recyclerView
        mCollectedAlbum.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCollectedListAdapter = new MusicListAdapter(getActivity(),mCollectedAlbum,MY_COLLECTED_ALBUM);
            mCollectedAlbum.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            mCollectedAlbum.setAdapter(mCollectedListAdapter);
//            创建歌单的recyclerView
            mCreatedAlbeum.setLayoutManager(new LinearLayoutManager(getActivity()));
            mCreatedListAdapter = new MusicListAdapter(getActivity(),mCreatedAlbeum,MY_CREATED_LIST);
            mCreatedAlbeum.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
            mCreatedAlbeum.setAdapter(mCreatedListAdapter);

        mTabCreatedAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isVisible =true;
                if(isCreatedVisible){
                    mCreatedAlbeum.setVisibility(View.VISIBLE);
                    mArrowCreated.setSelected(true);
                    isCreatedVisible = false;
                }else{
                    mCreatedAlbeum.setVisibility(View.GONE);
                    mArrowCreated.setSelected(false);
                    isCreatedVisible = true;
                }
            }
        });
            mTabCollectedAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                isVisible =true;
                    if(isCollectedVisible){
                        mCollectedAlbum.setVisibility(View.VISIBLE);
                        mArrowCollected.setSelected(true);
                        isCollectedVisible = false;
                    }else{
                        mCollectedAlbum.setVisibility(View.GONE);
                        mArrowCollected.setSelected(false);
                        isCollectedVisible = true;
                    }
                }
            });

        }
    class MyConnection implements ServiceConnection {
        private  final String CONTAG = "playMusicConnection";

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(CONTAG,"onServiceConnected");
//           获取binder的对象，调用binder的自定义方法，获取Service对象
            mBinder = (MusicServices.MusicBinder) service;
            myService = mBinder.getMusicService();

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
        Intent intent = new Intent(getActivity(), MusicServices.class);
        myConnection = new MainHomeFragment.MyConnection();
        Objects.requireNonNull(getActivity()).bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG,"mBinder" + mBinder);
    }
    private void unBindMusicService(){
        Objects.requireNonNull(getActivity()).unbindService(myConnection);
        myService = null;
    }
}
