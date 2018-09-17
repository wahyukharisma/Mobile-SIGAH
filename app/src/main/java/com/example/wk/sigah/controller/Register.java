package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.UserAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    private String URL;
    private GetURL getURL=new GetURL(URL);
    private EditText etUsername,etPassword,etPassword2,etName,etEmail,etIdentityNumber,etPhone,etAddress;
    String username,password,repeatPass,nama,no_identitas,telp,alamat,email,id_pel;
    Integer id_role=5;
    private Button btnRegister;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etPassword2=(EditText)findViewById(R.id.etOpassword);
        etName=(EditText)findViewById(R.id.etName);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etIdentityNumber=(EditText)findViewById(R.id.etIdentitiy);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etAddress=(EditText)findViewById(R.id.etAddress);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        try{
        onClickRegister(this);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public void onClickRegister(Register view){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Show progress dialog
                progress=new ProgressDialog(Register.this);
                progress.setCancelable(false);
                progress.setMessage("Loading. . .");
                progress.show();

                username=etUsername.getText().toString();
                password=etPassword.getText().toString();
                repeatPass=etPassword2.getText().toString();
                nama=etName.getText().toString();
                email=etEmail.getText().toString();
                no_identitas=etIdentityNumber.getText().toString();
                alamat=etAddress.getText().toString();
                telp=etPhone.getText().toString();

                if((username.equals("") || password.equals("") || email.equals("") || no_identitas.equals("") || alamat.equals("") || telp.equals(""))){
                    progress.dismiss();
                    Toast.makeText(Register.this,"Please fill all data in column",Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(repeatPass)){
                    progress.dismiss();
                    Toast.makeText(Register.this,"Incorrect repeat password",Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    progress.dismiss();
                    Toast.makeText(Register.this,"Please insert email correctly",Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        Retrofit retrofit=new Retrofit.Builder()
                                .baseUrl(getURL.GetMyURL())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        PelangganAPI apiRegister=retrofit.create(PelangganAPI.class);
                        Call<Value> call=apiRegister.Register(id_role,email,nama,no_identitas,telp,alamat,username);


                        call.enqueue(new Callback<Value>() {
                            @Override
                            public void onResponse(Call<Value> call, Response<Value> response) {
                                Integer value=response.body().getValue();
                                String message=response.body().getMessage();
                                progress.dismiss();

                                if(value==1){
                                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                                    getLastIDPel();
                                    initEditText();
                                }else{
                                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Value> call, Throwable t) {
                                t.printStackTrace();
                                progress.dismiss();
                                Toast.makeText(Register.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }catch (Exception e){
                    }
                }
            }
        });
    }


    public void getLastIDPel(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api=retrofit.create(PelangganAPI.class);

        Call<PelangganList> call=api.getLastIDPel();
        call.enqueue(new Callback<PelangganList>() {
            @Override
            public void onResponse(Call<PelangganList> call, Response<PelangganList> response) {
                response.body();
                id_pel=response.body().getResult().get(0).getIdPel();
                createUser();
            }

            @Override
            public void onFailure(Call<PelangganList> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Register.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void createUser(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI apiUser=retrofit.create(UserAPI.class);

        Call<Value> callUser=apiUser.CreatedUser(id_role,username,password,email,id_pel);

        callUser.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                Integer value=response.body().getValue();
                String message=response.body().getMessage();
                progress.dismiss();

                if(value==1){
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(Register.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initEditText(){
        etUsername.setText("");
        etPassword.setText("");
        etPassword2.setText("");
        etName.setText("");
        etEmail.setText("");
        etIdentityNumber.setText("");
        etPhone.setText("");
        etAddress.setText("");
        finish();
    }

}
