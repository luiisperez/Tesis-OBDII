package com.app.heydriver.heydriver.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by LAPGrock on 8/1/2017.
 */

public class ManageInformation {

    public void writeUserInformation(User user, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user.get_username());
        editor.putString("password", user.get_password());
        editor.putString("email", user.get_email());
        editor.putString("firstname", user.get_firstname());
        editor.putString("lastname", user.get_lastname());

        editor.commit();
    }


    public User getUserInformation(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        User storedUser = new User(preferences.getString("username", null),
                preferences.getString("password", null),
                preferences.getString("email", null),
                preferences.getString("firstname", null),
                preferences.getString("lastname", null));
        return storedUser;

    }

    public void writeCarInformation(Car car, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("brand", car.get_brand());
        editor.putString("model", car.get_model());
        editor.putString("serial", car.get_serial());
        editor.putInt("year", car.get_year());

        editor.commit();
    }


    public Car getCarInformation(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Car storedCar = new Car(preferences.getString("serial", null),
                preferences.getString("brand", null),
                preferences.getString("model", null),
                preferences.getInt("year", 0));
        return storedCar;

    }

    public void writeAllBrandsFromNHTSA(ArrayList<String> brands, Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> set = new HashSet<String>();
        set.addAll(brands);
        editor.putStringSet("brands", set);
        editor.commit();
    }


    public ArrayList<String> getAllBrandsFromNHTSA(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = preferences.getStringSet("brands", null);
        ArrayList<String> storedBrands = new ArrayList<String>();
        if (set == null){
            storedBrands = null;
        }else{
            for (String str : set)
                storedBrands.add(str);
        }
        return storedBrands;

    }
}
