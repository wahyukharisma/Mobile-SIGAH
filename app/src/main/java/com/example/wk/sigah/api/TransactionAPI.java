package com.example.wk.sigah.api;

import com.example.wk.sigah.model.IncomeBranchList;
import com.example.wk.sigah.model.IncomeMounthList;
import com.example.wk.sigah.model.PelangganRoomList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 17/04/2018.
 */

public interface TransactionAPI {
    @FormUrlEncoded
    @POST("getPendapatanPerJenisTamu.php")
    Call<IncomeMounthList> getIncomeMounth(@Field("tahun")String tahun);

    @GET("getPendapatanCabang.php")
    Call<IncomeBranchList> getIncomeBranch();
}
