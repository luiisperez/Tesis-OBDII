package com.app.heydriver.heydriver.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.heydriver.heydriver.common.Entities.User;

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
}
