package com.example.lolita.acitvities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lolita.R;
import com.example.lolita.adapters.MusicGridAdapter;
import com.example.lolita.adapters.MusicListAdapter;
import com.example.lolita.views.GridSpaceItemDecoration;
import com.example.lolita.views.MainHomeFragment;
import com.example.lolita.views.MainMusicFragment;

public class MainActivity extends AppCompatActivity {
 private RecyclerView mRvGrid, mRvlist;
 private MusicGridAdapter mGridAdapter;
 private MusicListAdapter mListAdapter;
 private ImageView mIvHome, mIvMainHome, mIvMainMusic,mIvSearch;
 private FragmentManager fragmentManager ;
 private FragmentTransaction transaction;
 private LinearLayout layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化各种控件，一开始放mainmusic页面
        initView();

        //点击图标，显示相应页面，包括页面的跳转
        newActivity();

    }

    private void newActivity(){


        mIvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO 添加跳转的activity
            }
        });

    }
//    控件的监听
   public void onSelectClick(View target){

        switch (target.getId()){
            case R.id.main_home:
               layouts = findViewById(R.id.fg_mainHome);

                fragmentManager = this.getSupportFragmentManager();
                mIvMainHome.setSelected(true);
                mIvMainMusic.setSelected(false);

                transaction = fragmentManager.beginTransaction();
                Fragment homeFragment = new MainHomeFragment();
                transaction.replace(R.id.container, homeFragment);
                transaction.commit();
                break;
            case R.id.main_music:
                layouts = findViewById(R.id.fg_mainHome);

                fragmentManager = this.getSupportFragmentManager();
                mIvMainMusic.setSelected(true);
                mIvMainHome.setSelected(false);

                transaction = fragmentManager.beginTransaction();
                Fragment musicFragment = new MainMusicFragment();
                transaction.replace(R.id.container, musicFragment);
                transaction.commit();
                break;


        }

   }
    private  void initView(){

        mIvHome = findViewById(R.id.iv_home);
        mIvSearch = findViewById(R.id.search);
        mIvMainHome = findViewById(R.id.main_home);
        mIvMainMusic = findViewById(R.id.main_music);
        layouts = findViewById(R.id.fg_mainMusic);

        fragmentManager = this.getSupportFragmentManager();
        mIvMainHome.setSelected(true);

        transaction = fragmentManager.beginTransaction();
        Fragment fragment = new MainHomeFragment();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


}
