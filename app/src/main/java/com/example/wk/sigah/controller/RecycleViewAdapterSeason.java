package com.example.wk.sigah.controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.seasonView;

import java.util.List;

/**
 * Created by Kharisma on 06/04/2018.
 */

public class RecycleViewAdapterSeason extends RecyclerView.Adapter<RecycleViewAdapterSeason.MyViewHolder> {

    Context mContext;
    List<seasonView> mData;
    Dialog myDialog;

    public RecycleViewAdapterSeason(Context mContext, List<seasonView> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecycleViewAdapterSeason.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.item_seasonview,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //init dialog
        myDialog=new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_season);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_season.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv=(TextView)myDialog.findViewById(R.id.txtSeasonName);
                TextView dialog_price_tv=(TextView)myDialog.findViewById(R.id.txtPriceSeason);
                TextView dialog_start_tv=(TextView)myDialog.findViewById(R.id.txtStartDate);
                TextView dialog_end_tv=(TextView)myDialog.findViewById(R.id.txtEndDate);
                TextView dialog_room_tv=(TextView)myDialog.findViewById(R.id.txtRoomNameSeason);
                ImageView dialog_image=(ImageView)myDialog.findViewById(R.id.imgRoomSeason);
                //find by id dialog
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_price_tv.setText(mData.get(vHolder.getAdapterPosition()).getPrice());
                dialog_start_tv.setText(mData.get(vHolder.getAdapterPosition()).getStart_date());
                dialog_end_tv.setText(mData.get(vHolder.getAdapterPosition()).getEnd_date());
                dialog_room_tv.setText(mData.get(vHolder.getAdapterPosition()).getRoomName());
                dialog_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getImage());
                myDialog.show();


            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterSeason.MyViewHolder holder, int position) {
        holder.txtSeasonName.setText(mData.get(position).getName());
        holder.txtPriceSeason.setText(mData.get(position).getPrice());
        holder.txtStartDate.setText(mData.get(position).getStart_date());
        holder.txtEndDate.setText(mData.get(position).getEnd_date());
        holder.txtRoomName.setText(mData.get(position).getRoomName());
        holder.img.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_season;
        private TextView txtSeasonName;
        private TextView txtPriceSeason;
        private TextView txtStartDate;
        private TextView txtEndDate;
        private TextView txtRoomName;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_season=(LinearLayout)itemView.findViewById(R.id.season_id);
            txtSeasonName=(TextView)itemView.findViewById(R.id.txtSeasonName);
            txtStartDate=(TextView)itemView.findViewById(R.id.txtDateStart);
            txtEndDate=(TextView)itemView.findViewById(R.id.txtDateEnd);
            txtPriceSeason=(TextView)itemView.findViewById(R.id.txtPriceSeason);
            txtRoomName=(TextView)itemView.findViewById(R.id.txtRoomNameSeason);
            img=(ImageView)itemView.findViewById(R.id.itmPictureSeason);
        }
    }
}
