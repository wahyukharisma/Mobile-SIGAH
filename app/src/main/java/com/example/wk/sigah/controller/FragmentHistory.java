package com.example.wk.sigah.controller;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.ReservationAPI;
import com.example.wk.sigah.api.SeasonAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.Reservation;
import com.example.wk.sigah.model.ReservationList;
import com.example.wk.sigah.model.SeasonList;
import com.example.wk.sigah.model.historyView;
import com.example.wk.sigah.model.notaList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHistory extends Fragment {

    public Button btnSearch;
    public Spinner spinFilter;
    private String[] filterView={"Booking","Success"};
    public List<Reservation> getData;
    public String tempIDPel;
    private List<historyView> listHistory;
    private ProgressDialog progress;
    String URL;
    private GetURL getURL=new GetURL(URL);

    public Fragment fragment;
    public String myTempIDPel;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpinnerItem;


    public FragmentHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_history, container, false);

        spinFilter=(Spinner)view.findViewById(R.id.spinFilter);
        ArrayAdapter<String> adapterSpinner= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,filterView);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinFilter.setAdapter(adapterSpinner);

        btnSearch=(Button)view.findViewById(R.id.btnSearch);
        myTempIDPel=getArguments().getString("id_pel");

        setStatus("Booking");
        getDataBooking();
        setOnClickBtnSearch(this);

        return view;

    }

    private void setFragment(Fragment fragment,String tag) {
        android.support.v4.app.FragmentTransaction fragmentTranasction=fragmentTranasction=getChildFragmentManager().beginTransaction();
        fragmentTranasction.replace(R.id.container,fragment,tag);
        fragmentTranasction.commit();
    }

    public void getDataBooking(){
        progress=new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReservationAPI api = retrofit.create(ReservationAPI.class);

        Call<ReservationList> call = api.viewReservationBy(myTempIDPel);
        call.enqueue(new Callback<ReservationList>() {
            @Override
            public void onResponse(Call<ReservationList> call, Response<ReservationList> response) {
                progress.dismiss();
                fragment=new FragmentHistoryChild(response.body().getResult());
                Bundle bundle=new Bundle();
                bundle.putString("status",getStatus());
                fragment.setArguments(bundle);
                setFragment(fragment,"HistoryChild");
            }
            @Override
            public void onFailure(Call<ReservationList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getDataNota(){
        progress=new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReservationAPI api = retrofit.create(ReservationAPI.class);

        Call<notaList> call = api.viewNoteBy(myTempIDPel);
        call.enqueue(new Callback<notaList>() {
            @Override
            public void onResponse(Call<notaList> call, Response<notaList> response) {
                progress.dismiss();
                fragment=new FragmentHistoryNote(response.body().getResult());
                setFragment(fragment,"NoteHistory");
            }

            @Override
            public void onFailure(Call<notaList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(getActivity(),"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setOnClickBtnSearch(FragmentHistory view){
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getSpinnerItem=spinFilter.getSelectedItem().toString();
                if(getSpinnerItem.equals("Booking")){
                    setStatus("Booking");
                    getDataBooking();
                }else{
                    setStatus("Sukses");
                    getDataNota();
                }
            }
        });
    }
}
