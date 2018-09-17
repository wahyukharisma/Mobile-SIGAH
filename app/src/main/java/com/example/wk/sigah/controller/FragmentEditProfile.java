package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.UserAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.Value;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class FragmentEditProfile extends Fragment {
    View view;
    private EditText etName,etPhone,etEmail;
    private Button btnEdit;
    private String tempIdPel,tempEmail,tempName,tempPhone;
    private ProgressDialog progress;
    private String URL;
    private String name,phone,email;
    private GetURL getURL=new GetURL(URL);

    public FragmentEditProfile(String tempIdPel) {
        this.tempIdPel = tempIdPel;
    }

    public FragmentEditProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.edit_profile,container,false);

        etName=(EditText)view.findViewById(R.id.etoldName);
        etPhone=(EditText)view.findViewById(R.id.etPhone);
        etEmail=(EditText)view.findViewById(R.id.etEmail);
        btnEdit=(Button)view.findViewById(R.id.btnEditProfile);

        getDataPelanggan();
        onClickEditProfile(getView());
        return view;
    }

    public void onClickEditProfile(View view){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().matches("") || etPhone.getText().toString().matches("") || etEmail.getText().toString().matches("")){
                    Toast.makeText(getActivity(),"Please insert all data in field",Toast.LENGTH_SHORT).show();
                }if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()){
                    Toast.makeText(getActivity(),"Please insert email with corre",Toast.LENGTH_SHORT).show();
                }
                else{
                    updatePelangganBy();
                }

            }
        });
    }

    public void getDataPelanggan(){
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api = retrofit.create(PelangganAPI.class);

        Call<PelangganList> call = api.getPelanggan(tempIdPel);
        call.enqueue(new Callback<PelangganList>() {
            @Override
            public void onResponse(Call<PelangganList> call, Response<PelangganList> response) {
                progress.dismiss();
                response.body();
                Integer value=response.body().getValue();
                if(value==1){
                    tempName=response.body().getResult().get(0).getNama();
                    etName.setText(tempName);

                    tempPhone=response.body().getResult().get(0).getTelp();
                    etPhone.setText(tempPhone);

                    tempEmail=response.body().getResult().get(0).getEmail();
                    etEmail.setText(tempEmail);

                }else{
                    Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PelangganList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updatePelangganBy(){
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        name=etName.getText().toString();
        email=etEmail.getText().toString();
        phone=etPhone.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api = retrofit.create(PelangganAPI.class);

        Call<Value> call = api.updatePelangganBy(email,name,phone,tempEmail,tempIdPel);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                progress.dismiss();
                Integer value=response.body().getValue();
                String message=response.body().getMessage();

                if(value==1){
                    updateUserEmailBy();
                    Toast.makeText(getActivity(),"Profile Update",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateUserEmailBy(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI api = retrofit.create(UserAPI.class);

        Call<Value> call = api.updateUserEmail(tempEmail,email);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

            }
        });
    }


}
