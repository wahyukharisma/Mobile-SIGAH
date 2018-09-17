package com.example.wk.sigah.controller;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.LoginAPI;
import com.example.wk.sigah.api.RoomAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.RoomFilter;
import com.example.wk.sigah.model.RoomFilterList;
import com.example.wk.sigah.model.UserList;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class showRoomAvailable extends AppCompatActivity{

    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);
    String check_in,check_out,branch;
    Button btnSearch;
    ImageView imgBack;
    String date;

    // Spinner indexes
    String[] branchView={"Yogyakarta","Bandung"};
    String[] durationView={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    String[] filterView={"Room Number","Lower Price","Higher Price"};

    Spinner spinnerDuration,spinnerBranch,spinnerFilter;
    TextView txtChooseDate,txtCheckOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room_available);

        //SPINNER INIT
        spinnerBranch=(Spinner)findViewById(R.id.spinBranch);
        ArrayAdapter<String> adapterSpinnerBranch= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,branchView);
        adapterSpinnerBranch.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerBranch.setAdapter(adapterSpinnerBranch);

        spinnerDuration=(Spinner)findViewById(R.id.spinDuration);
        ArrayAdapter<String> adapterSpinnerDur= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,durationView);
        adapterSpinnerDur.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDuration.setAdapter(adapterSpinnerDur);
        spinnerDuration.setSelection(0);

        spinnerFilter=(Spinner)findViewById(R.id.spinFilter);
        ArrayAdapter<String> adapterSpinnerFilter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,filterView);
        adapterSpinnerFilter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerFilter.setAdapter(adapterSpinnerFilter);
        spinnerFilter.setSelection(0);

        btnSearch=(Button)findViewById(R.id.btnSearch);
        imgBack=(ImageView) findViewById(R.id.imgBackHomepage);
        txtChooseDate=(TextView)findViewById(R.id.txtDateChooser);
        txtCheckOut=(TextView)findViewById(R.id.txtCheckOut);

        onClickBackHomepage(this);
        onClickButtonSearch(this);
        onClickDate(this);

        Intent intent=getIntent();
        txtChooseDate.setText(check_in=intent.getStringExtra("check_in"));
        txtCheckOut.setText(check_out=intent.getStringExtra("check_out"));
        branch=intent.getStringExtra("branch");

        Adapter adapter=spinnerBranch.getAdapter();
        for(int i=0;i<spinnerBranch.getAdapter().getCount();i++){
            if(adapter.getItem(i).toString().equals(branch)){
                spinnerBranch.setSelection(i);
            }
        }

        setFragmentByID();

    }

    public void onClickDate(showRoomAvailable view){
        txtChooseDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                openDatePicker();
                spinnerDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String temp=spinnerDuration.getSelectedItem().toString();
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c=Calendar.getInstance();
                        try {
                            String tempDate=date;
                            c.setTime(sdf.parse(tempDate));
                            c.add(Calendar.DATE,Integer.parseInt(temp));
                            tempDate=sdf.format(c.getTime());
                            txtCheckOut.setText(tempDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            }
        });
    }

    public void setFragmentByID(){
        progress=new ProgressDialog(showRoomAvailable.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        String branchTemp=spinnerBranch.getSelectedItem().toString();
        String check_inTemp=txtChooseDate.getText().toString();
        String check_outTemp=txtCheckOut.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomAPI api = retrofit.create(RoomAPI.class);

        Call<RoomFilterList> call = api.filterRoom(branchTemp,check_inTemp,check_outTemp);
        call.enqueue(new Callback<RoomFilterList>() {
            @Override
            public void onResponse(Call<RoomFilterList> call, Response<RoomFilterList> response) {
                progress.dismiss();
                FragmentFilterRoomStandar fragmentFilterRoomStandar=new FragmentFilterRoomStandar(response.body().getResult());
                setFragment(fragmentFilterRoomStandar,"FragmentFilter");
            }

            @Override
            public void onFailure(Call<RoomFilterList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(showRoomAvailable.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setFragmentByPriceAsc(){
        progress=new ProgressDialog(showRoomAvailable.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();
        String branchTemp=spinnerBranch.getSelectedItem().toString();
        String check_inTemp=txtChooseDate.getText().toString();
        String check_outTemp=txtCheckOut.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomAPI api = retrofit.create(RoomAPI.class);

        Call<RoomFilterList> call = api.filterRoomPriceAsc(branchTemp,check_inTemp,check_outTemp);
        call.enqueue(new Callback<RoomFilterList>() {
            @Override
            public void onResponse(Call<RoomFilterList> call, Response<RoomFilterList> response) {
                progress.dismiss();
                FragmentFilterRoomPrice fragmentFilterRoomPrice=new FragmentFilterRoomPrice(response.body().getResult());
                setFragment(fragmentFilterRoomPrice,"FragmentFilterPriceAsc");
            }

            @Override
            public void onFailure(Call<RoomFilterList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(showRoomAvailable.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setFragmentByPriceDesc(){
        progress=new ProgressDialog(showRoomAvailable.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();
        String branchTemp=spinnerBranch.getSelectedItem().toString();
        String check_inTemp=txtChooseDate.getText().toString();
        String check_outTemp=txtCheckOut.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomAPI api = retrofit.create(RoomAPI.class);


        Call<RoomFilterList> call = api.filterRoomPriceDesc(branchTemp,check_inTemp,check_outTemp);
        call.enqueue(new Callback<RoomFilterList>() {
            @Override
            public void onResponse(Call<RoomFilterList> call, Response<RoomFilterList> response) {
                progress.dismiss();
                FragmentFilterRoomPrice fragmentFilterRoomPrice=new FragmentFilterRoomPrice(response.body().getResult());
                setFragment(fragmentFilterRoomPrice,"FragmentFilterPriceDesc");
            }

            @Override
            public void onFailure(Call<RoomFilterList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(showRoomAvailable.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(android.support.v4.app.Fragment fragment, String tag) {
        android.support.v4.app.FragmentTransaction fragmentTranasction=fragmentTranasction=getSupportFragmentManager().beginTransaction();
        fragmentTranasction.replace(R.id.fragment_container,fragment,tag);
        fragmentTranasction.commit();
    }
    public void onClickButtonSearch(showRoomAvailable view){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerFilter.getSelectedItem().toString().equals("Room Number")){
                    setFragmentByID();
                }else if(spinnerFilter.getSelectedItem().toString().equals("Lower Price")){
                    setFragmentByPriceAsc();
                }else{
                    setFragmentByPriceDesc();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog d = new DatePickerDialog(showRoomAvailable.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;
                date = year + "-" + month + "-" + day;
                txtChooseDate.setText(date);

                String temp=spinnerDuration.getSelectedItem().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Calendar c=Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, Integer.parseInt(temp));
                txtCheckOut.setText(sdf.format(c.getTime()));
            }
        }, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        d.getDatePicker().setMinDate(System.currentTimeMillis());
        d.show();
    }

    public void onClickBackHomepage(showRoomAvailable view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
