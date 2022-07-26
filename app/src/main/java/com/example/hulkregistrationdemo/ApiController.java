package com.example.hulkregistrationdemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String url="https://hulkenstein.com/";
    private static ApiController clientObj;
    private static Retrofit retrofit;

    ApiController()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiController getInstance()
    {
        if (clientObj==null)
            clientObj=new ApiController();

        return clientObj;
    }

    ApiSet getApi()
    {
        return retrofit.create(ApiSet.class);
    }
}
