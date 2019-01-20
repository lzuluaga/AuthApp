package com.cedesistemas.authapp.services;


import com.cedesistemas.authapp.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IServices {


    @GET("user/auth")
    Call<User> logIn(@Query("email") String email, @Query("password")String password);
}
