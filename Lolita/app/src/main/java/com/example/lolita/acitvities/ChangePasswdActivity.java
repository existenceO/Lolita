package com.example.lolita.acitvities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lolita.R;

public class ChangePasswdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passwd);

        initView();
    }
    private void initView(){
        initNavBar(true, "修改密码");
    }
    //TODO 接收原来的密码并校验，接收新修改的密码，确认两次输入是否一致
}
