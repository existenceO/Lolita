package com.example.lolita.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lolita.R;
import com.example.lolita.acitvities.AlbumListActivity;
import com.example.lolita.acitvities.PlayMusicActivity;
/**
 *推荐音乐及专辑音乐adapter
 */


public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context mContext;
    private View mItemView;
    private  PopupWindow popupWindow,popupAddToCollection;
    private RecyclerView mRv;
    private LinearLayout ivMore;
    private LinearLayout mAddCollectionList;
    private boolean isCalculationRecyclerView;
    private View contentView;
    private int mlistType;//个人创建的歌单or专辑歌单
    public MusicListAdapter (Context context, RecyclerView recyclerView, int listType){

        mContext = context;
        mRv = recyclerView;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_music,viewGroup, false);
         contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_music_detail,null);
//        LinearLayout add_collection = contentView.findViewById(R.id.add_collection);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
//        popupWindow.setBackgroundDrawable(contentView.getResources().getDrawable(android.R.color.transparent));
        popupWindow.setBackgroundDrawable(new BitmapDrawable(contentView.getResources(), (Bitmap) null));

        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int ViewType) {
        setRecyclerViewHeight();
//        加载网络图片，服务器我的PC，没开就加载不到
//       TODO 多张图片的加载
        Glide.with(mContext)
                .load("http://192.168.180.83:8089/goodday/image/photo/a2-s.jpg")
                .into(viewHolder.ivIcon);
        /**
         * 给ivMore添加监听事件，用户点击了，就弹出小页面*/
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    setBackgroudAlpha(0.7f);
                    popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        setBackgroudAlpha(1f);
                    }
                });
                    mAddCollectionList.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
                        }
                    });
//                popupWindow.showAsDropDown(mRv);
//                Toast.makeText(mContext, "被点击了", Toast.LENGTH_SHORT).show();
                Log.e("viewType",Integer.toString(ViewType));
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 改为点击音乐
                 Intent intent = new Intent(mContext, PlayMusicActivity.class);
                 mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }
    /**
      设置弹窗之后的背景变暗
     */
     private void setBackgroudAlpha(float alpha){
         WindowManager.LayoutParams layoutParams = ((Activity)mContext).getWindow().getAttributes();
         layoutParams.alpha = alpha; //0.0-1.0
         ((Activity)mContext).getWindow().setAttributes(layoutParams);

     }
    /**
    * 1. 获取itemView的高度
     * 2.获取itemView的数量
     * 3. itemViewHeght * itemView 得到recyclerView的高度*/
    private void setRecyclerViewHeight(){

        if(isCalculationRecyclerView || mRv == null)
            return ;
//              获取itemView的高度
        isCalculationRecyclerView = true;
             RecyclerView.LayoutParams ItemViewLp = (RecyclerView.LayoutParams) mItemView.getLayoutParams();
             int itemCount = getItemCount();
             int recyclerViewHeight = ItemViewLp.height * itemCount;

             //设置recyclerView的高度
        LinearLayout.LayoutParams rvLp = (LinearLayout.LayoutParams) mRv.getLayoutParams();
        rvLp.height = recyclerViewHeight;
        mRv.setLayoutParams(rvLp);

    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;
         View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            ivMore = itemView.findViewById(R.id.music_more);

            mAddCollectionList = contentView.findViewById(R.id.add_collection);

        }
    }
}
