package com.example.wk.sigah.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.incomeBranchView;

import java.util.List;

/**
 * Created by Kharisma on 18/04/2018.
 */

public class RecycleViewAdapterReport5 extends RecyclerView.Adapter<RecycleViewAdapterReport5.MyViewHolder> {

    Context mContext;
    List<incomeBranchView> mData;

    public RecycleViewAdapterReport5(Context mContext, List<incomeBranchView> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.item_report5,parent,false);
        final MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterReport5.MyViewHolder holder, int position) {
        holder.txtNo.setText(mData.get(position).getNo());
        holder.txtYear.setText(mData.get(position).getYear());
        holder.txtTotYog.setText(mData.get(position).getTotalYogyakarta());
        holder.txtTotBan.setText(mData.get(position).getTotalBandung());
        holder.txtTotYogBan.setText(mData.get(position).getTotalYogBan());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNo;
        private TextView txtYear;
        private TextView txtTotBan;
        private TextView txtTotYog;
        private TextView txtTotYogBan;
        private TableLayout item_report5;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_report5=(TableLayout)itemView.findViewById(R.id.report5_item);
            txtNo=(TextView)itemView.findViewById(R.id.txtNo);
            txtYear=(TextView)itemView.findViewById(R.id.txtYear);
            txtTotBan=(TextView)itemView.findViewById(R.id.txtTotBan);
            txtTotYog=(TextView)itemView.findViewById(R.id.txtTotYog);
            txtTotYogBan=(TextView)itemView.findViewById(R.id.txtTotAll);

        }
    }

}
