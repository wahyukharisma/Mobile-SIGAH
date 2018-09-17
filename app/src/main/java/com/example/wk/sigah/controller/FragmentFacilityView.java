package com.example.wk.sigah.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.Facility;
import com.example.wk.sigah.model.Room;
import com.example.wk.sigah.model.facilityView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class FragmentFacilityView extends android.support.v4.app.Fragment{
    View view;
    private RecyclerView recyclerView;
    private List<facilityView>listFacility;
    private List<Facility> myFacility;

    public FragmentFacilityView(List<Facility> myFacility) {
        this.myFacility = myFacility;
    }

    public FragmentFacilityView() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_facilityview,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.facility_view);
        RecycleViewAdapterFacility recycleViewAdapterFacility =new RecycleViewAdapterFacility(getContext(),listFacility);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recycleViewAdapterFacility);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFacility= new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();

        for(int i=0;i<myFacility.size();i++){
            Double tempMoney=Double.parseDouble(myFacility.get(i).getHarga());
            String money=nf.format(tempMoney);

            if(myFacility.get(i).getNamaFasilitas().equals("Extra Bed")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.extrabed,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Laundry Regular")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.laundry,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Laundry Fast Service")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.laundry,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Massage")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.massage,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Minibar")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.minibar,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Breakfast")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.breakfast,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Lunch")){
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.lunch,
                        myFacility.get(i).getDeskripsi()));
            }else if(myFacility.get(i).getNamaFasilitas().equals("Dinner")) {
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.dinner,
                        myFacility.get(i).getDeskripsi()));
            }else{
                listFacility.add(new facilityView(myFacility.get(i).getNamaFasilitas(),
                        myFacility.get(i).getAlamat(),
                        money,
                        R.drawable.meetingroom,
                        myFacility.get(i).getDeskripsi()));
            }
        }
    }
}
