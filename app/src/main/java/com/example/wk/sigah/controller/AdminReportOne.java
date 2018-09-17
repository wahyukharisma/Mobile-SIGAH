package com.example.wk.sigah.controller;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.RoomAPI;
import com.example.wk.sigah.model.BestPelangganList;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.RoomFilterList;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.path;

/**
 * Created by Kharisma on 14/04/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AdminReportOne extends AppCompatActivity {

    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);
    Button btnCreate;
    ImageView imgBack;
    private View mRootView;

    Date date= Calendar.getInstance().getTime();
    SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");


    //Spinner
    Integer getYear=Calendar.getInstance().get(Calendar.YEAR);
    public Spinner spinYear;
    String spinnerSelected;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_report_1);
        //txtDate=(TextView) findViewById(R.id.txtDate);
        imgBack=(ImageView)findViewById(R.id.imgBack);

        //Init Buttton
        btnCreate=(Button)findViewById(R.id.btnCreate);

        //Init Spinner
        spinYear=(Spinner)findViewById(R.id.spinYear);
        initSpinner();

        //txtDate.setText(thisDate);
        onClickBackListener(this);
        onClickBtnCreate(this);

    }

    public void onClickBtnCreate(AdminReportOne view){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerSelected=spinYear.getSelectedItem().toString();
                showReport();

            }
        });
    }

    public void onClickBackListener(AdminReportOne view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void showReport(){
        progress=new ProgressDialog(AdminReportOne.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api = retrofit.create(PelangganAPI.class);

        Call<BestPelangganList> call = api.getBestPel(spinnerSelected);
        call.enqueue(new Callback<BestPelangganList>() {
            @Override
            public void onResponse(Call<BestPelangganList> call, Response<BestPelangganList> response) {
                progress.dismiss();
                FragmentReportOne fragmentReportOne=new FragmentReportOne(response.body().getResult());
                setFragment(fragmentReportOne,"FragmentReportOne");
            }

            @Override
            public void onFailure(Call<BestPelangganList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(AdminReportOne.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction fragmentTranasction=fragmentTranasction=getSupportFragmentManager().beginTransaction();
        fragmentTranasction.replace(R.id.fragment_container,fragment,tag);
        fragmentTranasction.commit();
    }

    public void initSpinner(){
        ArrayList<String> years=new ArrayList<String>();
        for(int i=2015;i<=getYear;i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AdminReportOne.this,android.R.layout.simple_spinner_item,years);

        spinYear.setAdapter(adapter);
    }

}
