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

import com.example.lolita.R;
import com.example.lolita.Services.MusicServices;
import com.example.lolita.acitvities.HomeActivity;
import com.example.lolita.adapters.MusicGridAdapter;
import com.example.lolita.adapters.MusicListAdapter;

import java.util.Objects;

public class MainMusicFragment extends Fragment {
    private RecyclerView mRvGrid, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private View rootView;//要绑定的layout
    private int MAIN_MUSIC_LIST = 4;
    private MusicServices.MusicBinder mBinder;
    private MusicServices myService;
    private MyConnection myConnection;
    private final String TAG = "MainMusicFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mainmusic, container, false);
        //加载recyclerView里的相关item
        initView();
        bindMusicService();
        return rootView;
    }
   public void onDestroyView() {

       super.onDestroyView();
       unBindMusicService();
   }
    private  void initView(){

        //推荐歌单
        mRvGrid = rootView.findViewById(R.id.rv_grid_music);
        //设置显示的列数
        mRvGrid.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), mRvGrid));//记笔记，默认1dp的分割线
//      取消局部的滚动条
        mRvGrid.setNestedScrollingEnabled(false);
        mGridAdapter = new MusicGridAdapter(getActivity());
//          记笔记

        mRvGrid.setAdapter(mGridAdapter);

        //推荐音乐
//        不知道recyclerView的高度的话，需要手动计算
        mRvList = rootView.findViewById(R.id.rv_list_music);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));//线性布局
        mRvList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        取消局部滚动条
        //  mRvlist.setNestedScrollingEnabled(false);
//
        mListAdapter = new MusicListAdapter(getActivity(), mRvList, MAIN_MUSIC_LIST);
        mRvList.setAdapter(mListAdapter);


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
        myConnection = new MyConnection();
       getActivity().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG,"mBinder" + mBinder);
    }
    private void unBindMusicService(){
    getActivity().unbindService(myConnection);
        myService = null;
    }

}
