package com.app.heydriver.heydriver.common.Rest;



import com.app.heydriver.heydriver.common.Entities.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by root on 20/05/17.
 */

public interface ApiEndPointInterface {

    /**
     * Declaracion de servicios web a los cuales les hago peticiones
     * @param userparam
     * @param passwordparam
     * @return
     */

    @GET("webresources/heydriverws/login_user")
    Call<User> loginUser(@Query("username") String userparam, @Query("password") String passwordparam);

    @GET("webresources/heydriverws/prueba")
    Call<User> prueba();


}
