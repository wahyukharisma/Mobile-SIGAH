package com.example.wk.sigah.controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.notaView;

import java.util.List;

/**
 * Created by Kharisma on 03/05/2018.
 */

public class RecycleViewAdapterHistoryReservation extends RecyclerView.Adapter<RecycleViewAdapterHistoryReservation.MyViewHolder>{

    Context mContext;
    List<notaView> mData;
    Dialog myDialog;

    public RecycleViewAdapterHistoryReservation(Context mContext, List<notaView> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.item_history_reservation,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //Init Dialog
        myDialog=new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_history_reservation);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_history_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView txtIDBooking,txtDateOrder,txtNameOrder,txtAddress,txtCheckIn,txtCheckOut,txtChild,txtAdult,txtPaymentDate;

                //Finding ID
                txtIDBooking=(TextView)myDialog.findViewById(R.id.txtIDBooking);
                txtDateOrder=(TextView)myDialog.findViewById(R.id.txtDateOrder);
                txtNameOrder=(TextView)myDialog.findViewById(R.id.txtNameOrder);
                txtAddress=(TextView)myDialog.findViewById(R.id.txtAddress);
                txtCheckIn=(TextView)myDialog.findViewById(R.id.txtCheckIn);
                txtCheckOut=(TextView)myDialog.findViewById(R.id.txtCheckOut);
                txtChild=(TextView)myDialog.findViewById(R.id.txtChild);
                txtAdult=(TextView)myDialog.findViewById(R.id.txtAdult);
                txtPaymentDate=(TextView)myDialog.findViewById(R.id.txtPaymentDate);

                //Set Text Dialog
                txtIDBooking.setText(mData.get(vHolder.getAdapterPosition()).getId_booking());
                txtDateOrder.setText(mData.get(vHolder.getAdapterPosition()).getTanggal());
                txtNameOrder.setText(mData.get(vHolder.getAdapterPosition()).getNama());
                txtAddress.setText(mData.get(vHolder.getAdapterPosition()).getAlamatPel());
                txtCheckIn.setText(mData.get(vHolder.getAdapterPosition()).getCheckIn());
                txtCheckOut.setText(mData.get(vHolder.getAdapterPosition()).getCheckOut());
                txtChild.setText(mData.get(vHolder.getAdapterPosition()).getAnak());
                txtAdult.setText(mData.get(vHolder.getAdapterPosition()).getDewasa());
                txtPaymentDate.setText(mData.get(vHolder.getAdapterPosition()).getTanggal());

                myDialog.show();

            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtIDBooking.setText(mData.get(position).getId_booking());
        holder.txtLocation.setText(mData.get(position).getAlamatHotel());
        holder.txtResType.setText(mData.get(position).getTipeReservasi());
        holder.txtDateRes.setText(mData.get(position).getTanggal());
        holder.txtStatus.setText(mData.get(position).getStatusReservasi());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtIDBooking;
        private TextView txtLocation;
        private TextView txtResType;
        private TextView txtDateRes;
        private TextView txtStatus;
        private LinearLayout item_history_res;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_history_res=(LinearLayout)itemView.findViewById(R.id.history_reservation_id);
            txtIDBooking=(TextView)itemView.findViewById(R.id.txtIdBooking);
            txtLocation=(TextView)itemView.findViewById(R.id.txtLocation);
            txtResType=(TextView)itemView.findViewById(R.id.txtTipeRes);
            txtDateRes=(TextView)itemView.findViewById(R.id.txtDateRes);
            txtStatus=(TextView)itemView.findViewById(R.id.txtStatus);
        }
    }
}
