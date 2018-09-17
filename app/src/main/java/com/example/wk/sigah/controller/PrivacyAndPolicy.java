package com.example.wk.sigah.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wk.sigah.R;

public class PrivacyAndPolicy extends AppCompatActivity {

    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacyandpolicy);

        img1=(ImageView)findViewById(R.id.imgBackPP);
        onClickBack(this);
    }

    public void onClickBack(PrivacyAndPolicy view){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
