package com.example.lolita.views;

import android.content.Context;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;


public class WEqualsHImageView extends AppCompatImageView{

    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public WEqualsHImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写onMeasure方法
    //记笔记
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        //获取view宽度
       /* int width = MeasureSpec.getSize(widthMeasureSpec);
        //获取view模式
//        可以是match_parent, wrap_content or 具体dp
        int mode = MeasureSpec.getMode(widthMeasureSpec);//??*/

    }




}
