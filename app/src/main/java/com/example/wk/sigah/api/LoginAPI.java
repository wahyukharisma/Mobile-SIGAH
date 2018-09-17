package com.example.wk.sigah.api;

import com.example.wk.sigah.model.UserList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 02/04/2018.
 */

public interface LoginAPI {
    @FormUrlEncoded
    @POST("login.php")
    Call<UserList>Login(@Field("username")String username,
                        @Field("password")String password);
}
