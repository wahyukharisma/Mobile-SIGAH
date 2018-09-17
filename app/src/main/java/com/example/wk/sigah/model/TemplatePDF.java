package com.example.wk.sigah.model;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kharisma on 26/04/2018.
 */

public class TemplatePDF {
    private Context context;
    private File pdfFile;
    private com.itextpdf.text.Document document;
    private Paragraph paragraph;

    private Font fTitle=new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fSubTitle=new Font(Font.FontFamily.TIMES_ROMAN,18,Font.BOLD);
    private Font fText=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);

    public TemplatePDF(Context context) {
        this.context = context;
    }
    public void createFile(){
        File folder=new File(Environment.getExternalStorageDirectory().toString(),"PDF");

        if(!folder.exists())
            folder.mkdir();
        pdfFile=new File(folder,"TemplatePDF.pdf");
    }
    public void addMetaData(String title,String subject,String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }
    public void addTitles(String title,String subTitle,String date){
        paragraph=new Paragraph();
        addChild(new Paragraph(title,fTitle));
        addChild(new Paragraph(subTitle,fSubTitle));
        addChild(new Paragraph("Date Printed :"+date,fSubTitle));
        paragraph.setSpacingAfter(30);

        try{
            document.add(paragraph);
        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }

    }
    private void addChild(Paragraph childParagraf){
        childParagraf.setAlignment(Element.ALIGN_CENTER);
    }
    public void addParagraf(String text){
        try{
            paragraph=new Paragraph(text,fText);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        }catch (Exception e){
            Log.e("openDocument",e.toString());
        }
    }
    public void createTable(String[]header, ArrayList<String>clients){
        paragraph=new Paragraph();
        paragraph.setFont(fText);
        PdfPTable pdfPTable=new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(100);
        PdfPCell pdfPCell;
        int indexC=0;

        while (indexC<header.length){
            pdfPCell=new PdfPCell(new Phrase(header[indexC++],fSubTitle));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.ORANGE);
            pdfPTable.addCell(pdfPCell);
        }

        for(int indexR=0;indexR<clients.size();indexR++){
            String row=clients.get(indexR);
        }
    }
}
