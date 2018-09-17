package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.model.BestPelanggan;
import com.example.wk.sigah.model.GMailSender;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.NewPelanggan;
import com.example.wk.sigah.model.NewPelangganList;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.ReportTwo;
import com.example.wk.sigah.model.StoragePremission;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kharisma on 15/04/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AdminReportTwo extends AppCompatActivity {
    //chart
    private BarChart chart;
    List<NewPelanggan> tempPel=new ArrayList<>();
    ArrayList<String> xAxisLabel=new ArrayList<>();
    ArrayList<BarEntry> valueSet=new ArrayList<>();
    ArrayList<IBarDataSet> dataSets=null;
    Integer jan,feb,mar,apr,may,jun,jul,aug,sep,oct,nov,dec;
    Integer totalAll;
    public Integer tempNewPel;

    public TextView txtJan,txtFeb,txtMar,txtApr,txtMay,txtJun,txtJul,txtAug,txtSep,txtOct,txtNov,txtDec,txtTotalAll;
    public Spinner spinYear;
    public Button btnCreate,btnCreatePdf;
    String spinnerSelected;

    Integer getYear=Calendar.getInstance().get(Calendar.YEAR);
    ImageView imgBack;

    //Retorfit
    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);

    //Generate PDF
    List<ReportTwo> tempReportTwo=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_report_2);


        imgBack=(ImageView)findViewById(R.id.imgBack);
        spinYear=(Spinner)findViewById(R.id.spinYear);
        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnCreatePdf=(Button)findViewById(R.id.btnCreatePdf);

        //initSpinner
        initSpinner();

        //txtDateTemp=(TextView)findViewById(R.id.txtThisDate);
        txtTotalAll=(TextView)findViewById(R.id.txtTotalAll);
        //txtDateTemp.setText(thisDate);
        //GetTxtTotal
        txtJan=(TextView)findViewById(R.id.txtTotalJan);
        txtFeb=(TextView)findViewById(R.id.txtTotalFeb);
        txtMar=(TextView)findViewById(R.id.txtTotalMar);
        txtApr=(TextView)findViewById(R.id.txtTotalApr);
        txtMay=(TextView)findViewById(R.id.txtTotalMay);
        txtJun=(TextView)findViewById(R.id.txtTotalJun);
        txtJul=(TextView)findViewById(R.id.txtTotalJul);
        txtAug=(TextView)findViewById(R.id.txtTotalAug);
        txtSep=(TextView)findViewById(R.id.txtTotalSep);
        txtOct=(TextView)findViewById(R.id.txtTotalOct);
        txtNov=(TextView)findViewById(R.id.txtTotalNov);
        txtDec=(TextView)findViewById(R.id.txtTotalDec);

        //initPdfData
        initReportTwo(tempReportTwo);

        //Init chart
        chart=(BarChart)findViewById(R.id.chartReportTwo);
        initMonth();
        onClickBtnCreate(this);
        onClickBackListener(this);
        onClickBtnCreatePdf(this);
    }

    public void onClickBtnCreatePdf(AdminReportTwo view){
        btnCreatePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
            }
        });
    }
    public void onClickBtnCreate(AdminReportTwo view){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerSelected=spinYear.getSelectedItem().toString();
                initMonth();
                getTotalPel();
            }
        });
    }

    public void getTotalPel(){
        progress=new ProgressDialog(AdminReportTwo.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PelangganAPI api=retrofit.create(PelangganAPI.class);
        Call<NewPelangganList> call=api.getNewPel(spinnerSelected);

        call.enqueue(new Callback<NewPelangganList>() {
            @Override
            public void onResponse(Call<NewPelangganList> call, Response<NewPelangganList> response) {
                progress.dismiss();
                tempPel=response.body().getResult();
                Integer check=0;
                for(int i=0;i<response.body().getResult().size();i++){
                    check=1;
                    if(response.body().getResult().get(i).getMonth().equals("January")){
                        txtJan.setText(response.body().getResult().get(i).getNewPel());
                        jan=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("February")){
                        txtFeb.setText(response.body().getResult().get(i).getNewPel());
                        feb=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("March")){
                        txtMar.setText(response.body().getResult().get(i).getNewPel());
                        mar=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("April")){
                        txtApr.setText(response.body().getResult().get(i).getNewPel());
                        apr=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("May")){
                        txtMay.setText(response.body().getResult().get(i).getNewPel());
                        may=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("June")){
                        txtJun.setText(response.body().getResult().get(i).getNewPel());
                        jun=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("July")){
                        txtJul.setText(response.body().getResult().get(i).getNewPel());
                        jul=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("August")){
                        txtAug.setText(response.body().getResult().get(i).getNewPel());
                        aug=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("September")){
                        txtSep.setText(response.body().getResult().get(i).getNewPel());
                        sep=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("October")){
                        txtOct.setText(response.body().getResult().get(i).getNewPel());
                        oct=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else if(response.body().getResult().get(i).getMonth().equals("November")){
                        txtNov.setText(response.body().getResult().get(i).getNewPel());
                        nov=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }else{
                        txtDec.setText(response.body().getResult().get(i).getNewPel());
                        dec=Integer.parseInt(response.body().getResult().get(i).getNewPel());
                    }
                    tempNewPel=tempNewPel+Integer.parseInt(response.body().getResult().get(i).getNewPel());
                }
                if (check==1){
                    txtTotalAll.setText(Integer.toString(tempNewPel));
                    initChart(Boolean.TRUE);
                }else{
                    initChart(Boolean.FALSE);
                    initMonth();
                }
            }

            @Override
            public void onFailure(Call<NewPelangganList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(AdminReportTwo.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initChart(Boolean create){
        if(create==Boolean.TRUE){
            chart.setFitBars(true);
            chart.setDescription(null);
            chart.setPinchZoom(false);
            chart.setDrawGridBackground(false);
            chart.setScaleEnabled(false);
            //Label
            xAxisLabel.add("Jan");
            xAxisLabel.add("Feb");
            xAxisLabel.add("Mar");
            xAxisLabel.add("Apr");
            xAxisLabel.add("May");
            xAxisLabel.add("Jun");
            xAxisLabel.add("Jul");
            xAxisLabel.add("Aug");
            xAxisLabel.add("Sep");
            xAxisLabel.add("Oct");
            xAxisLabel.add("Nov");
            xAxisLabel.add("Dec");
            XAxis xAxis= chart.getXAxis();
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return xAxisLabel.get((int)value);
                }
            });
            //Bar Entry
            valueSet.add(new BarEntry(0,jan));
            valueSet.add(new BarEntry(1,feb));
            valueSet.add(new BarEntry(2,mar));
            valueSet.add(new BarEntry(3,apr));
            valueSet.add(new BarEntry(4,may));
            valueSet.add(new BarEntry(5,jun));
            valueSet.add(new BarEntry(6,jul));
            valueSet.add(new BarEntry(7,aug));
            valueSet.add(new BarEntry(8,sep));
            valueSet.add(new BarEntry(9,oct));
            valueSet.add(new BarEntry(10,nov));
            valueSet.add(new BarEntry(11,dec));

            //Bar Data Set
            BarDataSet barDataSet=new BarDataSet(valueSet,"Month");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setDrawValues(true);

            dataSets=new ArrayList<>();
            dataSets.add(barDataSet);

            YAxis yAxis=chart.getAxisRight();
            yAxis.setEnabled(false);

            BarData barData=new BarData(dataSets);
            chart.setExtraOffsets(0,0,0,20);
            chart.setData(barData);
            chart.animateXY(2000,2000);
            chart.invalidate();
        }else if(create==Boolean.FALSE){
            chart.clear();
            chart.invalidate();
        }


    }
    public void initMonth(){
        jan=feb=mar=apr=may=jun=jul=aug=sep=oct=nov=dec=0;
        totalAll=jan+feb+mar+apr+may+jun+jul+aug+sep+oct+nov+dec;
        tempNewPel=0;

        txtJan.setText(Integer.toString(jan));
        txtFeb.setText(Integer.toString(feb));
        txtMar.setText(Integer.toString(mar));
        txtApr.setText(Integer.toString(apr));
        txtMay.setText(Integer.toString(may));
        txtJun.setText(Integer.toString(jun));
        txtJul.setText(Integer.toString(jul));
        txtAug.setText(Integer.toString(aug));
        txtSep.setText(Integer.toString(sep));
        txtOct.setText(Integer.toString(oct));
        txtNov.setText(Integer.toString(nov));
        txtDec.setText(Integer.toString(dec));

        txtTotalAll.setText(Integer.toString(totalAll));
    }

    public void onClickBackListener(AdminReportTwo view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initSpinner(){
        ArrayList<String> years=new ArrayList<String>();
        for(int i=2015;i<=getYear;i++){
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AdminReportTwo.this,android.R.layout.simple_spinner_item,years);
        spinYear.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createPDF()
    {

        //Get this date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-MMM-yyyy");
        String thisDate=df.format(c);

        //Storage permission and save in directory
        StoragePremission storagePermissiion=new StoragePremission();
        storagePermissiion.verifyStoragePermissions(AdminReportTwo.this);
        Document doc=new Document(PageSize.A4);
        String temp= Environment.getExternalStorageDirectory()+"/SIGAH-ReportNewCustomer.pdf";

        try{
            PdfWriter.getInstance(doc,new FileOutputStream(temp));
            doc.open();

            //////////HEADER//////////
            //Add Image
            Drawable d = getResources().getDrawable(R.drawable.sigahlogo);
            BitmapDrawable bitDw = ((BitmapDrawable) d);
            Bitmap bmp = bitDw.getBitmap();
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,20,stream);
            Image image=Image.getInstance(stream.toByteArray());
            float documentWidth = doc.getPageSize().getWidth() - doc.leftMargin() - doc.rightMargin();
            image.scaleToFit(documentWidth,100);
            image.setAlignment(Image.ALIGN_CENTER);
            doc.add(image);

            //Add title 1
            Paragraph p1;
            Font title = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
            // Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            p1=new Paragraph("P.Mangkubumi No.18, Yogyakarta 55233",title);
            p1.setAlignment(p1.ALIGN_CENTER);
            doc.add(p1);

            //Add title 2
            Paragraph p2;
            Font title2 = new Font(Font.FontFamily.TIMES_ROMAN, 15,Font.BOLD);
            p2=new Paragraph("Phone.(0274)487711",title2);
            p2.setAlignment(p2.ALIGN_CENTER);
            doc.add(p2);

            //Adding Spacing
            doc.add(Chunk.NEWLINE);
            ////////////////////////////

            /////////////BODY///////////////
            /////////////Title/////////////
            Integer getYear=Calendar.getInstance().get(Calendar.YEAR);
            Paragraph titleReport;
            Font styleTitleReport = new Font(Font.FontFamily.TIMES_ROMAN, 15,Font.BOLD);
            titleReport=new Paragraph("Report New Customer in "+getYear,styleTitleReport);
            titleReport.setAlignment(titleReport.ALIGN_CENTER);
            doc.add(titleReport);
            doc.add(Chunk.NEWLINE);
            ///////////////////////////////
            doc.add(createFirstTable(tempPel,tempReportTwo));
            ///////////////////////////////

            ///////////FOOTER//////////////
            String tempPrintedOn="Printed on "+thisDate;
            doc.add(Chunk.NEWLINE);
            Paragraph footer;
            Font styleFooter = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
            footer=new Paragraph(tempPrintedOn,styleFooter);
            footer.setAlignment(footer.ALIGN_RIGHT);
            doc.add(footer);
            //////////////////////////////

            doc.close();
            getDataEmail(temp);
            Toast.makeText(AdminReportTwo.this, "Success", Toast.LENGTH_SHORT).show();
        }catch(DocumentException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable createFirstTable(List<NewPelanggan> temp,List<ReportTwo> tempReportTwo) {
        Integer tempNewPel=0;

        // a table with three columns
        PdfPTable table = new PdfPTable(3);
        // the cell object
        PdfPCell cell=new PdfPCell();
        //
        Paragraph total=new Paragraph("Total All");

        //add the column name
        table.addCell("Number");
        table.addCell("Month");
        table.addCell("Total");

        //adding data
        for(int i=0;i<temp.size();i++){
            for(int j=0;j<12;j++)
            {
                if(tempReportTwo.get(j).getMonth().equals(temp.get(i).getMonth())){
                    tempReportTwo.get(j).setTotal(temp.get(i).getNewPel());
                }
            }
            tempNewPel=tempNewPel+Integer.parseInt(temp.get(i).getNewPel());
        }

        for(int i=0;i<12;i++){
            table.addCell(tempReportTwo.get(i).getNumber());
            table.addCell(tempReportTwo.get(i).getMonth());
            table.addCell(tempReportTwo.get(i).getTotal());
        }
        cell.setColspan(2);
        cell.addElement(total);
        table.addCell(cell);
        table.addCell(String.valueOf(tempNewPel));
        return table;
    }

    public void getDataEmail(final String file){
        //final String tempPDF=file+".pdf";
        Integer value=1;
        if(value==1){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        GMailSender sender = new GMailSender(
                                "sigah.work@gmail.com",
                                "sigahadmin12320");
                        sender.addAttachment(file);
                        sender.sendMail("SIGAH Report","Hello,\nhere is the report from best customer of year ,\nThank You",
                                "sigah.work@gmail.com",
                                "madewahyu39@gmail.com");
                    } catch (Exception e) {
                        Toast.makeText(AdminReportTwo.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }
            }).start();
            Toast.makeText(AdminReportTwo.this,"Email has been send, please check your email",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AdminReportTwo.this,"Email is unregistered in SIGAH",Toast.LENGTH_SHORT).show();
        }
    }

    public void initReportTwo(List<ReportTwo> tempReportTwo){
        String[] MyMounth={"January","February","March","April","May","June","July","Augst","September","October","November","December"};

        for(int i=0;i<12;i++){
            ReportTwo temp=new ReportTwo(String.valueOf(i+1),MyMounth[i],"0");
            tempReportTwo.add(i,temp);
        }

    }
}
