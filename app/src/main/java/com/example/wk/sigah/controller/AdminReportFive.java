package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.TransactionAPI;
import com.example.wk.sigah.model.BestPelangganList;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.IncomeBranchList;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 18/04/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AdminReportFive extends AppCompatActivity {
    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);
    TextView txtDate;
    ImageView imgBack;
    Date date= Calendar.getInstance().getTime();
    SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");
    public String thisDate=df.format(date);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_report_5);

        txtDate=(TextView)findViewById(R.id.txtThisDate);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        txtDate.setText(thisDate);

        showReport();
        onClickBackListener(this);
    }
    public void showReport(){
        progress=new ProgressDialog(AdminReportFive.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TransactionAPI api = retrofit.create(TransactionAPI.class);

        Call<IncomeBranchList> call = api.getIncomeBranch();
        call.enqueue(new Callback<IncomeBranchList>() {
            @Override
            public void onResponse(Call<IncomeBranchList> call, Response<IncomeBranchList> response) {
                progress.dismiss();
                FragmentReportFive fragmentReportFive=new FragmentReportFive(response.body().getResult());
                setFragment(fragmentReportFive,"FragmentReportFive");
            }

            @Override
            public void onFailure(Call<IncomeBranchList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(AdminReportFive.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction fragmentTranasction=fragmentTranasction=getSupportFragmentManager().beginTransaction();
        fragmentTranasction.replace(R.id.fragment_container,fragment,tag);
        fragmentTranasction.commit();
    }
    public void onClickBackListener(AdminReportFive view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
