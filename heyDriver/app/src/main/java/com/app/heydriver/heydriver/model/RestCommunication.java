package com.app.heydriver.heydriver.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.ControladorSQLite;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class RestCommunication {
    private String ip = "192.168.0.107";
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
        try{            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
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
            ArrayList<Car> _response = new ArrayList<>();
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

            Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PROMEDIUM",null);
            if (promedium_cursor.moveToFirst() ) {
                Cursor _promedium = db.rawQuery("SELECT car_name, car_model, AirFuel_Ratio, Timing_Advance, Engine_RPM, " +
                        "Short_Term_Fuel_Trim2, Short_Term_Fuel_Trim1, Long_Term_Fuel_Trim2, Long_Term_Fuel_Trim1, " +
                        "Mass_Air_Flow, Engine_Coolant_Temperature, Engine_Load, Intake_Manifold_Pressure, Air_Intake_Temperature, vin_dtc" +
                        " FROM CAR_PROMEDIUM", null);
                if (_promedium.moveToFirst()) {
                    do {
                        String stringcode = getStringCode(callMethodGetANNAnalysis(_promedium.getString(14),_promedium.getString(0), _promedium.getString(1), _promedium.getFloat(2), _promedium.getFloat(3),
                                _promedium.getFloat(4), _promedium.getFloat(5), _promedium.getFloat(6), _promedium.getFloat(7), _promedium.getFloat(8),
                                _promedium.getFloat(9), _promedium.getFloat(10), _promedium.getFloat(11), _promedium.getFloat(12), _promedium.getFloat(13)));

                        savePrediction(_promedium.getString(14),_promedium.getString(0), _promedium.getString(1),stringcode);

                    } while (_promedium.moveToNext());

                }
            }
            if(reading != null){
                for (ObdData obdData : reading) {
                    if (obdData.getLTFT2()==Float.valueOf(0)){ obdData.setLTFT2(obdData.getLTFT1());}
                    if (obdData.getSTFT2()==Float.valueOf(0)){ obdData.setSTFT2(obdData.getSTFT1());}
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


    public int callMethodSynchronizationContext(List<ObdData> reading, Context context) throws Exception {
        ControladorSQLite controladorSQLite=  new ControladorSQLite(context);
        SQLiteDatabase db = controladorSQLite.getWritableDatabase();
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();

            Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PROMEDIUM",null);
            if (promedium_cursor.moveToFirst() ) {
                Cursor _promedium = db.rawQuery("SELECT car_name, car_model, AirFuel_Ratio, Timing_Advance, Engine_RPM, " +
                        "Short_Term_Fuel_Trim2, Short_Term_Fuel_Trim1, Long_Term_Fuel_Trim2, Long_Term_Fuel_Trim1, " +
                        "Mass_Air_Flow, Engine_Coolant_Temperature, Engine_Load, Intake_Manifold_Pressure, Air_Intake_Temperature, vin_dtc" +
                        " FROM CAR_PROMEDIUM", null);
                if (_promedium.moveToFirst()) {
                    do {
                        String stringcode = getStringCode(callMethodGetANNAnalysis(_promedium.getString(14),_promedium.getString(0), _promedium.getString(1), _promedium.getFloat(2), _promedium.getFloat(3),
                                _promedium.getFloat(4), _promedium.getFloat(5), _promedium.getFloat(6), _promedium.getFloat(7), _promedium.getFloat(8),
                                _promedium.getFloat(9), _promedium.getFloat(10), _promedium.getFloat(11), _promedium.getFloat(12), _promedium.getFloat(13)));

                        savePredictionContext(_promedium.getString(14),_promedium.getString(0), _promedium.getString(1),stringcode,context);

                    } while (_promedium.moveToNext());

                }
            }
            if(reading != null){
                for (ObdData obdData : reading) {
                    if (obdData.getLTFT2()==Float.valueOf(0)){ obdData.setLTFT2(obdData.getLTFT1());}
                    if (obdData.getSTFT2()==Float.valueOf(0)){ obdData.setSTFT2(obdData.getSTFT1());}
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

    private String getStringCode(ArrayList<Integer> integers) {
        StringBuilder _return = new StringBuilder();
        for(int x=0;x<integers.size();x++) {
            _return.append(integers.get(x).toString());
        }
        return _return.toString();
    }

    public void savePrediction(String vin, String car_name,String car_model, String code) {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ControladorSQLite.DatosTabla.VIN_DTC,String.valueOf(vin));
        valores.put(ControladorSQLite.DatosTabla.CAR_NAME,String.valueOf(car_name));
        valores.put(ControladorSQLite.DatosTabla.CAR_MODEL,String.valueOf(car_model));
        valores.put(ControladorSQLite.DatosTabla.PREDICTION_CODE,String.valueOf(code));
        try
        {
            Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PREDICTION WHERE vin_dtc='"+vin+"'", null);
            if (promedium_cursor.moveToFirst() ) {
                if(promedium_cursor.getInt(0)>=1)
                {
                    long IdUpdate = db.update(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, valores, "vin_dtc='" + vin + "'", null);
                }
                else
                {
                    long IdGuardado = db.insert(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, "id", valores);
                }
            }
        }
        catch (Exception e){
            String b = e.toString();
        }
    }

    public void savePredictionContext(String vin, String car_name,String car_model, String code, Context context) {
        ControladorSQLite controladorSQLite=  new ControladorSQLite(context);
        SQLiteDatabase db = controladorSQLite.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(ControladorSQLite.DatosTabla.VIN_DTC,String.valueOf(vin));
        valores.put(ControladorSQLite.DatosTabla.CAR_NAME,String.valueOf(car_name));
        valores.put(ControladorSQLite.DatosTabla.CAR_MODEL,String.valueOf(car_model));
        valores.put(ControladorSQLite.DatosTabla.PREDICTION_CODE,String.valueOf(code));
        try
        {
            Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PREDICTION WHERE vin_dtc='"+vin+"'", null);
            if (promedium_cursor.moveToFirst() ) {
                if(promedium_cursor.getInt(0)>=1)
                {
                    long IdUpdate = db.update(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, valores, "vin_dtc='" + vin + "'", null);
                }
                else
                {
                    long IdGuardado = db.insert(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, "id", valores);
                }
            }
        }
        catch (Exception e){
            String b = e.toString();
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

    public ArrayList<Integer> callMethodGetANNAnalysis(String vin, String brand, String model, double air_fuel_ratio, double timeadvance, double rpm,
                                                       double stft2, double stft1, double ltft2, double ltft1, double maf, double coolant,
                                                       double motorcharge, double pressure_at, double admission_temp) throws Exception {
        try {
            conn = null;
            Gson gson = new GsonBuilder().create();
            BufferedReader br = communicate("GET", "annStudies?serial=" + vin + "&brand=" + brand + "&model=" + model + "&air_fuel_ratio="
                    + air_fuel_ratio + "&timeadvance=" + timeadvance + "&rpm=" + rpm + "&stft2="
                    + stft2 + "&stft1=" + stft1 + "&ltft2=" + ltft2 + "&ltft1=" + ltft1 + "&maf=" + maf
                    + "&coolant=" + coolant + "&motorcharge=" + motorcharge + "&pressure_at=" + pressure_at
                    + "&admission_temp=" + admission_temp);
            String output;
            ArrayList<Integer> response = new ArrayList<Integer>();
            ArrayList<Integer> _response = new ArrayList<Integer>();
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();

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
}
