package com.example.wk.sigah.api;

import com.example.wk.sigah.model.UserList;
import com.example.wk.sigah.model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Kharisma on 01/04/2018.
 */

public interface UserAPI {
    @FormUrlEncoded
    @POST("insertUser.php")
    Call<Value> CreatedUser(@Field("id_role")Integer id_role,
                            @Field("username")String username,
                            @Field("password")String password,
                            @Field("email")String email,
                            @Field("id_pel")String id_pel);
    @FormUrlEncoded
    @POST("updateUserEmailBy.php")
    Call<Value> updateUserEmail(@Field("email")String email,
                                @Field("emailTemp")String emailTemp);

    @FormUrlEncoded
    @POST("updatePasswordUser.php")
    Call<Value> updatePassword(@Field("id_pel")String id_pel,
                               @Field("password")String password,
                               @Field("oldPassword")String oldPassword);
    @FormUrlEncoded
    @POST("updateUserPassword.php")
    Call<UserList> updatePassword(@Field("email")String email);

}
