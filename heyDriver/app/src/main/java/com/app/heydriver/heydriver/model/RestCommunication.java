package com.app.heydriver.heydriver.model;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class RestCommunication {
    private String ip = "192.168.0.102";
    private static HttpURLConnection conn;


    private BufferedReader communicate(String _requetMethod, String _restfulMethod) throws IOException {
        try {
            URL url = new URL("http://"+ip+":8084/HeyDriverWS/webresources/heydriverws/" + _restfulMethod);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(_requetMethod);
            conn.setRequestProperty("Accept", "application/json");
            conn.setConnectTimeout(1000); //set timeout to 5 seconds

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

    //get the actual IP
    public static String getIP(){
        List<InetAddress> addrs;
        String address = "";
        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for(NetworkInterface intf : interfaces){
                addrs = Collections.list(intf.getInetAddresses());
                for(InetAddress addr : addrs){
                    if(!addr.isLoopbackAddress() && addr instanceof Inet4Address){
                        address = addr.getHostAddress().toUpperCase(new Locale("es", "MX"));
                    }
                }
            }
        }catch (Exception e){
            Log.w(TAG, "Ex getting IP value " + e.getMessage());
        }
        return address;
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

    public int callMethodAddVehicle(String username, Car c) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "addVehicle?user=" + username + "&car=" + URLEncoder.encode(gson.toJson( c ).toString(), "UTF-8"));
            String output;
            int _code = 500;
            while ((output = br.readLine()) != null) {
                _code = gson.fromJson(output, Integer.class);
            }
            conn.disconnect();
            return _code;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<Car> callMethodGetUserVehicles(String username) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "getUserVehicles?user=" + username);
            String output;
            ArrayList<Car> response = new ArrayList<Car>();
            ArrayList<Car> _response = new ArrayList<Car>();
            Type listType = new TypeToken<ArrayList<Car>>() {}.getType();

            while ((output = br.readLine()) != null) {
                _response = gson.fromJson(output, listType);
            }
            conn.disconnect();
            return _response;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public int callMethodDeleteVehicle(String username, String serial) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "removeVehicle?user=" + username + "&car=" + serial);
            String output;
            int _code = 500;
            while ((output = br.readLine()) != null) {
                _code = gson.fromJson(output, Integer.class);
            }
            conn.disconnect();
            return _code;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    // Este método recibe las lecuras que serán transmitidas al servidor
    public int callMethodSynchronization(List<ObdData> reading) throws Exception {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            if(reading != null){
                for (ObdData obdData : reading) {
                    BufferedReader br = communicate("GET", "synchronization?obddata=" + URLEncoder.encode(gson.toJson(obdData).toString(), "UTF-8"));
                    String a;
                    String _result="";
                    if ((a = br.readLine()) != null) {
                        _result = gson.fromJson(a, String.class);
                        String fecha = String.valueOf(obdData.getTime_mark().toString());
                        db.execSQL("DELETE from HISTORICO WHERE time_mark='"+fecha+"'");
                    }
                    if (_result.equals("false")) {
                        conn.disconnect();
                        db.close();
                        return 0;
                    }
                }
            }
            else
            {
                db.close();
                return 404;
            }
            conn.disconnect();
            db.close();
            return 1;
        }
        catch (Exception ex){
            db.close();
            throw ex;
        }
    }

    public ArrayList<String> callMethodGetVehicleBrands() throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "getBrands");
            String output;
            ArrayList<String> response = new ArrayList<String>();
            ArrayList<String> _response = new ArrayList<String>();
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();

            while ((output = br.readLine()) != null) {
                _response = gson.fromJson(output, listType);
            }
            conn.disconnect();
            Collections.sort(_response, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });
            return _response;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    public ArrayList<String> callMethodGetVehicleModelsByBrand(String brand) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "getModels?brand=" + brand );
            String output;
            ArrayList<String> response = new ArrayList<String>();
            ArrayList<String> _response = new ArrayList<String>();
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();

            while ((output = br.readLine()) != null) {
                _response = gson.fromJson(output, listType);
            }
            conn.disconnect();
            Collections.sort(_response, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareTo(s2);
                }
            });
            return _response;
        }
        catch (Exception ex){
            throw ex;
        }
    }
}
