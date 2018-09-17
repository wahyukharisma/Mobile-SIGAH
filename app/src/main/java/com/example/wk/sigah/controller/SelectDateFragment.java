package com.example.wk.sigah.controller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

/**
 * Created by Kharisma on 29/03/2018.
 */

public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }
    //Not Work Still Force close
    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        //populateSetDate(yy, mm+1, dd);
    }
    public void populateSetDate(int year, int month, int day) {
        //TextView txt=(TextView) getView().findViewById(R.id.txtDateChooser);
        //txt.setText(month+"/"+day+"/"+year);
    }
}
