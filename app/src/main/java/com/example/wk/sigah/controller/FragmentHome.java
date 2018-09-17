package com.example.wk.sigah.controller;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.RoomAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.Room;
import com.example.wk.sigah.model.RoomFilter;
import com.example.wk.sigah.model.RoomFilterList;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {
    ViewPager viewPager;
    CustomSwitchAdapterHome adapter;
    String date;

    //conn
    private String URL;
    private GetURL getURL=new GetURL(URL);

    // Spinner indexes
    String[] branchView={"Yogyakarta","Bandung"};
    String[] durationView={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};

    Spinner spinner1,spinner4;
    TextView txtDate,txtCheckOut,txtBranch;
    ImageView img1,img2,img3;

    Button btnSearch;


    public FragmentHome() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        //spinner
        //branch
        spinner1=(Spinner)view.findViewById(R.id.spinBranch);
        ArrayAdapter<String> adapterSpinner= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,branchView);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapterSpinner);

        //duration
        spinner4=(Spinner)view.findViewById(R.id.spinDuration);
        ArrayAdapter<String> adapterSpinner4= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,durationView);
        adapterSpinner4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapterSpinner4);
        spinner4.setSelection(0);

        //Image Click listener
        img1=(ImageView)view.findViewById(R.id.imageFacility1);
        img2=(ImageView)view.findViewById(R.id.imageFacility2);
        img3=(ImageView)view.findViewById(R.id.imageFacility3);
        onClickImageFacility1(getView());
        onClickImageFacility2(getView());
        onClickImageFacility3(getView());

        viewPager=(ViewPager)view.findViewById(R.id.viewImage);
        adapter=new CustomSwitchAdapterHome(this.getActivity());
        viewPager.setAdapter(adapter);

        //getDate
        txtDate=(TextView)view.findViewById(R.id.txtDateChooser);
        txtCheckOut=(TextView)view.findViewById(R.id.txtCheckOut);
        onClickDate(getView());

        btnSearch=(Button)view.findViewById(R.id.btnSearch);
        onClickSearchListener(getView());

        return view;
    }


    void onClickDate(View view){
        txtDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                try{
                    openDatePicker();
                    spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            String temp=spinner4.getSelectedItem().toString();
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
                catch (Exception e){
                    e.toString();
                }
            }
        });
    }
    void onClickImageFacility1(View view){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(getActivity(),RecycleViewRF.class);
                    getActivity().startActivity(intent);
                }catch (Exception e){
                }
            }
        });
    }
    void onClickImageFacility2(View view){
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(getActivity(),RecycleViewRF.class);
                    getActivity().startActivity(intent);
                }catch (Exception e){
                }
            }
        });
    }
    void onClickImageFacility3(View view){
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent=new Intent(getActivity(),RecycleViewRF.class);
                    getActivity().startActivity(intent);
                }catch (Exception e){
                }
            }
        });
    }

    public void onClickSearchListener(View view){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCheckOut.getText().toString().equals("-")){
                    Toast.makeText(getActivity(),"Please choose check in date first",Toast.LENGTH_SHORT).show();
                }else{
                    getDataFilter();
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog d = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month+1;
                date = year + "-" + month + "-" + day;
                txtDate.setText(date);

                String temp=spinner4.getSelectedItem().toString();
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

    public void getDataFilter(){
        String check_in=txtDate.getText().toString();
        String check_out=txtCheckOut.getText().toString();
        String branch=spinner1.getSelectedItem().toString();

        Intent intent=new Intent(getActivity(),showRoomAvailable.class);
        intent.putExtra("check_in",check_in);
        intent.putExtra("check_out",check_out);
        intent.putExtra("branch",branch);
        startActivity(intent);
    }
}
