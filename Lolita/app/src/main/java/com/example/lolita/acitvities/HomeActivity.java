package com.example.lolita.acitvities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lolita.R;
import com.example.lolita.utils.UserUtils;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
    }
    private void initView(){
        initNavBar(true, "个人中心");
    }
    /*
    * 修改密码
    * */
    public void onChangeClick(View v){
       startActivity(new Intent(this, ChangePasswdActivity.class));
    }
    /*退出登录*/
    // TODO 销毁session，android应该有类似的吧。。。
    public void onLogoutClick(View v){
        UserUtils.logout(this);
    }
}
