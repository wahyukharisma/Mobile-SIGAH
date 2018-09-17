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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.api.PelangganAPI;
import com.example.wk.sigah.api.RoomAPI;
import com.example.wk.sigah.model.GMailSender;
import com.example.wk.sigah.model.GetURL;
import com.example.wk.sigah.model.NewPelanggan;
import com.example.wk.sigah.model.NewPelangganList;
import com.example.wk.sigah.model.PelangganRoom;
import com.example.wk.sigah.model.PelangganRoomList;
import com.example.wk.sigah.model.ReportThree;
import com.example.wk.sigah.model.ReportTwo;
import com.example.wk.sigah.model.StoragePremission;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
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
 * Created by Kharisma on 16/04/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class AdminReportThree extends AppCompatActivity{

    //chart
    private BarChart chart;
    float barWidth=0.3f,barSpace=0f,groupSpace=0.4f;


    //Table
    TextView txtTotalAll;
    TextView txtTotalSup,txtTotalDD,txtTotalED,txtTotalJS;
    TextView txtSupPer,txtDDPer,txtEDPer,txtJSPer;
    TextView txtSupGr,txtDDGr,txtEDGr,txtJSGr;
    Integer TotalAll;
    Integer TotalSup,TotalDD,TotalED,TotalJS;
    Integer SupPer,DDPer,EDPer,JSPer;
    Integer SupGr,DDGr,EDGr,JSGr;

    //Retorfit
    private ProgressDialog progress;
    private String URL;
    private GetURL getURL=new GetURL(URL);

    Spinner spinYear;
    String spinnerSelected;
    Button btnCreate,btnCreatePdf;

    //Pdf Data
    List<ReportThree> tempReport=new ArrayList<>();
    List<PelangganRoom> tempPelRom=new ArrayList<>();

    Integer getYear=Calendar.getInstance().get(Calendar.YEAR);
    ImageView imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_report_3);

        spinYear=(Spinner)findViewById(R.id.spinYear);
        btnCreate=(Button)findViewById(R.id.btnCreate);
        btnCreatePdf=(Button)findViewById(R.id.btnCreatePdf);
        imgBack=(ImageView)findViewById(R.id.imgBack);

        //initSpinner
        initSpinner();

        //initLayer
        initTabel();

        //initDataPdf
        initDataPdf(tempReport);

        //call Chart
        chart=(BarChart)findViewById(R.id.chartReportThree);
        onClickBackListener(this);
        onClickBtnCreate(this);
        onClickBtnCreatePdf(this);
    }

    public void onClickBtnCreatePdf(AdminReportThree view){
        btnCreatePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPDF();
            }
        });
    }
    public void onClickBackListener(AdminReportThree view){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setTotal(){
        //set total
        TotalSup=SupGr+SupPer;
        TotalDD=DDGr+DDPer;
        TotalED=EDGr+EDPer;
        TotalJS=JSGr+JSPer;
        TotalAll=TotalDD+TotalSup+TotalED+TotalJS;

        txtTotalSup.setText(Integer.toString(TotalSup));
        txtTotalDD.setText(Integer.toString(TotalDD));
        txtTotalED.setText(Integer.toString(TotalED));
        txtTotalJS.setText(Integer.toString(TotalJS));
        txtTotalAll.setText(Integer.toString(TotalAll));
    }
    public void initTabel(){
        txtTotalAll=(TextView)findViewById(R.id.txtTotalAll);
        txtTotalSup=(TextView)findViewById(R.id.txtTotalSup);
        txtTotalDD=(TextView)findViewById(R.id.txtTotalDD);
        txtTotalED=(TextView)findViewById(R.id.txtTotalED);
        txtTotalJS=(TextView)findViewById(R.id.txtTotalJS);
        txtSupGr=(TextView)findViewById(R.id.txtGroupSup);
        txtSupPer=(TextView)findViewById(R.id.txtPersonalSup);
        txtDDGr=(TextView)findViewById(R.id.txtGroupDD);
        txtDDPer=(TextView)findViewById(R.id.txtPersonalDD);
        txtEDGr=(TextView)findViewById(R.id.txtGroupED);
        txtEDPer=(TextView)findViewById(R.id.txtPersonalED);
        txtJSGr=(TextView)findViewById(R.id.txtGroupJS);
        txtJSPer=(TextView)findViewById(R.id.txtPersonalJS);

        TotalSup=TotalDD=TotalED=TotalJS=0;
        SupPer=DDPer=EDPer=JSPer=0;
        SupGr=DDGr=EDGr=JSGr=0;
        TotalAll=TotalSup+TotalDD+TotalED+TotalJS;

        txtSupPer.setText(Integer.toString(SupPer));
        txtDDPer.setText(Integer.toString(DDPer));
        txtEDPer.setText(Integer.toString(EDPer));
        txtJSPer.setText(Integer.toString(JSPer));

        txtSupGr.setText(Integer.toString(SupGr));
        txtDDGr.setText(Integer.toString(DDGr));
        txtEDGr.setText(Integer.toString(EDGr));
        txtJSGr.setText(Integer.toString(JSGr));

        txtTotalSup.setText(Integer.toString(TotalSup));
        txtTotalDD.setText(Integer.toString(TotalDD));
        txtTotalED.setText(Integer.toString(TotalED));
        txtTotalJS.setText(Integer.toString(TotalJS));

        txtTotalAll.setText(Integer.toString(TotalAll));

    }

    public void initDataPdf(List<ReportThree> tempReport){
        String[] type={"Superior","Deluxe","Executive Deluxe","Junior Suite"};
        for(int i=0;i<4;i++){
            ReportThree temp=new ReportThree(String.valueOf(i+1),type[i],"0","0","0");
            tempReport.add(i,temp);
        }
    }

    public void getData(){
        progress=new ProgressDialog(AdminReportThree.this);
        progress.setCancelable(false);
        progress.setMessage("Loading. . .");
        progress.show();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getURL.GetMyURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RoomAPI api=retrofit.create(RoomAPI.class);
        Call<PelangganRoomList> call=api.getPelangganKamar(spinnerSelected);

        call.enqueue(new Callback<PelangganRoomList>() {
            @Override
            public void onResponse(Call<PelangganRoomList> call, Response<PelangganRoomList> response) {
                progress.dismiss();
                Integer check=0;
                tempPelRom=response.body().getResult();
                for(int i=0;i<response.body().getResult().size();i++){
                    check=1;
                    if(response.body().getResult().get(i).getRoomType().equals("Superior")){
                        if(response.body().getResult().get(i).getReservationtype().equals("1")){
                            txtSupPer.setText(response.body().getResult().get(i).getTotal());
                            SupPer=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                        else{
                            txtSupGr.setText(response.body().getResult().get(i).getTotal());
                            SupGr=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                    }else if(response.body().getResult().get(i).getRoomType().equals("Deluxe")){
                        if(response.body().getResult().get(i).getReservationtype().equals("1")){
                            txtDDPer.setText(response.body().getResult().get(i).getTotal());
                            DDPer=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                        else{
                            txtDDGr.setText(response.body().getResult().get(i).getTotal());
                            DDGr=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                    }else if(response.body().getResult().get(i).getRoomType().equals("Executive Deluxe")){
                        if(response.body().getResult().get(i).getReservationtype().equals("1")){
                            txtEDPer.setText(response.body().getResult().get(i).getTotal());
                            EDPer=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                        else{
                            txtEDGr.setText(response.body().getResult().get(i).getTotal());
                            EDGr=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                    }else{
                        if(response.body().getResult().get(i).getReservationtype().equals("1")){
                            txtJSPer.setText(response.body().getResult().get(i).getTotal());
                            JSPer=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                        else{
                            txtJSGr.setText(response.body().getResult().get(i).getTotal());
                            JSGr=Integer.parseInt(response.body().getResult().get(i).getTotal());
                        }
                    }
                }
                if(check==1){
                    setTotal();
                    setChart(Boolean.TRUE);
                }else{
                    initTabel();
                    setChart(Boolean.FALSE);

                }

            }

            @Override
            public void onFailure(Call<PelangganRoomList> call, Throwable t) {
                t.printStackTrace();
                progress.dismiss();
                Toast.makeText(AdminReportThree.this,"Network Connection Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setChart(Boolean create){
        if(create==Boolean.TRUE){
            chart.setFitBars(true);
            chart.setDescription(null);
            chart.setPinchZoom(false);
            chart.setDrawBarShadow(false);
            chart.setDrawGridBackground(false);
            chart.setScaleEnabled(false);

            int groupCount=4;

            ArrayList xVals=new ArrayList();

            //make x label
            xVals.add("Sup");
            xVals.add("DD");
            xVals.add("ED");
            xVals.add("JS");

            //init dummy data
            ArrayList yVals1=new ArrayList();
            ArrayList yVals2=new ArrayList();

            yVals1.add(new BarEntry(1,SupPer));
            yVals2.add(new BarEntry(1,SupGr));
            yVals1.add(new BarEntry(2,DDPer));
            yVals2.add(new BarEntry(2,DDGr));
            yVals1.add(new BarEntry(3,EDPer));
            yVals2.add(new BarEntry(3,EDGr));
            yVals1.add(new BarEntry(4,JSPer));
            yVals2.add(new BarEntry(4,JSGr));

            //Draw graph
            BarDataSet set1,set2;
            set1=new BarDataSet(yVals1,"Personal");
            set1.setColor(Color.BLUE);
            set2=new BarDataSet(yVals2,"Group");
            set2.setColor(Color.GREEN);

            BarData data=new BarData(set1,set2);
            data.setValueFormatter(new LargeValueFormatter());
            chart.setData(data);
            chart.getBarData().setBarWidth(barWidth);
            chart.getXAxis().setAxisMaximum(0);
            chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
            chart.groupBars(1, groupSpace, barSpace);
            chart.getData().setHighlightEnabled(false);
            chart.animateXY(2000,2000);
            chart.invalidate();

            //draw indicator
            Legend l = chart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(true);
            l.setYOffset(20f);
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
            xAxis.setAxisMaximum(6);
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
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(AdminReportThree.this,android.R.layout.simple_spinner_item,years);

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
        storagePermissiion.verifyStoragePermissions(AdminReportThree.this);
        Document doc=new Document(PageSize.A4);
        String temp= Environment.getExternalStorageDirectory()+"/SIGAH-ReportTotalCustomerEveryRoom.pdf";

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
            titleReport=new Paragraph("Report Total Customer in Every Room "+getYear,styleTitleReport);
            titleReport.setAlignment(titleReport.ALIGN_CENTER);
            doc.add(titleReport);
            doc.add(Chunk.NEWLINE);
            ///////////////////////////////
            doc.add(createFirstTable(tempPelRom,tempReport));
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
            Toast.makeText(AdminReportThree.this, "Success", Toast.LENGTH_SHORT).show();
        }catch(DocumentException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Not Clear
    public static PdfPTable createFirstTable(List<PelangganRoom> temp, List<ReportThree> tempReportThree) {
        Integer tempNewPel=0;
        Integer tempTotalType;
        // a table with three columns
        PdfPTable table = new PdfPTable(5);
        // the cell object
        PdfPCell cell=new PdfPCell();
        //
        Paragraph total=new Paragraph("Total All");

        //add the column name
        table.addCell("Number");
        table.addCell("Room Type");
        table.addCell("Group");
        table.addCell("Personal");
        table.addCell("Total");

        //adding data
        for(int i=0;i<temp.size();i++){
            for(int j=0;j<4;j++)
            {
                if(tempReportThree.get(j).getRoomType().equals(temp.get(i).getRoomType())){
                    if(temp.get(i).getReservationtype().equalsIgnoreCase("Personal")){
                        tempReportThree.get(j).setPersonal(temp.get(i).getTotal());
                    }else{
                        tempReportThree.get(j).setGroup(temp.get(i).getTotal());
                    }
                }
            }
        }

        for(int i=0;i<tempReportThree.size();i++){
            tempTotalType=Integer.parseInt(tempReportThree.get(i).getPersonal())+Integer.parseInt(tempReportThree.get(i).getPersonal());

            table.addCell(tempReportThree.get(i).getNumber());
            table.addCell(tempReportThree.get(i).getRoomType());
            table.addCell(tempReportThree.get(i).getPersonal());
            table.addCell(tempReportThree.get(i).getGroup());
            table.addCell(String.valueOf(tempTotalType));
            tempNewPel=tempNewPel+tempTotalType;
        }
        cell.setColspan(4);
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
                        Toast.makeText(AdminReportThree.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }
            }).start();
            Toast.makeText(AdminReportThree.this,"Email has been send, please check your email",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(AdminReportThree.this,"Email is unregistered in SIGAH",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBtnCreate(AdminReportThree view){
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerSelected=spinYear.getSelectedItem().toString();
                initTabel();
                getData();
            }
        });
    }


}
