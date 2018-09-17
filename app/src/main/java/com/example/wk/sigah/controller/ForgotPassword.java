package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.UserAPI;
import com.example.wk.sigah.model.GMailSender;
import com.example.wk.sigah.model.GetCountBookingList;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.UserList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends AppCompatActivity {
    public ImageView img1;
    public Button btnSend;
    public TextView txtEmail;
    public String temp,URL;
    private GetURL getURL=new GetURL(URL);
    public ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        img1=(ImageView)findViewById(R.id.imgBackHome);
        btnSend=(Button)findViewById(R.id.btnSend);
        txtEmail=(TextView)findViewById(R.id.txtEmail);


        onClickBack(this);
        onClickSend(this);
    }

    void onClickBack(ForgotPassword view){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ForgotPassword.super.onBackPressed();
                    finish();
                }catch (Exception e){
                }
            }
        });
    }
    public void onClickSend(ForgotPassword view){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataEmail();
            }
        });
    }

    public void getDataEmail(){
        progress=new ProgressDialog(ForgotPassword.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        temp=txtEmail.getText().toString();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI api=retrofit.create(UserAPI.class);
        Call<UserList> call=api.updatePassword(temp);
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                progress.dismiss();
                Integer value=response.body().getValue();
                if(value==1){
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                GMailSender sender = new GMailSender(
                                        "sigah.work@gmail.com",
                                        "sigahadmin12320");
                                sender.sendMail("SIGAH Password Notification","Hello,\nWe received a request to reset your SIGAH password \nFor that we have reset your password accordance with your username,\nplease after this you must change your password through SIGAH apps\nThank You",
                                        "sigah.work@gmail.com",
                                        temp);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }).start();
                    Toast.makeText(ForgotPassword.this,"Email has been send, please check your email",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this,"Email is unregistered in SIGAH",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(ForgotPassword.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
