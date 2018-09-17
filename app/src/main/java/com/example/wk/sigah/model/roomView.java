package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 30/03/2018.
 */

public class roomView {
    private String roomtype;
    private String description;
    private String price;
    private String descriptonDetil;
    private int photo;

    public roomView(){

    }

    public roomView(String roomtype, String description, String price, int photo,String descriptonDetil) {
        this.roomtype = roomtype;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.descriptonDetil=descriptonDetil;
    }

    //Getter

    public String getDescriptonDetil() {
        return descriptonDetil;
    }

    public String getRoomtype() {
        return roomtype;
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

    //Setter

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
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

    public void setDescriptonDetil(String descriptonDetil) {
        this.descriptonDetil = descriptonDetil;
    }
}
