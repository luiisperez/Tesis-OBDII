package com.app.heydriver.heydriver.model;


import com.app.heydriver.heydriver.common.Entities.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RestCommunication {
    private String ip = "192.168.1.102";
    private static HttpURLConnection conn;

    private BufferedReader communicate(String _requetMethod, String _restfulMethod) throws IOException {
        try {
            URL url = new URL("http://"+ip+":8084/HeyDriverWS/webresources/heydriverws/" + _restfulMethod);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(_requetMethod);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            return br;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public User callMethodPrueba() throws Exception {
        try {
            conn = null;
            BufferedReader br = communicate("GET", "prueba");
            String output;
            User _user = new User();
            while ((output = br.readLine()) != null) {
                Gson gson = new GsonBuilder().create();
                _user = gson.fromJson(output, User.class);
            }
            conn.disconnect();
            return _user;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public User callMethodSignUpUser(User u) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "signUpUser?user=" + URLEncoder.encode(gson.toJson( u ).toString(), "UTF-8"));
            String output;
            User _user = new User();
            while ((output = br.readLine()) != null) {
                _user = gson.fromJson(output, User.class);
            }
            conn.disconnect();
            return _user;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public User callMethodLoginUser(User u) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "userLogin?user=" + URLEncoder.encode(gson.toJson( u ).toString(), "UTF-8"));
            String output;
            User _user = new User();
            while ((output = br.readLine()) != null) {
                _user = gson.fromJson(output, User.class);
            }
            conn.disconnect();
            return _user;
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
