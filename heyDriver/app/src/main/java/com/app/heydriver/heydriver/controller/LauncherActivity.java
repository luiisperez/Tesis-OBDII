package com.app.heydriver.heydriver.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.model.ManageInformation;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ManageInformation storeinfo = new ManageInformation();
        User u = storeinfo.getUserInformation(getApplicationContext());
        if ((!u.get_email().equals("")) && (!u.get_firstname().equals("")) &&
                (!u.get_lastname().equals("")) && (!u.get_password().equals("")) &&
                (!u.get_username().equals(""))){
            Intent myintent = new Intent(LauncherActivity.this, HomeActivity.class);
            finish();
            startActivity(myintent);
        }else{
            Intent myintent = new Intent(LauncherActivity.this, StartUpActivity.class);
            finish();
            startActivity(myintent);
        }
    }
}
