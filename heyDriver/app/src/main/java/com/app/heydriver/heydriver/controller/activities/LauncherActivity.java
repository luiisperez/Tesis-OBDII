package com.app.heydriver.heydriver.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.adapters.SynchronizingAdapter;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LauncherActivity extends AppCompatActivity {

    public static List<Integer> itemPositionStacks = new ArrayList<>();
    public TextView status_message;
    static String str_result = "";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        status_message = (TextView) findViewById(R.id.tv_launcher_message);
        new CountDownTimer(2000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                status_message.setText(getString(R.string.string_start_app));
            }
        }.start();
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                status_message.setText(getString(R.string.string_sync_info));
                ManageInformation storeinfo = new ManageInformation();
                User u = storeinfo.getUserInformation(getApplicationContext());
                if ((u.get_email() != null) && (u.get_firstname() != null) &&
                        (u.get_lastname() != null) && (u.get_password() != null) &&
                        (u.get_username() != null)){
                    final Intent myintent = new Intent(LauncherActivity.this, HomeActivity.class);
                    boolean connected = false;
                    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    try {
                        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                            //we are connected to a network
                            connected = true;
                            SynchronizingTask task = new SynchronizingTask();
                            try {
                                str_result = task.execute((Void) null).get();
                                status_message.setText(str_result);
                                new CountDownTimer(2000,1000){
                                    @Override
                                    public void onTick(long millisUntilFinished) {

                                    }

                                    @Override
                                    public void onFinish() {
                                        finish();
                                        startActivity(myintent);
                                    }
                                }.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            connected = false;
                            finish();
                            startActivity(myintent);
                        }
                    }
                    //si el dispositivo está registrado en otra red...
                    catch (Exception e)
                    {
                        connected = false;
                        finish();
                        startActivity(myintent);
                    }
                }else{
                    Intent myintent = new Intent(LauncherActivity.this, StartUpActivity.class);
                    finish();
                    startActivity(myintent);
                }
            }
        }.start();
    }



    private class SynchronizingTask extends AsyncTask<Void, Void, String> {
        private int response = 0;
        SynchronizingAdapter sa = new SynchronizingAdapter();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params) {
            List<ObdData> sincData = null;
            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodSynchronizationContext(sa.syncDataContext(getApplicationContext()),getApplicationContext());
                if (response == 1){
                    return getString(R.string.sincronized_data) + "...";
                }else{
                    return getString(R.string.no_data) + "...";
                }
            }
            catch (Exception e) {
                return getString(R.string.error_bad_communication) + "...";
            }
        }

        @Override
        protected void onPostExecute(final String success) {
        }
    }
}
