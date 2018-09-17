package com.example.wk.sigah.controller;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.model.GetCountBookingList;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment {

    LinearLayout layout1,layout2,layout3;
    TextView txtEmail,txtUsername,txtTotalRes;

    private String myTempIdPel;
    private ProgressDialog progress;
    private String email,name,countReservation,URL;
    private GetURL getURL=new GetURL(URL);

    //Get Shared Preferences
    SharedPrefManager sharedPrefManager;



    public FragmentAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);
        //init Shared Preferences
        sharedPrefManager=new SharedPrefManager(getActivity());

        layout1=(LinearLayout)view.findViewById(R.id.editProfile);
        layout2=(LinearLayout)view.findViewById(R.id.changePassword);
        layout3=(LinearLayout)view.findViewById(R.id.logOut);

        txtEmail=(TextView)view.findViewById(R.id.txtEmail);
        txtUsername=(TextView)view.findViewById(R.id.txtUsername);
        txtTotalRes=(TextView)view.findViewById(R.id.txtTotalRes);

        myTempIdPel=getArguments().getString("id_pel");
        loadDataPelanggan();

        onClickEdit(getView());
        onClickChangePassword(getView());
        onClickLogOut(getView());

        return view;
    }

    public void onClickEdit(View view){
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentControlAccountPassword();
            }
        });
    }
    public void onClickChangePassword(View view){
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentControlAccountPassword();
            }
        });
    }
    public void onClickLogOut(View view){
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad=new AlertDialog.Builder(getActivity()).setTitle("Are you sure want to logout?").
                        setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sharedPrefManager.saveSPBoolean(sharedPrefManager.SP_ALREADY_LOGIN,false);
                                startActivity(new Intent(getActivity(),LoginActivity.class));
                                getActivity().finish();
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
    public void loadDataPelanggan(){
        progress=new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api=retrofit.create(PelangganAPI.class);
        Call<PelangganList> call=api.getPelanggan(myTempIdPel);

        call.enqueue(new Callback<PelangganList>() {
            @Override
            public void onResponse(Call<PelangganList> call, Response<PelangganList> response) {
                response.body();
                Integer value=response.body().getValue();
                progress.dismiss();
                if(value==1){
                    email=response.body().getResult().get(0).getEmail();
                    name=response.body().getResult().get(0).getNama();
                    txtUsername.setText(name);
                    txtEmail.setText(email);
                    getCountReservasiID();
                }else{
                    Toast.makeText(getActivity(),"No Data Found ",Toast.LENGTH_SHORT).show();
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
    public void getCountReservasiID(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api=retrofit.create(PelangganAPI.class);
        Call<GetCountBookingList> call=api.getCountBooking(email);
        call.enqueue(new Callback<GetCountBookingList>() {
            @Override
            public void onResponse(Call<GetCountBookingList> call, Response<GetCountBookingList> response) {
                countReservation=response.body().getResult().get(0).getTotalReservation();
                txtTotalRes.setText(countReservation);
            }

            @Override
            public void onFailure(Call<GetCountBookingList> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void intentControlAccountPassword(){
        Intent intent=new Intent(getActivity(),ControlAccountPassword.class);
        intent.putExtra("id_pel",myTempIdPel);
        getActivity().startActivity(intent);
    }
}
