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
import com.example.wk.sigah.model.RoomFilter;
import com.example.wk.sigah.model.roomFilterView;
import com.example.wk.sigah.model.seasonView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kharisma on 10/04/2018.
 */

public class FragmentFilterRoomStandar extends Fragment {

    private RecyclerView myRecycleView;
    RecycleViewAdapterRoomAvailable recycleViewAdapterRoomAvailable;
    private List<roomFilterView> listFilter;

    public FragmentFilterRoomStandar(List<RoomFilter> getData) {
        this.getData = getData;
    }

    List<RoomFilter> getData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_filterroomstandar,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.roomFilter_view);
        recycleViewAdapterRoomAvailable =new RecycleViewAdapterRoomAvailable(getContext(),listFilter);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterRoomAvailable);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFilter=new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();
        String statusSmok;

        for(int i=0;i<getData.size();i++){
            Double tempMoney=Double.parseDouble(getData.get(i).getHarga());
            String money=nf.format(tempMoney);

            if(getData.get(i).getStatusMerokok().equals("1")){
               statusSmok="Yes";
            }else{
                statusSmok="No";
            }
            if(getData.get(i).getTipeKamar().equals("Superior")){
                listFilter.add(new roomFilterView(getData.get(i).getNomorRuangan(),
                        getData.get(i).getLantai(),
                        getData.get(i).getKapasitas(),
                        money,
                        getData.get(i).getDeskripsi(),
                        statusSmok,
                        getData.get(i).getAlamat(),
                        getData.get(i).getTipeKamar(),
                        getData.get(i).getJenisKasur(),
                        R.drawable.room_superior));
            }else if(getData.get(i).getTipeKamar().equals("Deluxe")){
                listFilter.add(new roomFilterView(getData.get(i).getNomorRuangan(),
                        getData.get(i).getLantai(),
                        getData.get(i).getKapasitas(),
                        money,
                        getData.get(i).getDeskripsi(),
                        statusSmok,
                        getData.get(i).getAlamat(),
                        getData.get(i).getTipeKamar(),
                        getData.get(i).getJenisKasur(),
                        R.drawable.room_doubledeluxe));
            }else if(getData.get(i).getTipeKamar().equals("Executive Deluxe")){
                listFilter.add(new roomFilterView(getData.get(i).getNomorRuangan(),
                        getData.get(i).getLantai(),
                        getData.get(i).getKapasitas(),
                        money,
                        getData.get(i).getDeskripsi(),
                        statusSmok,
                        getData.get(i).getAlamat(),
                        getData.get(i).getTipeKamar(),
                        getData.get(i).getJenisKasur(),
                        R.drawable.room_executivedeluxe));
            }else{
                listFilter.add(new roomFilterView(getData.get(i).getNomorRuangan(),
                        getData.get(i).getLantai(),
                        getData.get(i).getKapasitas(),
                        money,
                        getData.get(i).getDeskripsi(),
                        statusSmok,
                        getData.get(i).getAlamat(),
                        getData.get(i).getTipeKamar(),
                        getData.get(i).getJenisKasur(),
                        R.drawable.room_juniorsuite));
            }
        }
    }
}
