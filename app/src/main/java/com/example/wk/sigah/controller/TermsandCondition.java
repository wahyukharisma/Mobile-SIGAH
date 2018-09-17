package com.example.wk.sigah.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wk.sigah.R;

public class TermsandCondition extends AppCompatActivity {

    ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandcondition);

        img1=(ImageView)findViewById(R.id.imgBackTC);
        onClickBack(this);
    }

    public void onClickBack(TermsandCondition view){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
