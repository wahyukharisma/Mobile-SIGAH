package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 14/04/2018.
 */

public class BestPelangganList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<BestPelanggan> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<BestPelanggan> getResult() {
        return result;
    }

    public void setResult(List<BestPelanggan> result) {
        this.result = result;
    }
}
