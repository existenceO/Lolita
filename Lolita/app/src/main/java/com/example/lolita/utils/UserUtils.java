package com.example.lolita.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.lolita.R;
import com.example.lolita.acitvities.LoginActivity;

public class UserUtils {
    /*
    * 验证用户输入的合法性*/
    public static boolean validateLogin(Context context, String phone, String pwd){
//        简单
//     RegexUtils.isMobileSimple(phone);
//       精确
     RegexUtils.isMobileExact(phone);
        if(! RegexUtils.isMobileExact(phone)){
            Toast.makeText(context, "无效手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(context, "请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /*
    * 退出登录*
    */
    public static void logout (Context context){

        Intent intent = new Intent(context, LoginActivity.class);
        //添加intent flag, 情况task栈并新建task栈，但是导致过度动画出问题
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //overridePendingTransition要在startActivity后使用
        //定义activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }
}
