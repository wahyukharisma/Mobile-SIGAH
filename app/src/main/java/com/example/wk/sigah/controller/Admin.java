package com.example.wk.sigah.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.SharedPrefManager;

public class Admin extends AppCompatActivity {

    LinearLayout reportOne,reportTwo,reportThree,reportFour,reportFive;
    TextView txtLogOut;
    private boolean isUserClickedBackButton=false;
    //Get Shared Preferences
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        reportOne=(LinearLayout)findViewById(R.id.report1);
        reportTwo=(LinearLayout)findViewById(R.id.report2);
        reportThree=(LinearLayout)findViewById(R.id.report3);
        reportFour=(LinearLayout)findViewById(R.id.report4);
        reportFive=(LinearLayout)findViewById(R.id.report5);
        txtLogOut=(TextView)findViewById(R.id.txtLogOut);

        onClickReportOne(this);
        onClickReportTwo(this);
        onClickReportThree(this);
        onClickReportFour(this);
        onClickReportFive(this);
        onClickLogOutListener(this);
    }

    public void onClickReportOne(Admin view){
        reportOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportOne=new Intent(Admin.this,AdminReportOne.class);
                startActivity(reportOne);
            }
        });
    }

    public void onClickReportTwo(Admin view){
        reportTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportTwo=new Intent(Admin.this,AdminReportTwo.class);
                startActivity(reportTwo);
            }
        });
    }
    public void onClickReportThree(Admin view){
        reportThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportThree=new Intent(Admin.this,AdminReportThree.class);
                startActivity(reportThree);
            }
        });
    }
    public void onClickReportFour(Admin view){
        reportFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportFour=new Intent(Admin.this,AdminReportFour.class);
                startActivity(reportFour);
            }
        });
    }
    public void onClickReportFive(Admin view){
        reportFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportFive=new Intent(Admin.this,AdminReportFive.class);
                startActivity(reportFive);
            }
        });
    }
    public void onClickLogOutListener(Admin view){
        txtLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(Admin.this).setTitle("Are you sure want to logout?").
                        setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        sharedPrefManager.saveSPBoolean(sharedPrefManager.SP_ALREADY_LOGIN,false);
                                        startActivity(new Intent(Admin.this,LoginActivity.class));
                                        Admin.this.finish();
                                    }
                                }).setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                ad.setCancelable(false);
                ad.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(!isUserClickedBackButton){
            Toast.makeText(this,"Press back again to logout",Toast.LENGTH_LONG).show();
            isUserClickedBackButton=true;
        }else{
            super.onBackPressed();
            //android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            finish();
        }
        new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                isUserClickedBackButton=false;
            }
        }.start();
    }
}
