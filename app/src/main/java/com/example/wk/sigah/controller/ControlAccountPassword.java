package com.example.wk.sigah.controller;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wk.sigah.R;

public class ControlAccountPassword extends AppCompatActivity {
    private TabLayout tab_Layout;
    private AppBarLayout appBarLayout;
    private ViewPager view_Pager;
    private ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_p_change_p);

        //Get Intent Data
        Intent intent=getIntent();
        String temp=intent.getStringExtra("id_pel");

        tab_Layout=(TabLayout)findViewById(R.id.tabLayout);
        appBarLayout=(AppBarLayout)findViewById(R.id.appBar);
        view_Pager=(ViewPager)findViewById(R.id.viewPager);
        img1=(ImageView)findViewById(R.id.imgBack);

        //adding fragment
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new FragmentEditProfile(temp),"Edit Profile");
        adapter.AddFragment(new FragmentChangePassword(temp),"Change Password");

        //adapter setting
        view_Pager.setAdapter(adapter);
        tab_Layout.setupWithViewPager(view_Pager);

        //onClickListener
        onClickBack(this);
    }
    public void onClickBack(ControlAccountPassword view){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
