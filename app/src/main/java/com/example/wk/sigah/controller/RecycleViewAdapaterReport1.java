package com.example.wk.sigah.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.bestPelangganView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Kharisma on 14/04/2018.
 */

public class RecycleViewAdapaterReport1 extends RecyclerView.Adapter<RecycleViewAdapaterReport1.MyViewHolder> {

    Context mContext;
    List<bestPelangganView> mData;

    public RecycleViewAdapaterReport1(Context mContext, List<bestPelangganView> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.item_report1,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtPosition.setText(mData.get(position).getPosition());
        holder.txtTotalPay.setText(mData.get(position).getTotalPay());
        holder.txtName.setText(mData.get(position).getNama());
        holder.txtPoint.setText(mData.get(position).getPoin());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtPosition;
        private TextView txtName;
        private TextView txtTotalPay;
        private TextView txtPoint;
        private LinearLayout item_report1;


        public MyViewHolder(View itemView) {
            super(itemView);
            item_report1=(LinearLayout)itemView.findViewById(R.id.report1_item);
            txtName=(TextView)itemView.findViewById(R.id.txtName);
            txtTotalPay=(TextView)itemView.findViewById(R.id.txtPayment);
            txtPoint=(TextView)itemView.findViewById(R.id.txtTotalReservation);
            txtPosition=(TextView)itemView.findViewById(R.id.txtPosition);
        }
    }
}
