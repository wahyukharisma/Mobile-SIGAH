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
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.facilityView;

import java.util.List;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class RecycleViewAdapterFacility extends RecyclerView.Adapter<RecycleViewAdapterFacility.MyViewHolder>{

    Context mContex;
    List<facilityView> mData;
    Dialog myDialog;

    public RecycleViewAdapterFacility(Context mContex, List<facilityView> mData) {
        this.mContex = mContex;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view= LayoutInflater.from(mContex).inflate(R.layout.item_facilityview,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //init my dialog
        myDialog=new Dialog(mContex);
        myDialog.setContentView(R.layout.dialog_facility);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_facility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_name_tv=(TextView)myDialog.findViewById(R.id.txtFacilityNameDetil);
                TextView dialog_price_tv=(TextView)myDialog.findViewById(R.id.txtPriceFacilityDetil);
                TextView dialog_desc_tv=(TextView)myDialog.findViewById(R.id.txtDescDetilFacility);
                ImageView dialog_image=(ImageView)myDialog.findViewById(R.id.imgDetilFacility);
                //findbyid dialog
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getFacilityname());
                dialog_price_tv.setText(mData.get(vHolder.getAdapterPosition()).getPrice());
                dialog_desc_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescription_detil());
                dialog_image.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtFacilityName.setText(mData.get(position).getFacilityname());
        holder.txtDescrpiton.setText(mData.get(position).getDescription());
        holder.txtPrice.setText(mData.get(position).getPrice());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView txtFacilityName;
        private TextView txtDescrpiton;
        private TextView txtPrice;
        private ImageView img;
        private LinearLayout item_facility;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_facility=(LinearLayout)itemView.findViewById(R.id.facility_id);
            txtFacilityName=(TextView)itemView.findViewById(R.id.txtFacilityName);
            txtDescrpiton=(TextView)itemView.findViewById(R.id.txtDescFacility);
            txtPrice=(TextView)itemView.findViewById(R.id.txtPriceFaccility);
            img=(ImageView)itemView.findViewById(R.id.imgFacility);
        }
    }
}
