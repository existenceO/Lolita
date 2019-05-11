package com.example.lolita.acitvities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lolita.R;

public class BaseActivity extends Activity {
    private ImageView mIvBack, mIvHome,mIvMainHome, mIvMainMusic,mIvSearch;
    private TextView mTvTitle;


    protected void initNavBar(boolean isShowBack,String title){
           mIvBack = findViewById(R.id.Iv_back);
           mTvTitle= findViewById(R.id.Tv_Title);
        //   mIvHome = findViewById(R.id.Iv_Home);

           mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
//           mIvHome.setVisibility(isShowHome ? View.VISIBLE : View.GONE);
           mTvTitle.setText(title);

           mIvBack.setOnClickListener(
                   new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           onBackPressed();
                       }
                   }
           );
           /*mIvHome.setOnClickListener(
           new View.OnClickListener(){

                       @Override
                       public void onClick(View v) {
                           startActivity(new Intent(BaseActivity.this, HomeActivity.class));
                       }
                   }
           );*/
    }



}
