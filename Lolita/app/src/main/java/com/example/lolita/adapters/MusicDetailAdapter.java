package com.example.lolita.adapters;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lolita.R;

import java.util.ArrayList;

public class MusicDetailAdapter  extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> item;
    private ArrayList<String> item2;
    public MusicDetailAdapter(Context context, ArrayList<String> item,ArrayList<String> item2){
          mContext = context;
          this.item = item;
          this.item2 = item2;
          inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ItemView holder;
         if(convertView == null){
             holder = new ItemView();
             convertView = inflater.inflate(R.layout.item_list_detail,null);
             holder.musicNameText = convertView.findViewById(R.id.music_name_detail);
             holder.singerNameText= convertView.findViewById(R.id.singer_detail);
             convertView.setTag(holder);
         }else {
             holder = (ItemView)convertView.getTag();
         }
         holder.musicNameText.setText(item.get(position));
         holder.singerNameText.setText(item2.get(position));
        return convertView;
    }
     class ItemView{
        protected TextView musicNameText;
        protected TextView singerNameText;
        public ItemView(){
            super();
        }


     }
}
