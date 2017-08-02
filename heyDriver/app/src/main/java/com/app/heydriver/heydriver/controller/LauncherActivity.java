package com.app.heydriver.heydriver.controller;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.provider.Telephony;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                ManageInformation storeinfo = new ManageInformation();
                User u = storeinfo.getUserInformation(getApplicationContext());
                if ((u.get_email() != null) && (u.get_firstname() != null) &&
                        (u.get_lastname() != null) && (u.get_password() != null) &&
                        (u.get_username() != null)){
                    Intent myintent = new Intent(LauncherActivity.this, HomeActivity.class);
                    finish();
                    startActivity(myintent);
                }else{
                    Intent myintent = new Intent(LauncherActivity.this, StartUpActivity.class);
                    finish();
                    startActivity(myintent);
                }
            }
        }.start();
    }
}
