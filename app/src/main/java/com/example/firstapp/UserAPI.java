package com.example.firstapp;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface UserAPI {
   @Headers("Content-Type: application/json")
   @POST("/login")
    Call<Void> login(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("/register")
    Call<Void>  signUp(@Body UserRegister userregister);

    @Headers("Content-Type: application/json")
   @GET("/user")
    Call<User> getUser(@Body User user);
}
