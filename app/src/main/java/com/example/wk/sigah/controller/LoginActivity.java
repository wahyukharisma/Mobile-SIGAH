package com.example.wk.sigah.controller;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.LoginAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.SharedPrefManager;
import com.example.wk.sigah.model.StoragePremission;
import com.example.wk.sigah.model.UserList;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    TextView txtForgotPass;
    EditText ETusername, ETpassword;
    Button btnlogin, btnRegister;
    String username, password;
    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);


    //Shared Preferences
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPrefManager=new SharedPrefManager(this);
        if(sharedPrefManager.getSpAlreadyLogin()){
            startActivity(new Intent(LoginActivity.this,Homepage.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        ETusername = (EditText) findViewById(R.id.ETusername);
        ETpassword = (EditText) findViewById(R.id.ETpassword);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnSignUp);
        txtForgotPass = (TextView) findViewById(R.id.link1);

        onClickLogin(this);
        onClickSignUp(this);
        onClickForgotPass(this);
    }


    void onClickLogin(LoginActivity v) {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    progress = new ProgressDialog(LoginActivity.this);
                    progress.setCancelable(false);
                    progress.setMessage("Loading. . .");
                    progress.show();
                    username = ETusername.getText().toString();
                    password = ETpassword.getText().toString();
                    login();
                } catch (Exception e) {
                }

            }
        });
    }

    void onClickSignUp(LoginActivity v) {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent signUp = new Intent(LoginActivity.this, Register.class);
                    startActivity(signUp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void onClickForgotPass(LoginActivity v) {
        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ETusername.setText("");
                    ETpassword.setText("");
                    Intent forgotPass = new Intent(LoginActivity.this, ForgotPassword.class);
                    startActivity(forgotPass);
                } catch (Exception e) {
                }
            }
        });
    }
    public void login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginAPI api = retrofit.create(LoginAPI.class);

        Call<UserList> call = api.Login(username, password);

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Integer value=response.body().getValue();
                progress.dismiss();

                if(value==1){
                    response.body();
                    ETusername.setText("");
                    ETpassword.setText("");
                    if(response.body().getResult().get(0).getIdRole().equals("5")){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent homeUser=new Intent(LoginActivity.this,Homepage.class);
                        homeUser.putExtra("id_pel",response.body().getResult().get(0).getIdPel());
                        homeUser.putExtra("email",response.body().getResult().get(0).getEmail());

                        //put shared preferences
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL,response.body().getResult().get(0).getEmail());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID_PEL,response.body().getResult().get(0).getIdPel());
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_ALREADY_LOGIN,true);
                        startActivity(homeUser);
                    }else if(response.body().getResult().get(0).getIdRole().equals("1") || response.body().getResult().get(0).getIdRole().equals("2")){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent homeadmin=new Intent(LoginActivity.this,Admin.class);
                        startActivity(homeadmin);
                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed please check username or password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Login failed please check username or password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(LoginActivity.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
