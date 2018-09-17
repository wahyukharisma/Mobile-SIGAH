package com.example.wk.sigah.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kharisma on 18/04/2018.
 */

public class IncomeBranchList {
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<IncomeBranch> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<IncomeBranch> getResult() {
        return result;
    }

    public void setResult(List<IncomeBranch> result) {
        this.result = result;
    }
}
