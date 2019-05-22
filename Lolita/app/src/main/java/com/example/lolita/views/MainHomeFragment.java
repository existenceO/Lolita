package com.example.lolita.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lolita.R;
import com.example.lolita.adapters.MusicListAdapter;

public class MainHomeFragment extends Fragment {
    private int MY_COLLECTED_ALBUM = 2;
    private int MY_CREATED_LIST = 3;
    View rootView;//要绑定的layout
     private RecyclerView mCollectedAlbum;
     private RecyclerView mCreatedAlbeum;
     private LinearLayout mTabCollectedAlbum, mTabCreatedAlbum;
     private ImageView mArrowCollected, mArrowCreated;
    private MusicListAdapter mCollectedListAdapter,mCreatedListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mainhome, container, false);
        initView();
        return rootView;
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
}
