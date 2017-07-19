package com.app.heydriver.heydriver.common.Rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 20/05/17.
 */

public class ApiClient {


    public static final String BASE_URL= "http://192.168.1.111:8084/HeyDriverWS/";

    private static Retrofit retrofit = null;

    /**
     * Singleton encargado de devolver una unica instancia de la clase Retrofit
     * @return retorna una instancia de la clase Retrofit
     */
     public static Retrofit getClient(){

        Gson gson = new GsonBuilder().setLenient().create();

        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            }
        return retrofit;
    }
}
