package com.app.heydriver.heydriver.controller;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.model.ManageInformation;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button mLogInButton = (Button) findViewById(R.id.btn_su_sign_in);
        Button mSignUpButton = (Button) findViewById(R.id.btn_su_sign_up);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToLogIn();
            }
        });
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSignUp();
            }
        });
    }


    private void moveToLogIn() {
        Intent myintent = new Intent(StartUpActivity.this, LoginActivity.class);
        finish();
        startActivity(myintent);
    }


    private void moveToSignUp() {
        Intent myintent = new Intent(StartUpActivity.this, SignUpActivity.class);
        finish();
        startActivity(myintent);
    }
}
