package com.example.wk.sigah.controller;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wk.sigah.R;
import com.example.wk.sigah.model.BestPelanggan;
import com.example.wk.sigah.model.GMailSender;
import com.example.wk.sigah.model.StoragePremission;
import com.example.wk.sigah.model.bestPelangganView;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
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

/**
 * Created by Kharisma on 15/04/2018.
 */

public class FragmentReportOne extends Fragment {

    public List<BestPelanggan>getData;
    public List<bestPelangganView> listReport1;
    RecycleViewAdapaterReport1 recycleViewAdapaterReport1;
    RecyclerView myRecycleView;
    public View view;
    public Button btnCreatePDF;
    public  List<BestPelanggan> tempData;
    private ProgressDialog progress;

    public FragmentReportOne(List<BestPelanggan> getData) {
        this.getData = getData;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_report_one,container,false);

        myRecycleView=(RecyclerView)view.findViewById(R.id.viewReportOne);
        recycleViewAdapaterReport1 =new RecycleViewAdapaterReport1(getContext(),listReport1);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recycleViewAdapaterReport1);

        tempData=getData;
        btnCreatePDF=(Button)view.findViewById(R.id.btnCreatePdf);

        onClickBtnCreatePdf(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listReport1 = new ArrayList<>();
        NumberFormat nf=NumberFormat.getInstance();

        for (int i = 0; i < getData.size(); i++) {
            Double tempMoney=Double.parseDouble(getData.get(i).getTotalPay());
            String money=nf.format(tempMoney);
            listReport1.add(new bestPelangganView(getData.get(i).getNama(),
                    getData.get(i).getPoin(),
                    money,Integer.toString(i+1)));
        }
    }

    public void onClickBtnCreatePdf(FragmentReportOne view){
        btnCreatePDF.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                progress=new ProgressDialog(getActivity());
                progress.setCancelable(false);
                progress.setMessage("Generating PDF File. . .");
                progress.show();
                Toast.makeText(getActivity(), "Please wait generating PDF...", Toast.LENGTH_LONG).show();
                createPDF();
                progress.dismiss();
            }
        });
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
        storagePermissiion.verifyStoragePermissions(getActivity());
        Document doc=new Document(PageSize.A4);
        String temp= Environment.getExternalStorageDirectory()+"/SIGAH-ReportBestCustomer.pdf";

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
            titleReport=new Paragraph("Report Best Customer of "+getYear,styleTitleReport);
            titleReport.setAlignment(titleReport.ALIGN_CENTER);
            doc.add(titleReport);
            doc.add(Chunk.NEWLINE);
            ///////////////////////////////
            doc.add(createFirstTable(getData));
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
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
        }catch(DocumentException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PdfPTable createFirstTable(List<BestPelanggan> temp) {
        // a table with three columns
        PdfPTable table = new PdfPTable(4);
        // the cell object

       //add the column name
        table.addCell("Number");
        table.addCell("Name");
        table.addCell("Total Reservation");
        table.addCell("Total Payment (IDR)");

        //adding data
        NumberFormat nf=NumberFormat.getInstance();
        for(int i=0;i<temp.size();i++){
            Double tempMoney=Double.parseDouble(temp.get(i).getTotalPay());
            String money=nf.format(tempMoney);

            table.addCell(String.valueOf(i+1));
            table.addCell(temp.get(i).getNama());
            table.addCell(temp.get(i).getPoin());
            table.addCell(money);
        }
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
                        Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
                    }
                }
            }).start();
            Toast.makeText(getActivity(),"Email has been send, please check your email",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(),"Email is unregistered in SIGAH",Toast.LENGTH_SHORT).show();
        }
    }

}
