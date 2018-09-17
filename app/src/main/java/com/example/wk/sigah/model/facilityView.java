package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class facilityView {
    private String facilityname;
    private String description;
    private String price;
    private int photo;
    private String description_detil;

    public facilityView(){

    }

    public facilityView(String facilityname, String description, String price, int photo,String description_detil) {
        this.facilityname = facilityname;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.description_detil=description_detil;
    }

    //getter

    public String getDescription_detil() {
        return description_detil;
    }

    public String getFacilityname() {
        return facilityname;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public int getPhoto() {
        return photo;
    }

    //setter

    public void setDescription_detil(String description_detil) {
        this.description_detil = description_detil;
    }

    public void setFacilityname(String facilityname) {
        this.facilityname = facilityname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
