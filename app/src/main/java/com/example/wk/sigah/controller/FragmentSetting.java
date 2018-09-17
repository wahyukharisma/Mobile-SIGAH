package com.example.wk.sigah.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment {

    TextView txtAbout,txtPP,txtTC,txtGW;

    public FragmentSetting() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_setting, container, false);

        txtAbout=(TextView)view.findViewById(R.id.txtAbout);
        txtPP=(TextView)view.findViewById(R.id.txtPP);
        txtTC=(TextView)view.findViewById(R.id.txtTC);
        txtGW=(TextView)view.findViewById(R.id.txtGoWeb);
        onClickAbout(getView());
        onClickTC(getView());
        onClickPP(getView());
        onClickGW(getView());

        return view;
    }
    public void onClickAbout(View view){
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AboutHotel.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void onClickTC(View view){
        txtTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),TermsandCondition.class);
                getActivity().startActivity(intent);
            }
        });
    }

    public void onClickPP(View v){
        txtPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),PrivacyAndPolicy.class);
                getActivity().startActivity(intent);
            }
        });
    }
    public void onClickGW(View view){
        txtGW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Youre Clicked Go To Website",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
