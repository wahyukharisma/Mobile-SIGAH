package com.example.wk.sigah.controller;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wk.sigah.R;

public class AboutHotel extends AppCompatActivity {

    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_hotel);

        imgBack=(ImageView)findViewById(R.id.imgBackA);
        onClickBack(this);
    }

    public void onClickBack(AboutHotel v){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    finish();
                }catch (Exception e){
                    e.toString();
                }
            }
        });
    }
}
