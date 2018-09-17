package com.example.wk.sigah.api;

import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.PelangganRoomList;
import com.example.wk.sigah.model.RoomFacilityList;
import com.example.wk.sigah.model.RoomFilterList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 09/04/2018.
 */

public interface RoomAPI {
    @GET("viewKamar.php")
    Call<RoomFacilityList>viewRoom();

    @FormUrlEncoded
    @POST("viewKamarFilter.php")
    Call<RoomFilterList>filterRoom(@Field("alamat")String alamat,
                                   @Field("tgl_check_in")String tgl_check_in,
                                   @Field("tgl_check_out")String tgl_check_out);

    @FormUrlEncoded
    @POST("viewKamarFilterPriceAsc.php")
    Call<RoomFilterList>filterRoomPriceAsc(@Field("alamat")String alamat,
                                   @Field("tgl_check_in")String tgl_check_in,
                                   @Field("tgl_check_out")String tgl_check_out);
    @FormUrlEncoded
    @POST("viewKamarFilterPriceDesc.php")
    Call<RoomFilterList>filterRoomPriceDesc(@Field("alamat")String alamat,
                                        @Field("tgl_check_in")String tgl_check_in,
                                        @Field("tgl_check_out")String tgl_check_out);

    @FormUrlEncoded
    @POST("getPelangganKamar.php")
    Call<PelangganRoomList>getPelangganKamar(@Field("tahun")String tahun);
}
