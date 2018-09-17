package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.TransactionAPI;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.IncomeMounthList;
import com.example.wk.sigah.model.NewPelangganList;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 17/04/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AdminReportFour extends AppCompatActivity {

    //chart
    private BarChart chart;
    float barWidth=0.3f,barSpace=0f,groupSpace=0.4f;

    //initTable
    public TextView txtPerJan,txtPerFeb,txtPerMar,txtPerApr,txtPerMay,txtPerJun,txtPerJul,txtPerAug,txtPerSep,txtPerOct,txtPerNov,txtPerDec;
    public TextView txtGrJan,txtGrFeb,txtGrMar,txtGrApr,txtGrMay,txtGrJun,txtGrJul,txtGrAug,txtGrSep,txtGrOct,txtGrNov,txtGrDec;
    public TextView txtTotJan,txtTotFeb,txtTotMar,txtTotApr,txtTotMay,txtTotJun,txtTotJul,txtTotAug,txtTotSep,txtTotOct,txtTotNov,txtTotDec,txtTotAll;
    public Double PerJan,PerFeb,PerMar,PerApr,PerMay,PerJun,PerJul,PerAug,PerSep,PerOct,PerNov,PerDec;
    public Double GrJan,GrFeb,GrMar,GrApr,GrMay,GrJun,GrJul,GrAug,GrSep,GrOct,GrNov,GrDec;
    public Double TotJan,TotFeb,TotMar,TotApr,TotMay,TotJun,TotJul,TotAug,TotSep,TotOct,TotNov,TotDec,TotAll;

    Integer getYear= Calendar.getInstance().get(Calendar.YEAR);
    ImageView imgBack;

    Spinner spinYear;
    Button btnCreate;
    String spinnerValue;

    //Retorfit
    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_report_4);

        imgBack=(ImageView)findViewById(R.id.imgBack);
        spinYear=(Spinner)findViewById(R.id.spinYear);
        btnCreate=(Button)findViewById(R.id.btnCreate);

        //initSpinner
        initSpinner();

        //init all value table view
        initTable();

        //init chart
        chart=(BarChart)findViewById(R.id.chartReportFour);

        onClickBtnCreate(this);
        onClickBackListener(this);

    }

    public void initTable(){
        //Personal
        txtPerJan=(TextView)findViewById(R.id.txtPerJan);
        txtPerFeb=(TextView)findViewById(R.id.txtPerFeb);
        txtPerMar=(TextView)findViewById(R.id.txtPerMar);
        txtPerApr=(TextView)findViewById(R.id.txtPerApr);
        txtPerMay=(TextView)findViewById(R.id.txtPerMay);
        txtPerJun=(TextView)findViewById(R.id.txtPerJun);
        txtPerJul=(TextView)findViewById(R.id.txtPerJul);
        txtPerAug=(TextView)findViewById(R.id.txtPerAug);
        txtPerSep=(TextView)findViewById(R.id.txtPerSep);
        txtPerOct=(TextView)findViewById(R.id.txtPerOct);
        txtPerNov=(TextView)findViewById(R.id.txtPerNov);
        txtPerDec=(TextView)findViewById(R.id.txtPerDec);

        //Group
        txtGrJan=(TextView)findViewById(R.id.txtGrpJan);
        txtGrFeb=(TextView)findViewById(R.id.txtGrpFeb);
        txtGrMar=(TextView)findViewById(R.id.txtGrpMar);
        txtGrApr=(TextView)findViewById(R.id.txtGrpApr);
        txtGrMay=(TextView)findViewById(R.id.txtGrpMay);
        txtGrJun=(TextView)findViewById(R.id.txtGrpJun);
        txtGrJul=(TextView)findViewById(R.id.txtGrpJul);
        txtGrAug=(TextView)findViewById(R.id.txtGrpAug);
        txtGrSep=(TextView)findViewById(R.id.txtGrpSep);
        txtGrOct=(TextView)findViewById(R.id.txtGrpOct);
        txtGrNov=(TextView)findViewById(R.id.txtGrpNov);
        txtGrDec=(TextView)findViewById(R.id.txtGrpDec);

        //Total Personal and Group
        txtTotJan=(TextView)findViewById(R.id.txtTotJan);
        txtTotFeb=(TextView)findViewById(R.id.txtTotFeb);
        txtTotMar=(TextView)findViewById(R.id.txtTotMar);
        txtTotApr=(TextView)findViewById(R.id.txtTotApr);
        txtTotMay=(TextView)findViewById(R.id.txtTotMay);
        txtTotJun=(TextView)findViewById(R.id.txtTotJun);
        txtTotJul=(TextView)findViewById(R.id.txtTotJul);
        txtTotAug=(TextView)findViewById(R.id.txtTotAug);
        txtTotSep=(TextView)findViewById(R.id.txtTotSep);
        txtTotOct=(TextView)findViewById(R.id.txtTotOct);
        txtTotNov=(TextView)findViewById(R.id.txtTotNov);
        txtTotDec=(TextView)findViewById(R.id.txtTotDec);
        txtTotAll=(TextView)findViewById(R.id.txtTotAll);

        //init value
        PerJan=PerFeb=PerMar=PerApr=PerMay=PerJun=PerJul=PerAug=PerSep=PerOct=PerNov=PerDec= Double.valueOf(0);
        GrJan=GrFeb=GrMar=GrApr=GrMay=GrJun=GrJul=GrAug=GrSep=GrOct=GrNov=GrDec= Double.valueOf(0);
        TotJan=TotFeb=TotMar=TotApr=TotMay=TotJun=TotJul=TotAug=TotSep=TotOct=TotNov=TotDec= Double.valueOf(0);
        TotAll=TotJan+TotFeb+TotMar+TotApr+TotMay+TotJun+TotJul+TotAug+TotSep+TotOct+TotNov+TotDec;

        //Personal
        txtPerJan.setText(Double.toString(PerJan));
        txtPerFeb.setText(Double.toString(PerFeb));
        txtPerMar.setText(Double.toString(PerMar));
        txtPerApr.setText(Double.toString(PerApr));
        txtPerMay.setText(Double.toString(PerMay));
        txtPerJun.setText(Double.toString(PerJun));
        txtPerJul.setText(Double.toString(PerJul));
        txtPerAug.setText(Double.toString(PerAug));
        txtPerSep.setText(Double.toString(PerSep));
        txtPerOct.setText(Double.toString(PerOct));
        txtPerNov.setText(Double.toString(PerNov));
        txtPerDec.setText(Double.toString(PerDec));

        //Group
        txtGrJan.setText(Double.toString(GrJan));
        txtGrFeb.setText(Double.toString(GrFeb));
        txtGrMar.setText(Double.toString(GrMar));
        txtGrApr.setText(Double.toString(GrApr));
        txtGrMay.setText(Double.toString(GrMay));
        txtGrJun.setText(Double.toString(GrJun));
        txtGrJul.setText(Double.toString(GrJul));
        txtGrAug.setText(Double.toString(GrAug));
        txtGrSep.setText(Double.toString(GrSep));
        txtGrOct.setText(Double.toString(GrOct));
        txtGrNov.setText(Double.toString(GrNov));
        txtGrDec.setText(Double.toString(GrDec));

        //Total Personal and Group
        txtTotJan.setText(Double.toString(TotJan));
        txtTotFeb.setText(Double.toString(TotFeb));
        txtTotMar.setText(Double.toString(TotMar));
        txtTotApr.setText(Double.toString(TotApr));
        txtTotMay.setText(Double.toString(TotMay));
        txtTotJun.setText(Double.toString(TotJun));
        txtTotJul.setText(Double.toString(TotJul));
        txtTotAug.setText(Double.toString(TotAug));
        txtTotSep.setText(Double.toString(TotSep));
        txtTotOct.setText(Double.toString(TotOct));
        txtTotNov.setText(Double.toString(TotNov));
        txtTotDec.setText(Double.toString(TotDec));
        txtTotAll.setText(Double.toString(TotAll));

    }

    public void getReport(){
        progress=new ProgressDialog(AdminReportFour.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TransactionAPI api=retrofit.create(TransactionAPI.class);
        Call<IncomeMounthList> call=api.getIncomeMounth(spinnerValue);

        call.enqueue(new Callback<IncomeMounthList>() {
            @Override
            public void onResponse(Call<IncomeMounthList> call, Response<IncomeMounthList> response) {
                progress.dismiss();
                Integer check=0;
                NumberFormat nf=NumberFormat.getInstance();
                for(int i=0;i<response.body().getResult().size();i++){
                    check=1;
                    Double tempMoney=Double.parseDouble(response.body().getResult().get(i).getIncome());
                    String money=nf.format(tempMoney);
                    if(response.body().getResult().get(i).getMonth().equals("January")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerJan.setText(money);
                            PerJan=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrJan.setText(money);
                            GrJan=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("February")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerFeb.setText(money);
                            PerFeb=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrFeb.setText(money);
                            GrFeb=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("March")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerMar.setText(money);
                            PerMar=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrMar.setText(money);
                            GrMar=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("April")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerApr.setText(money);
                            PerApr=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrApr.setText(money);
                            GrApr=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("May")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerMay.setText(money);
                            PerMay=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrMay.setText(money);
                            GrMay=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("June")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerJun.setText(money);
                            PerJun=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrJun.setText(money);
                            GrJun=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("July")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerJul.setText(money);
                            PerJul=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrJul.setText(money);
                            GrJul=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("August")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerAug.setText(money);
                            PerAug=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrAug.setText(response.body().getResult().get(i).getIncome());
                            GrAug=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("September")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerSep.setText(money);
                            PerSep=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrSep.setText(money);
                            GrSep=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("October")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerOct.setText(money);
                            PerOct=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrOct.setText(money);
                            GrOct=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else if(response.body().getResult().get(i).getMonth().equals("November")){
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerNov.setText(money);
                            PerNov=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrNov.setText(money);
                            GrNov=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }else{
                        if(response.body().getResult().get(i).getReservationType().equals("1")){
                            txtPerDec.setText(money);
                            PerDec=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }else{
                            txtGrDec.setText(money);
                            GrDec=Double.parseDouble(response.body().getResult().get(i).getIncome());
                        }
                    }
                    TotAll=TotAll+Integer.parseInt(response.body().getResult().get(i).getIncome());
                }
                if(check==1){
                    String tempTotal=nf.format(TotAll);
                    txtTotAll.setText(tempTotal);
                    setTotalbyMounth();
                    setChart(Boolean.TRUE);
                }else {
                    initTable();
                    setChart(Boolean.FALSE);
                }

            }

            @Override
            public void onFailure(Call<IncomeMounthList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(AdminReportFour.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickBackListener(AdminReportFour view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void setTotalbyMounth(){
        NumberFormat nf=NumberFormat.getInstance();
        TotJan=PerJan+GrJan;
        TotFeb=PerFeb+GrFeb;
        TotMar=PerMar+GrMar;
        TotApr=PerApr+GrApr;
        TotMay=PerMay+GrMay;
        TotJun=PerJun+GrJun;
        TotJul=PerJul+GrJul;
        TotAug=PerAug+GrAug;
        TotSep=PerSep+GrSep;
        TotOct=PerOct+GrOct;
        TotNov=PerNov+GrNov;
        TotDec=PerDec+GrDec;
        TotAll=TotJan+TotFeb+TotMar+TotApr+TotMay+TotJun+TotJul+TotAug+TotSep+TotOct+TotNov+TotDec;

        txtTotJan.setText(nf.format(TotJan));
        txtTotFeb.setText(nf.format(TotFeb));
        txtTotMar.setText(nf.format(TotMar));
        txtTotApr.setText(nf.format(TotApr));
        txtTotMay.setText(nf.format(TotMay));
        txtTotJun.setText(nf.format(TotJun));
        txtTotJul.setText(nf.format(TotJul));
        txtTotAug.setText(nf.format(TotAug));
        txtTotSep.setText(nf.format(TotSep));
        txtTotOct.setText(nf.format(TotOct));
        txtTotNov.setText(nf.format(TotNov));
        txtTotDec.setText(nf.format(TotDec));
        txtTotAll.setText(nf.format(TotAll));
    }

    public void setChart(Boolean create){
        if(create==Boolean.TRUE){
            chart.setDescription(null);
            chart.setPinchZoom(false);
            chart.setDrawBarShadow(false);
            chart.setScaleEnabled(false);

            int groupCount=12;

            ArrayList xVals=new ArrayList();

            //make x label
            xVals.add("Jan");
            xVals.add("Feb");
            xVals.add("Mar");
            xVals.add("Apr");
            xVals.add("May");
            xVals.add("Jun");
            xVals.add("Jul");
            xVals.add("Aug");
            xVals.add("Sep");
            xVals.add("Oct");
            xVals.add("Nov");
            xVals.add("Dec");

            //init dummy data
            ArrayList yVals1=new ArrayList();
            ArrayList yVals2=new ArrayList();
            ArrayList yVals3=new ArrayList();
            //jan
            yVals1.add(new BarEntry(1,PerJan.intValue()));
            yVals2.add(new BarEntry(1,GrJan.intValue()));
            yVals3.add(new BarEntry(1,TotJan.intValue()));
            //jan
            yVals1.add(new BarEntry(2,PerFeb.intValue()));
            yVals2.add(new BarEntry(2,GrFeb.intValue()));
            yVals3.add(new BarEntry(2,TotFeb.intValue()));
            //mar
            yVals1.add(new BarEntry(3,PerMar.intValue()));
            yVals2.add(new BarEntry(3,GrMar.intValue()));
            yVals3.add(new BarEntry(3,TotMar.intValue()));
            //apr
            yVals1.add(new BarEntry(4,PerApr.intValue()));
            yVals2.add(new BarEntry(4,GrApr.intValue()));
            yVals3.add(new BarEntry(4,TotApr.intValue()));
            //may
            yVals1.add(new BarEntry(5,PerMay.intValue()));
            yVals2.add(new BarEntry(5,GrMay.intValue()));
            yVals3.add(new BarEntry(5,TotMay.intValue()));
            //jun
            yVals1.add(new BarEntry(6,PerJun.intValue()));
            yVals2.add(new BarEntry(6,GrJun.intValue()));
            yVals3.add(new BarEntry(6,TotJun.intValue()));
            //jul
            yVals1.add(new BarEntry(7,PerJul.intValue()));
            yVals2.add(new BarEntry(7,GrJul.intValue()));
            yVals3.add(new BarEntry(7,TotJul.intValue()));
            //aug
            yVals1.add(new BarEntry(8,PerAug.intValue()));
            yVals2.add(new BarEntry(8,GrAug.intValue()));
            yVals3.add(new BarEntry(8,TotAug.intValue()));
            //sep
            yVals1.add(new BarEntry(9,PerSep.intValue()));
            yVals2.add(new BarEntry(9,GrSep.intValue()));
            yVals3.add(new BarEntry(9,TotSep.intValue()));
            //oct
            yVals1.add(new BarEntry(10,PerOct.intValue()));
            yVals2.add(new BarEntry(10,GrOct.intValue()));
            yVals3.add(new BarEntry(10,TotOct.intValue()));
            //nov
            yVals1.add(new BarEntry(11,PerNov.intValue()));
            yVals2.add(new BarEntry(11,GrNov.intValue()));
            yVals3.add(new BarEntry(11,TotNov.intValue()));
            //dec
            yVals1.add(new BarEntry(12,PerDec.intValue()));
            yVals2.add(new BarEntry(12,GrDec.intValue()));
            yVals3.add(new BarEntry(12,TotDec.intValue()));

            //Draw graph
            BarDataSet set1,set2,set3;
            set1=new BarDataSet(yVals1,"Personal");
            set1.setColor(Color.BLUE);
            set2=new BarDataSet(yVals2,"Group");
            set2.setColor(Color.GREEN);
            set3=new BarDataSet(yVals3,"Total");
            set3.setColor(Color.RED);

            BarData data=new BarData(set1,set2,set3);
            data.setValueFormatter(new LargeValueFormatter());
            chart.setData(data);
            chart.getBarData().setBarWidth(barWidth);
            chart.getXAxis().setAxisMaximum(0);
            chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
            chart.groupBars(3, groupSpace, barSpace);
            chart.getData().setHighlightEnabled(false);
            chart.animateXY(2000,2000);
            chart.invalidate();

            //draw indicator
            Legend l = chart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(true);
            l.setYOffset(1f);
            l.setXOffset(0f);
            l.setYEntrySpace(0f);
            l.setTextSize(8f);

            //draw x axis and y axis
            //X-axis
            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setAxisMaximum(20);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
            //Y-axis
            chart.getAxisRight().setEnabled(false);
            YAxis leftAxis = chart.getAxisLeft();
            leftAxis.setValueFormatter(new LargeValueFormatter());
            leftAxis.setDrawGridLines(true);
            leftAxis.setSpaceTop(35f);
            leftAxis.setAxisMinimum(0f);
        }else{
            chart.clear();
            chart.invalidate();
        }

    }
    public void initSpinner(){
        ArrayList<String> years=new ArrayList<String>();
        for(int i=2015;i<=getYear;i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AdminReportFour.this,android.R.layout.simple_spinner_item,years);

        spinYear.setAdapter(adapter);
    }

    public void onClickBtnCreate(AdminReportFour view){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerValue=spinYear.getSelectedItem().toString();
                initTable();
                getReport();
            }
        });
    }
}
