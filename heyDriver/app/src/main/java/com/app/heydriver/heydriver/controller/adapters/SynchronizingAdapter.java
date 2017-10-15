package com.app.heydriver.heydriver.controller.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by crist on 12/10/2017.
 */

public class SynchronizingAdapter {
    List<ObdData> readings = new ArrayList<ObdData>();
    SimpleDateFormat mask = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");

    public List<ObdData> syncData(Context context) throws ParseException {

        try {
            SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
            Cursor cursor = db
                    //TODO
                    .rawQuery("SELECT Air_Intake_Temperature, Ambient_Air_Temperature, Engine_Coolant_Temperature, " +
                            "Barometric_Pressure, Fuel_Pressure, Intake_Manifold_Pressure, Engine_Load, Engine_Runtime, " +
                            "Engine_RPM, Vehicle_Speed, Mass_Air_Flow, Throttle_Position, Trouble_Codes, Fuel_Level, Fuel_type, " +
                            "Fuel_Consumption_Rate, Timing_Advance, Diagnostic_Trouble_Codes, Command_Equivalence_Ratio, " +
                            "Control_Module_Power_Supply , Fuel_Rail_Pressure, Vehicle_Identification_Number, Distance_traveled_with_MIL_on, " +
                            "Short_Term_Fuel_Trim2, Short_Term_Fuel_Trim1, Long_Term_Fuel_Trim2, Long_Term_Fuel_Trim1, " +
                            "Engine_oil_temperature, AirFuel_Ratio, Wideband_AirFuel_Ratio,time_mark, lat, lon, alt FROM HISTORICO", null);
            if (cursor.moveToFirst()) {
                do {
                    //TODO
                    ObdData reading = new ObdData();
                    reading.setAir_Intake_Temperature(cursor.getFloat(0));
                    reading.setAmbient_Air_Temperature(cursor.getFloat(1));
                    reading.setEngine_Coolant_Temperature(cursor.getFloat(2));
                    reading.setBarometric_Pressure(cursor.getFloat(3));
                    reading.setFuel_Pressure(cursor.getFloat(4));
                    reading.setIntake_Manifold_Pressure(cursor.getFloat(5));
                    reading.setEngine_Load(cursor.getFloat(6));
                    reading.setEngine_Runtime(cursor.getString(7));
                    reading.setEngine_RPM(cursor.getFloat(8));
                    reading.setVehicle_Speed(cursor.getFloat(9));
                    reading.setMass_Air_Flow(cursor.getFloat(10));
                    reading.setThrottle_Position(cursor.getFloat(11));
                    reading.setTrouble_Codes(cursor.getString(12));
                    reading.setFuel_Level(cursor.getFloat(13));
                    reading.setFuel_type(cursor.getString(14));
                    reading.setFuel_Consumption_Rate(cursor.getFloat(15));
                    reading.setTiming_Advance(cursor.getFloat(16));
                    reading.setDiagnostic_Trouble_Codes(cursor.getString(17));
                    reading.setCommand_Equivalence_Ratio(cursor.getFloat(18));
                    reading.setControl_Module_Power_Supply(cursor.getFloat(19));
                    reading.setFuel_Rail_Pressure(cursor.getFloat(20));
                    reading.setVehicle_Identification_Number(cursor.getString(21));
                    reading.setDistance_traveled_with_MIL_on(cursor.getFloat(22));
                    reading.setSTFT2(cursor.getFloat(23));
                    reading.setSTFT1(cursor.getFloat(24));
                    reading.setLTFT2(cursor.getFloat(25));
                    reading.setLTFT1(cursor.getFloat(26));
                    reading.setEngine_oil_temperature(cursor.getFloat(27));
                    reading.setAirFuel_Ratio(cursor.getFloat(28));
                    reading.setWideband_AirFuel_Ratio(cursor.getFloat(29));
                    //date
                    String datetime = cursor.getString(30);
                    reading.setTime_mark(Timestamp.valueOf(datetime));
                    //ubication
                    reading.setLat(cursor.getFloat(31));
                    reading.setLon(cursor.getFloat(32));
                    reading.setAlt(cursor.getFloat(33));
                    readings.add(reading);
                } while (cursor.moveToNext());
            }

            db.close();
            if (readings.size() >= 1) {
                return readings;
            } else {
                return null;
            }

            // aquí se debe preguntar si readings size es >=1 y si es así, tran
        } catch (Exception e) {
            Log.d(TAG, "callMethodSynchronization: Error de sincronización");
            Toast toast = Toast.makeText(context, R.string.error_bad_communication, Toast.LENGTH_LONG);
            toast.show();
            throw e;
        }
    }

}
