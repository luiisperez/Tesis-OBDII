package com.app.heydriver.heydriver.controller.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.heydriver.heydriver.common.Entities.ControladorSQLite;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.model.ManageInformation;

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

    public List<ObdData> syncData() throws ParseException {

        try {
            //homologate();
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

        } catch (Exception e) {
            Log.d(TAG, "callMethodSynchronization: Error de sincronización");
            throw e;
        }
    }

    private void homologate() {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ManageInformation info_car = new ManageInformation();
        Cursor avg_cursor = db.rawQuery("SELECT avg(Air_Intake_Temperature), avg(Engine_Coolant_Temperature), " +
                "avg(Intake_Manifold_Pressure),avg(Mass_Air_Flow), avg(Engine_Load), " +
                "avg(Engine_RPM), avg(Timing_Advance), avg(Control_Module_Power_Supply), " +
                "avg(Short_Term_Fuel_Trim2), avg(Short_Term_Fuel_Trim1), avg(Long_Term_Fuel_Trim2), avg(Long_Term_Fuel_Trim1), " +
                "avg(AirFuel_Ratio) FROM HISTORICO", null);
        ContentValues cv = new ContentValues();
        if (avg_cursor.moveToFirst()) {
            if(avg_cursor.getDouble(0)==0)
            {
                cv.put("Air_Intake_Temperature",42.5f);
            }
            if(avg_cursor.getDouble(1)==0)
            {
                cv.put("Engine_Coolant_Temperature",65f);
            }
            if(avg_cursor.getDouble(2)==0)
            {
                cv.put("Intake_Manifold_Pressure",35f);
            }
            if(avg_cursor.getDouble(3)==0)
            {
                cv.put("Mass_Air_Flow",20.5f);
            }
            if(avg_cursor.getDouble(4)==0)
            {
                cv.put("Engine_Load",50f);
            }
            if(avg_cursor.getDouble(5)==0)
            {
                cv.put("Engine_RPM",3200.5f);
            }
            if(avg_cursor.getDouble(6)==0)
            {
                cv.put("Timing_Advance",0.5f);
            }
            if(avg_cursor.getDouble(7)==0)
            {
                cv.put("Control_Module_Power_Supply",13.5f);
            }
            if(avg_cursor.getDouble(12)==0)
            {
                cv.put("AirFuel_Ratio",14.7f);
            }
            if (cv.size()>=1) {
                db.update(ControladorSQLite.DatosTabla.TABLA_HISTORICO, cv, "id", null);
            }
            db.close();
        }
    }

}
