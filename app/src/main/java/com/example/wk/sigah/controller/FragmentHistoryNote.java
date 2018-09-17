package com.example.wk.sigah.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.nota;
import com.example.wk.sigah.model.notaView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kharisma on 03/05/2018.
 */

public class FragmentHistoryNote extends Fragment {

    RecycleViewAdapterHistoryReservation recycleViewAdapterHistoryReservation;
    private RecyclerView myRecycleView;
    public List<notaView> listNota;

    public FragmentHistoryNote(List<nota> getData) {
        this.getData = getData;
    }

    public List<nota> getData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history_child,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.history_view);
        recycleViewAdapterHistoryReservation =new RecycleViewAdapterHistoryReservation(getContext(),listNota);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterHistoryReservation);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listNota=new ArrayList<>();
        for(int i=0;i<getData.size();i++){
            listNota.add(new notaView(getData.get(i).getKodeReservasi(),
                    getData.get(i).getTglReservasi(),
                    getData.get(i).getNama(),
                    getData.get(i).getAlamatPel(),
                    getData.get(i).getTglCheckIn(),
                    getData.get(i).getTglCheckOut(),
                    getData.get(i).getDewasa(),
                    getData.get(i).getAnak(),
                    getData.get(i).getTglReservasi(),
                    getData.get(i).getReservasiTipe(),
                    getData.get(i).getReservasiStatus(),
                    getData.get(i).getAlamat()));
        }
    }
}
