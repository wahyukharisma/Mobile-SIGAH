package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 16/04/2018.
 */

public class PelangganRoomList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<PelangganRoom> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<PelangganRoom> getResult() {
        return result;
    }

    public void setResult(List<PelangganRoom> result) {
        this.result = result;
    }
}
