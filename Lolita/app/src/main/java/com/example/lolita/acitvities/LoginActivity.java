package com.example.lolita.acitvities;

import android.content.Intent;
import android.media.MediaRouter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.lolita.R;
import com.example.lolita.utils.UserUtils;
import com.example.lolita.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView mInputPhone, mInputPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
/*
初始化view*/

    private  void initView(){

        initNavBar(false, "登录");

        mInputPhone = findViewById(R.id.input_phone);
        mInputPwd = findViewById(R.id.input_pwd);
    }
    /*跳转注册页面*/
    public void onRegisterClick(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
    /*登录*/
    public void onCommitClick(View v){
       String phone = mInputPhone.getInputStr();
       String pwd = mInputPwd.getInputStr();
//        验证用户输入的合法性
      /* if(!UserUtils.validateLogin(this, phone, pwd)){
           return;
       }*/
//       TODO 连接数据库校验用户名密码

//       跳转到主页面
        Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
       finish();
    }
}

