package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 17/04/2018.
 */

public class IncomeMounthList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<IncomeMounth> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<IncomeMounth> getResult() {
        return result;
    }

    public void setResult(List<IncomeMounth> result) {
        this.result = result;
    }
}
