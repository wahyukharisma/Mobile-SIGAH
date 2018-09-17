package com.example.wk.sigah.model;

/**
 * Created by Kharisma on 06/04/2018.
 */

public class seasonView {
    String start_date;
    String end_date;
    String name;
    String roomName;
    Integer image;
    String price;

    public seasonView(){}

    public seasonView(String start_date, String end_date, String name, Integer image, String price,String roomName) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.name = name;
        this.image = image;
        this.price = price;
        this.roomName=roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
