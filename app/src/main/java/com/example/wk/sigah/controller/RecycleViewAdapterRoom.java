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
import com.example.wk.sigah.model.roomView;

import java.util.List;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class RecycleViewAdapterRoom extends RecyclerView.Adapter<RecycleViewAdapterRoom.MyViewHolder> {

    Context mContex;
    List<roomView> mData;
    Dialog myDialog;

    public RecycleViewAdapterRoom(Context mContex, List<roomView> mData) {
        this.mContex = mContex;
        this.mData = mData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view= LayoutInflater.from(mContex).inflate(R.layout.item_roomview,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //init my dialog
        myDialog=new Dialog(mContex);
        myDialog.setContentView(R.layout.dialog_room);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv=(TextView)myDialog.findViewById(R.id.txtRoomName);
                TextView dialog_price_tv=(TextView)myDialog.findViewById(R.id.txtPriceRoom);
                TextView dialog_desc_tv=(TextView)myDialog.findViewById(R.id.txtDescDetil);
                ImageView dialog_image=(ImageView)myDialog.findViewById(R.id.imgDetilRoom);
                //findbyid dialog
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getRoomtype());
                dialog_price_tv.setText(mData.get(vHolder.getAdapterPosition()).getPrice());
                dialog_desc_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescriptonDetil());
                dialog_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtRoomType.setText(mData.get(position).getRoomtype());
        holder.txtDescrpiton.setText(mData.get(position).getDescription());
        holder.txtPrice.setText(mData.get(position).getPrice());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_room;
        private TextView txtRoomType;
        private TextView txtDescrpiton;
        private TextView txtPrice;
        private ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_room=(LinearLayout)itemView.findViewById(R.id.room_id);
            txtRoomType=(TextView)itemView.findViewById(R.id.txtRoomType);
            txtDescrpiton=(TextView)itemView.findViewById(R.id.txtDesc);
            txtPrice=(TextView)itemView.findViewById(R.id.txtPrice);
            img=(ImageView)itemView.findViewById(R.id.itmPicture);
        }
    }
}
