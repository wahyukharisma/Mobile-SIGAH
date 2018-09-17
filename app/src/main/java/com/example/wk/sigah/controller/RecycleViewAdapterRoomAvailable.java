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
import com.example.wk.sigah.model.roomFilterView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kharisma on 10/04/2018.
 */

public class RecycleViewAdapterRoomAvailable extends RecyclerView.Adapter<RecycleViewAdapterRoomAvailable.MyViewHolder>{

    Context mContex;
    List<roomFilterView> mData;
    Dialog myDialog;

    public RecycleViewAdapterRoomAvailable(Context mContex, List<roomFilterView> mData) {
        this.mContex = mContex;
        this.mData = mData;
    }

    @Override
    public RecycleViewAdapterRoomAvailable.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContex).inflate(R.layout.item_roomavailable,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //init dialog
        myDialog=new Dialog(mContex);
        myDialog.setContentView(R.layout.dialog_roomavailable);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_roomFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_r_number=(TextView)myDialog.findViewById(R.id.txtNumberRoom);
                TextView dialog_r_type=(TextView)myDialog.findViewById(R.id.txtRoomType);
                TextView dialog_r_capacity=(TextView)myDialog.findViewById(R.id.txtRoomCapacity);
                TextView dialog_r_smoking=(TextView)myDialog.findViewById(R.id.txtRoomSmooking);
                TextView dialog_r_floor=(TextView)myDialog.findViewById(R.id.txtRoomFloor);
                ImageView dialog_r_image=(ImageView)myDialog.findViewById(R.id.imgRoomAvailable);
                TextView dialog_r_bed=(TextView)myDialog.findViewById(R.id.txtBedType);
                TextView dialog_r_price=(TextView)myDialog.findViewById(R.id.txtPrice);
                TextView dialog_r_description=(TextView)myDialog.findViewById(R.id.txtDescription);
                //find dialog
                dialog_r_bed.setText(mData.get(vHolder.getAdapterPosition()).getJenis_kasur());
                dialog_r_capacity.setText(mData.get(vHolder.getAdapterPosition()).getKapasitas());
                dialog_r_description.setText(mData.get(vHolder.getAdapterPosition()).getDeskripsi());
                dialog_r_floor.setText(mData.get(vHolder.getAdapterPosition()).getLantai());
                dialog_r_number.setText(mData.get(vHolder.getAdapterPosition()).getNomor_ruangan());
                dialog_r_price.setText(mData.get(vHolder.getAdapterPosition()).getHarga());
                dialog_r_smoking.setText(mData.get(vHolder.getAdapterPosition()).getStatus_merokok());
                dialog_r_type.setText(mData.get(vHolder.getAdapterPosition()).getTipe_kamar());
                dialog_r_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getGambar());
                myDialog.show();
            }
        });
        return vHolder;

    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterRoomAvailable.MyViewHolder holder, int position) {
        holder.txtNumberRoom.setText(mData.get(position).getNomor_ruangan());
        holder.txtRoomType.setText(mData.get(position).getTipe_kamar());
        holder.txtPrice.setText(mData.get(position).getHarga());
        holder.imgRoom.setImageResource(mData.get(position).getGambar());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_roomFilter;
        private TextView txtRoomType;
        private TextView txtNumberRoom;
        private TextView txtPrice;
        private ImageView imgRoom;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_roomFilter=(LinearLayout)itemView.findViewById(R.id.roomAvailable_id);
            txtRoomType=(TextView)itemView.findViewById(R.id.txtRoomType);
            txtNumberRoom=(TextView)itemView.findViewById(R.id.txtNumberRoom);
            txtPrice=(TextView)itemView.findViewById(R.id.txtPrice);
            imgRoom=(ImageView)itemView.findViewById(R.id.itmPicture);
        }
    }
}
