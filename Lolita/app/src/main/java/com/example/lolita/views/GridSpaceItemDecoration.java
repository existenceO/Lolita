package com.example.lolita.views;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    public GridSpaceItemDecoration(int space, RecyclerView parent){
        mSpace = space;
        getRecyclerViewOffset(parent);
    }
/**
 * @param outRect Item的矩形边界
 * @param  view ItemView
 * @param parent RecyclerView
 * @param state RecyclerView的状态
 **/
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//判断Item是不是每一行第一个Item
//        View margin属性
//        margin为正，则view距离边界会产生一个距离
//        margin为负，则view会超出边界产生一个距离
        /*if(parent.getChildLayoutPosition(view) % 3 == 0){
            outRect.left = 0;
        }else
       */
        outRect.left = mSpace;


    }
    private void getRecyclerViewOffset(RecyclerView parent){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
