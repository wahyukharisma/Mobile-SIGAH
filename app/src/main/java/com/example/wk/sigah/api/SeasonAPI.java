package com.example.wk.sigah.api;

import com.example.wk.sigah.model.SeasonList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kharisma on 06/04/2018.
 */

public interface SeasonAPI {
    @GET("viewSeason.php")
    Call<SeasonList> getAllSeason();
}
