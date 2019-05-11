package com.example.lolita.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lolita.R;
import com.example.lolita.adapters.MusicGridAdapter;
import com.example.lolita.adapters.MusicListAdapter;

public class MainMusicFragment extends Fragment {
    private RecyclerView mRvGrid, mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;
    private View rootView;//要绑定的layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mainmusic, container, false);
        //加载recyclerView里的相关item
        initView();
        return rootView;
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
        mListAdapter = new MusicListAdapter(getActivity(), mRvList);
        mRvList.setAdapter(mListAdapter);


    }

}
