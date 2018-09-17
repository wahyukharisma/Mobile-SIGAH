package com.example.wk.sigah.api;

import com.example.wk.sigah.model.ReservationList;
import com.example.wk.sigah.model.notaList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 08/04/2018.
 */

public interface ReservationAPI {
    @FormUrlEncoded
    @POST("viewHistoryBy.php")
    Call<ReservationList>viewReservationBy(@Field("email")String email);

    @FormUrlEncoded
    @POST("viewNote.php")
    Call<notaList>viewNoteBy(@Field("id_pel")String id_pel);

    @FormUrlEncoded
    @POST("cancelReservation.php")
    Call<ReservationList>cancelReservastion(@Field("kode_reservasi")String kode_reservasi);
}
