package com.example.wk.sigah.controller;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.RoomAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.Room;
import com.example.wk.sigah.model.RoomFacilityList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecycleViewRF extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private String URL;
    private GetURL getURL=new GetURL(URL);
    public List<Room> getMyRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_rf);

        tabLayout=(TabLayout)findViewById(R.id.tab_Layout);
        viewPager=(ViewPager)findViewById(R.id.viewPager_id);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        //add Fragment
        getRoom();
    }

    public void getRoom(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomAPI api=retrofit.create(RoomAPI.class);
        Call<RoomFacilityList> call=api.viewRoom();

        call.enqueue(new Callback<RoomFacilityList>() {
            @Override
            public void onResponse(Call<RoomFacilityList> call, Response<RoomFacilityList> response) {
                viewPagerAdapter.AddFragment(new FragmentRoomView(response.body().getResult()),"Room List");
                viewPagerAdapter.AddFragment(new FragmentFacilityView(response.body().getResults()),"Paid Facility List");
                getMyRoom=response.body().getResult();
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                tabLayout.getTabAt(0).setIcon(R.drawable.room_icon);
                tabLayout.getTabAt(1).setIcon(R.drawable.plus_icon);
            }
            @Override
            public void onFailure(Call<RoomFacilityList> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(RecycleViewRF.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
