package com.example.wk.sigah.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.Room;
import com.example.wk.sigah.model.roomView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class FragmentRoomView extends android.support.v4.app.Fragment {

    View view;
    private RecyclerView myRecycleView;
    private List<roomView>listRoom;
    private List<Room> myListRoom;

    public FragmentRoomView(List<Room> myListRoom) {
        this.myListRoom = myListRoom;
    }

    public FragmentRoomView() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_roomview,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.room_view);
        RecycleViewAdapterRoom recycleViewAdapterRoom =new RecycleViewAdapterRoom(getContext(),listRoom);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapterRoom);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listRoom= new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();

        for(int i=0;i<myListRoom.size();i++){
            Double tempMoney=Double.parseDouble(myListRoom.get(i).getHarga());
            String money=nf.format(tempMoney);
            if(myListRoom.get(i).getTipeKamar().equals("Superior")){
                listRoom.add(new roomView(myListRoom.get(i).getTipeKamar(),
                        myListRoom.get(i).getAlamat(),
                        money,
                        R.drawable.room_superior,
                        myListRoom.get(i).getDeskripsi()));
            }
            else if(myListRoom.get(i).getTipeKamar().equals("Deluxe")){
                listRoom.add(new roomView(myListRoom.get(i).getTipeKamar(),
                        myListRoom.get(i).getAlamat(),
                        money,
                        R.drawable.room_doubledeluxe,
                        myListRoom.get(i).getDeskripsi()));
            }else if(myListRoom.get(i).getTipeKamar().equals("Executive Deluxe")){
                listRoom.add(new roomView(myListRoom.get(i).getTipeKamar(),
                        myListRoom.get(i).getAlamat(),
                        money,
                        R.drawable.room_executivedeluxe,
                        myListRoom.get(i).getDeskripsi()));
            }else{
                listRoom.add(new roomView(myListRoom.get(i).getTipeKamar(),
                        myListRoom.get(i).getAlamat(),
                        money,
                        R.drawable.room_juniorsuite,
                        myListRoom.get(i).getDeskripsi()));
            }
        }
    }


}
