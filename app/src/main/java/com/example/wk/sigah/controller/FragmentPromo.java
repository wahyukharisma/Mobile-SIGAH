package com.example.wk.sigah.controller;


import android.app.ProgressDialog;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.SeasonAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.Season;
import com.example.wk.sigah.model.SeasonList;
import com.example.wk.sigah.model.seasonView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class FragmentPromo extends Fragment {

    private TextView txtDate;
    private String currendtDateTimeString= DateFormat.getDateTimeInstance().format(new Date());
    private RecyclerView myRecycleView;
    RecycleViewAdapterSeason recycleViewAdapterSeason;
    private List<seasonView> listSeason;
    public List<Season> getData;

    public FragmentPromo(List<Season> getData) {
        this.getData = getData;
    }

    public FragmentPromo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_promo,container,false);
        //Set date in Text View
        txtDate=(TextView)view.findViewById(R.id.txtDateTime);
        txtDate.setText(currendtDateTimeString);

        myRecycleView=(RecyclerView)view.findViewById(R.id.season_view);
        recycleViewAdapterSeason =new RecycleViewAdapterSeason(getContext(),listSeason);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterSeason);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listSeason=new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();

        for(int i=0;i<getData.size();i++){
            Double tempMoney=Double.parseDouble(getData.get(i).getHarga());
            String money=nf.format(tempMoney);

            if(getData.get(i).getTipeKamar().equals("Superior")){
                listSeason.add(new seasonView(getData.get(i).getTanggalMulai(),
                        getData.get(i).getTanggalSelesai(),
                        getData.get(i).getNamaSeason(),
                        R.drawable.room_superior,money,
                        getData.get(i).getTipeKamar()));
            }
            else if(getData.get(i).getTipeKamar().equals("Deluxe")){
                listSeason.add(new seasonView(getData.get(i).getTanggalMulai(),
                        getData.get(i).getTanggalSelesai(),
                        getData.get(i).getNamaSeason(),
                        R.drawable.room_doubledeluxe,money,
                        getData.get(i).getTipeKamar()));
            }
            else if(getData.get(i).getTipeKamar().equals("Executive Deluxe")){
                listSeason.add(new seasonView(getData.get(i).getTanggalMulai(),
                        getData.get(i).getTanggalSelesai(),
                        getData.get(i).getNamaSeason(),
                        R.drawable.room_executivedeluxe,money,
                        getData.get(i).getTipeKamar()));
            }else{
                listSeason.add(new seasonView(getData.get(i).getTanggalMulai(),
                        getData.get(i).getTanggalSelesai(),
                        getData.get(i).getNamaSeason(),
                        R.drawable.room_juniorsuite,money,
                        getData.get(i).getTipeKamar()));
            }
        }


    }


}
