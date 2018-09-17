package com.example.wk.sigah.controller;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.ReservationAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.Reservation;
import com.example.wk.sigah.model.ReservationList;
import com.example.wk.sigah.model.facilityView;
import com.example.wk.sigah.model.historyView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 08/04/2018.
 */

public class RecycleViewAdapterHistory extends RecyclerView.Adapter<RecycleViewAdapterHistory.MyViewHolder>{
    Context mContex;
    List<historyView> mData;
    Dialog myDialog;
    private String URL;
    private GetURL getURL=new GetURL(URL);

    public RecycleViewAdapterHistory(Context mContex, List<historyView> mData) {
        this.mContex = mContex;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContex).inflate(R.layout.item_history,parent,false);
        final MyViewHolder vHolder=new MyViewHolder(view);

        //init dialog
        myDialog=new Dialog(mContex);
        myDialog.setContentView(R.layout.dialog_history);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dialog_date_tv=(TextView)myDialog.findViewById(R.id.txtDateRes);
                final TextView dialog_id_tv=(TextView)myDialog.findViewById(R.id.txtIDRes);
                Button dialog_btn=(Button)myDialog.findViewById(R.id.btnCancel);
                //finding id dialog
                final String tempID;
                dialog_date_tv.setText(mData.get(vHolder.getAdapterPosition()).getDate_reservation());
                dialog_id_tv.setText(mData.get(vHolder.getAdapterPosition()).getId_reservation());
                tempID=dialog_id_tv.getText().toString();

                dialog_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit=new Retrofit.Builder()
                                .baseUrl(getURL.GetMyURL())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        ReservationAPI api=retrofit.create(ReservationAPI.class);
                        Call<ReservationList> call=api.cancelReservastion(tempID);
                        call.enqueue(new Callback<ReservationList>() {
                            @Override
                            public void onResponse(Call<ReservationList> call, Response<ReservationList> response) {
                                Toast.makeText(mContex,"Cancel Success",Toast.LENGTH_SHORT).show();
                                myDialog.dismiss();
                                mData.remove(Integer.valueOf(vHolder.getAdapterPosition()));
                                notifyDataSetChanged();
                            }
                            @Override
                            public void onFailure(Call<ReservationList> call, Throwable t) {
                                t.printStackTrace();
                                Toast.makeText(mContex,"Network Connection Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtIDBooking.setText(mData.get(position).getId_reservation());
        holder.txtLocation.setText(mData.get(position).getLocation());
        holder.txtResType.setText(mData.get(position).getReservastion_type());
        holder.txtDateRes.setText(mData.get(position).getDate_reservation());
        holder.txtStatus.setText(mData.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtIDBooking;
        private TextView txtLocation;
        private TextView txtResType;
        private TextView txtDateRes;
        private TextView txtStatus;
        private LinearLayout item_history;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_history=(LinearLayout)itemView.findViewById(R.id.history_id);
            txtIDBooking=(TextView)itemView.findViewById(R.id.txtIdBooking);
            txtLocation=(TextView)itemView.findViewById(R.id.txtLocation);
            txtResType=(TextView)itemView.findViewById(R.id.txtTipeRes);
            txtDateRes=(TextView)itemView.findViewById(R.id.txtDateRes);
            txtStatus=(TextView)itemView.findViewById(R.id.txtStatus);
        }
    }

}
