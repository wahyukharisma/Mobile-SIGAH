package com.example.wk.sigah.api;

import com.example.wk.sigah.model.BestPelangganList;
import com.example.wk.sigah.model.GetCountBookingList;
import com.example.wk.sigah.model.NewPelanggan;
import com.example.wk.sigah.model.NewPelangganList;
import com.example.wk.sigah.model.PelangganList;
import com.example.wk.sigah.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 01/04/2018.
 */

public interface PelangganAPI {
    @FormUrlEncoded
    @POST("insertPelanggan.php")
    Call<Value> Register(@Field("id_role")Integer id_role,
                       @Field("email")String email,
                       @Field("nama")String nama,
                       @Field("no_identitas")String no_identitas,
                       @Field("telp")String telp,
                       @Field("alamat")String alamat,
                         @Field("username")String username);

    @FormUrlEncoded
    @POST("getPelangganBy.php")
    Call<PelangganList>getPelanggan(@Field("id_pel")String id_pel);

    @FormUrlEncoded
    @POST("getCountReservationID.php")
    Call<GetCountBookingList>getCountBooking(@Field("email")String email);

    @FormUrlEncoded
    @POST("updatePelangganBy.php")
    Call<Value>updatePelangganBy(@Field("email")String email,
                                 @Field("nama")String nama,
                                 @Field("telp")String telp,
                                 @Field("emailCek")String emailCek,
                                 @Field("id_pel")String id_pel);

    @GET("getLastIDPelanggan.php")
    Call<PelangganList>getLastIDPel();

    @FormUrlEncoded
    @POST("getBestPelanggan.php")
    Call<BestPelangganList>getBestPel(@Field("tahun") String tahun);

    @FormUrlEncoded
    @POST("getNewPelanggan.php")
    Call<NewPelangganList>getNewPel(@Field("tahun")String tahun);

}
