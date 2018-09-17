package com.example.wk.sigah.controller;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.ReservationAPI;
import com.example.wk.sigah.api.SeasonAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.ReservationList;
import com.example.wk.sigah.model.SeasonList;
import com.example.wk.sigah.model.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.design.widget.BottomNavigationView.*;
public class Homepage extends AppCompatActivity {

    private boolean isUserClickedBackButton=false;
    private ActionBar toolbar;
    private String URL;
    private GetURL getURL=new GetURL(URL);
    String temp,tempEmail;
    Fragment fragment;

    private ProgressDialog progress;

    //Get Data From Database
    String name,email,id_pel;

    //Get Shared Preferences
    SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        sharedPrefManager= new SharedPrefManager(this);

        if(sharedPrefManager.getSpAlreadyLogin()){
            //Get Shared preference data
            temp=sharedPrefManager.getSpIdPel();
            tempEmail=sharedPrefManager.getSpEmail();
        }else{
            //Get Intent Data
            Intent intent=getIntent();
            temp=intent.getStringExtra("id_pel");
            tempEmail=intent.getStringExtra("email");
        }

        //Call API
        loadData();

        toolbar=getSupportActionBar();
        setFragment(new FragmentHome(),"FragmentHome");

        BottomNavigationView navigation=(BottomNavigationView)findViewById(R.id.nav_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new OnNavigationItemSelectedListener() {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment fragment;
            Bundle bundle=new Bundle();
            bundle.putString("id_pel",id_pel);
            switch (item.getItemId()){
                case R.id.navigation_home:
                    fragment=new FragmentHome();
                    setFragment(fragment,"FragmentHome");
                    return true;

                case R.id.navigation_account:
                    fragment=new FragmentAccount();
                    fragment.setArguments(bundle);
                    setFragment(fragment,"FragmentAccount");
                    return true;

                case R.id.navigation_history:
                    fragment=new FragmentHistory();
                    fragment.setArguments(bundle);
                    setFragment(fragment,"FragmentHistory");
                    return true;

                case R.id.navigation_promo:
                    progress=new ProgressDialog(Homepage.this);
                    progress.setCancelable(false);
                    progress.setMessage("Loading. . .");
                    progress.show();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(getURL.GetMyURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    SeasonAPI api = retrofit.create(SeasonAPI.class);

                    Call<SeasonList> call = api.getAllSeason();
                    call.enqueue(new Callback<SeasonList>() {
                        @Override
                        public void onResponse(Call<SeasonList> call, Response<SeasonList> response) {
                            progress.dismiss();
                            response.body();
                            fragment=new FragmentPromo(response.body().getResult());
                            setFragment(fragment,"FragmentPromo");
                        }

                        @Override
                        public void onFailure(Call<SeasonList> call, Throwable t) {
                            t.printStackTrace();
                            progress.dismiss();
                            Toast.makeText(Homepage.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
                        }

                    });
                    return true;
                case R.id.navigation_setting:
                    fragment=new FragmentSetting();
                    setFragment(fragment,"FragmentSetting");
                    return true;

            }
            return false;
        }
    };
    private void setFragment(Fragment fragment,String tag) {
        android.support.v4.app.FragmentTransaction fragmentTranasction=fragmentTranasction=getSupportFragmentManager().beginTransaction();
        fragmentTranasction.replace(R.id.container,fragment,tag);
        fragmentTranasction.commit();
    }
    public void loadData(){
        progress=new ProgressDialog(Homepage.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api=retrofit.create(PelangganAPI.class);
        Call<PelangganList> call=api.getPelanggan(temp);

        call.enqueue(new Callback<PelangganList>() {
            @Override
            public void onResponse(Call<PelangganList> call, Response<PelangganList> response) {
                response.body();
                Integer value=response.body().getValue();
                progress.dismiss();
                if(value==1){
                    name=response.body().getResult().get(0).getNama();
                    email=response.body().getResult().get(0).getEmail();
                    id_pel=response.body().getResult().get(0).getIdPel();
                }else{
                    Toast.makeText(Homepage.this,"No Data Found ",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PelangganList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(Homepage.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(!isUserClickedBackButton){
            Toast.makeText(this,"Press back again to exit",Toast.LENGTH_LONG).show();
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
