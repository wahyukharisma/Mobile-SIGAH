package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 15/04/2018.
 */

public class NewPelangganList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<NewPelanggan> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<NewPelanggan> getResult() {
        return result;
    }

    public void setResult(List<NewPelanggan> result) {
        this.result = result;
    }
}
