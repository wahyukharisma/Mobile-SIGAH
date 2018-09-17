package com.example.wk.sigah.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.Reservation;
import com.example.wk.sigah.model.historyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kharisma on 27/04/2018.
 */

public class FragmentHistoryChild extends Fragment {

    public List<Reservation> getData;
    public List<historyView> listHistory;
    RecycleViewAdapterHistory recycleViewAdapterHistory;
    private RecyclerView myRecycleView;

    public FragmentHistoryChild(List<Reservation> getData) {
        this.getData = getData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history_child,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.history_view);
        recycleViewAdapterHistory =new RecycleViewAdapterHistory(getContext(),listHistory);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterHistory);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tempBundle;
        tempBundle=getArguments().getString("status");
        listHistory=new ArrayList<>();

        if(tempBundle.equals("Booking")){
            for(int i=0;i<getData.size();i++){
                if(getData.get(i).getReservasiStatus().equals("Booking")){
                    listHistory.add(new historyView(getData.get(i).getKodeReservasi(),
                            getData.get(i).getReservasiTipe(),
                            getData.get(i).getTglReservasi(),
                            getData.get(i).getAlamat(),
                            getData.get(i).getReservasiStatus()));
                }
            }
        }else{
            for(int i=0;i<getData.size();i++){
                if(getData.get(i).getReservasiStatus().equals("Sukses")){
                    listHistory.add(new historyView(getData.get(i).getKodeReservasi(),
                            getData.get(i).getReservasiTipe(),
                            getData.get(i).getTglReservasi(),
                            getData.get(i).getAlamat(),
                            getData.get(i).getReservasiStatus()));
                }
            }
        }

    }
}
