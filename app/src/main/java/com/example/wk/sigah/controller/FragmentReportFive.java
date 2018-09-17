package com.example.wk.sigah.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.IncomeBranch;
import com.example.wk.sigah.model.bestPelangganView;
import com.example.wk.sigah.model.incomeBranchView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kharisma on 18/04/2018.
 */

public class FragmentReportFive extends Fragment {
    public List<IncomeBranch> getData;
    public List<incomeBranchView> listIncome;
    RecyclerView myRecycleView;
    RecycleViewAdapterReport5 recycleViewAdapterReport5;
    TextView txtTotalAll;

    public FragmentReportFive(List<IncomeBranch> getData) {
        this.getData = getData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_report_five,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.reportView);
        recycleViewAdapterReport5 =new RecycleViewAdapterReport5(getContext(),listIncome);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterReport5);

        NumberFormat nf=NumberFormat.getInstance();
        Double totalAll=0.0;
        for (int i = 0; i < getData.size(); i++) {
            Double total=0.0;
            //Yogyakarta
            String moneyYogyakarta="0";
            if(getData.get(i).getYogyakarta()!=null){
                Double tempMoneyYogyakarta=Double.parseDouble(getData.get(i).getYogyakarta());
                total=total+tempMoneyYogyakarta;
            }else{
                Double tempMoneyYogyakarta=Double.parseDouble(moneyYogyakarta);
                total=total+tempMoneyYogyakarta;
            }
            //Bandung
            String moneyBandung="0";
            if(getData.get(i).getBandung()!=null){
                Double tempMoneyBandung=Double.parseDouble(getData.get(i).getBandung());
                total=total+tempMoneyBandung;
            }else{
                Double tempMoneyBandung=Double.parseDouble(moneyBandung);
                total=total+tempMoneyBandung;
            }
            totalAll=totalAll+total;
        }


        txtTotalAll=(TextView)view.findViewById(R.id.txtTotAll);
        String tempTotAll=String.valueOf(totalAll);
        txtTotalAll.setText(tempTotAll);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listIncome =new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();

        for (int i = 0; i < getData.size(); i++) {
            Double total=0.0;
            //Yogyakarta
            String moneyYogyakarta="0";
            if(getData.get(i).getYogyakarta()!=null){
                Double tempMoneyYogyakarta=Double.parseDouble(getData.get(i).getYogyakarta());
                moneyYogyakarta=nf.format(tempMoneyYogyakarta);
                total=total+tempMoneyYogyakarta;
            }else{
                Double tempMoneyYogyakarta=Double.parseDouble(moneyYogyakarta);
                total=total+tempMoneyYogyakarta;
            }
            //Bandung
            String moneyBandung="0";
            if(getData.get(i).getBandung()!=null){
                Double tempMoneyBandung=Double.parseDouble(getData.get(i).getBandung());
                moneyBandung=nf.format(tempMoneyBandung);
                total=total+tempMoneyBandung;
            }else{
                Double tempMoneyBandung=Double.parseDouble(moneyBandung);
                total=total+tempMoneyBandung;
            }
            String totalTemp=nf.format(total);

            listIncome.add(new incomeBranchView(Integer.toString(i+1),
                        moneyYogyakarta,
                        moneyBandung,totalTemp,getData.get(i).getYear()));
        }
    }
}
