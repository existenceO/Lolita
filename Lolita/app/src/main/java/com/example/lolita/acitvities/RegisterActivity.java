package com.example.lolita.acitvities;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lolita.R;
import com.example.lolita.models.MusicModel;
import com.example.lolita.models.UserModel;
import com.example.lolita.views.InputView;

public class RegisterActivity extends BaseActivity {
    private InputView mPhone;
    private InputView mPwd;
    private InputView mPwdCo;
    private InputView mEmail;
    private InputView mUsername;
    private UserModel userModel;

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
       mUsername = fd(R.id.username);
       mPhone = fd(R.id.input_phone);
       mEmail = fd(R.id.input_mail);
       mPwd = fd(R.id.input_pwd);
       mPwdCo = fd(R.id.input_pwd_confirm);



    }
    //TODO 注册手机号码，两次密码的检验，写入数据库
    public void onRegisterClick(View target){
//         userModel = new UserModel();
         String setUsername = mUsername.getInputStr();
         String setPassword = mPwd.getInputStr();
         String setPasswordCo = mPwdCo.getInputStr();
         String setEmail = mEmail.getInputStr();
         String setPhone = mPhone.getInputStr();



    }

}
