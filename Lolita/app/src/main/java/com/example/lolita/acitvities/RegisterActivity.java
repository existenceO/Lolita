package com.example.lolita.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lolita.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    /*
    * 初始化view
    * */
    private void initView(){
       initNavBar(true, "注册");


    }
    //TODO 注册手机号码，两次密码的检验，写入数据库
    public void onRegisterClick(View target){

    }

}
