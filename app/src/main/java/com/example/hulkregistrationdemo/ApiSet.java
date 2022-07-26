package com.example.hulkregistrationdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiSet {
    @GET("edgecourse/api/get_nonce/?controller=user&method=register")
    Call<ModelClass> getData();

    @FormUrlEncoded
    @POST("edgecourse/api/get_nonce/?")
    Call<ModelClassRegister> getRegisterInformation(
            @Field("username")String username,
            @Field("email") String email,
            @Field("password") String password

    );


}
