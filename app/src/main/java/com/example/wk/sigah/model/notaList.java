package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 06/05/2018.
 */

public class notaList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<nota> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<nota> getResult() {
        return result;
    }

    public void setResult(List<nota> result) {
        this.result = result;
    }
}
