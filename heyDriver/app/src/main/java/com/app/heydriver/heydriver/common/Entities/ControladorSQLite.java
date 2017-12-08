package com.app.heydriver.heydriver.common.Entities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by crist on 7/8/2017.
 */

public class ControladorSQLite extends SQLiteOpenHelper
{
    public static abstract class DatosTabla implements BaseColumns
    {
        public static final String TABLA_HISTORICO = "HISTORICO";
        public static final String TABLA_CAR_DTC = "CAR_DTC";
        public static final String TABLA_CAR_PREDICTION = "CAR_PREDICTION";
        public static final String TABLA_CAR_PROMEDIUM = "CAR_PROMEDIUM";

        public static final String COLUMNA_ID = "id";
        public static final String AIR_INTAKE_TEMP = "Air_Intake_Temperature";
        public static final String AMBIENT_AIR_TEMP = "Ambient_Air_Temperature";
        public static final String ENGINE_COOLANT_TEMP = "Engine_Coolant_Temperature";
        public static final String BAROMETRIC_PRESSURE = "Barometric_Pressure";
        public static final String FUEL_PRESSURE = "Fuel_Pressure";
        public static final String INTAKE_MANIFOLD_PRESSURE = "Intake_Manifold_Pressure";
        public static final String ENGINE_LOAD = "Engine_Load";
        public static final String ENGINE_RUNTIME = "Engine_Runtime";
        public static final String ENGINE_RPM = "Engine_RPM";
        public static final String SPEED = "Vehicle_Speed";
        public static final String MAF = "Mass_Air_Flow";
        public static final String THROTTLE_POS = "Throttle_Position";
        public static final String TROUBLE_CODES = "Trouble_Codes";
        public static final String FUEL_LEVEL = "Fuel_Level";
        public static final String FUEL_TYPE = "Fuel_type";
        public static final String FUEL_CONSUMPTION_RATE = "Fuel_Consumption_Rate";
        public static final String TIMING_ADVANCE = "Timing_Advance";
        public static final String DTC_NUMBER = "Diagnostic_Trouble_Codes";
        public static final String EQUIV_RATIO = "Command_Equivalence_Ratio";
        public static final String CONTROL_MODULE_VOLTAGE = "Control_Module_Power_Supply ";
        public static final String FUEL_RAIL_PRESSURE = "Fuel_Rail_Pressure";
        public static final String VIN = "Vehicle_Identification_Number";
        public static final String DISTANCE_TRAVELED_MIL_ON = "Distance_traveled_with_MIL_on";
        //long & short term fuel trim
        public static final String STFT2 = "Short_Term_Fuel_Trim2";
        public static final String STFT1 = "Short_Term_Fuel_Trim1";
        public static final String LTFT2 = "Long_Term_Fuel_Trim2";
        public static final String LTFT1 = "Long_Term_Fuel_Trim1";
        public static final String ENGINE_OIL_TEMP = "Engine_oil_temperature";
        public static final String AIR_FUEL_RATIO = "AirFuel_Ratio";
        public static final String WIDEBAND_AIR_FUEL_RATIO = "Wideband_AirFuel_Ratio";
        public static final String TIME_MARK = "time_mark";
        //GPS coordenades
        public static final String LAT = "lat";
        public static final String LON = "lon";
        public static final String ALT = "alt";

        public static final String VIN_DTC = "vin_dtc";
        public static final String CAR_NAME = "car_name";
        public static final String CAR_MODEL = "car_model";
        public static final String TOUBLE_CODE = "trouble_code_dtc";
        public static final String PREDICTION_CODE = "prediction_code";


        private static final String TEXT_TYPE = " TEXT";
        private static final String TEXT_FLOAT = " REAL";
        private static final String COMMA_SEP = ",";

        //TABLES
        private static final String CREAR_TABLA_HISTORICO =
                "CREATE TABLE " + DatosTabla.TABLA_HISTORICO + " (" +
                        DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatosTabla.AIR_INTAKE_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.AMBIENT_AIR_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_COOLANT_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.BAROMETRIC_PRESSURE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.FUEL_PRESSURE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.INTAKE_MANIFOLD_PRESSURE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_LOAD + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_RUNTIME + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_RPM + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.SPEED + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.MAF + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.THROTTLE_POS + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.TROUBLE_CODES + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.FUEL_LEVEL + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.FUEL_TYPE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.FUEL_CONSUMPTION_RATE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.TIMING_ADVANCE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.DTC_NUMBER + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.EQUIV_RATIO + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.CONTROL_MODULE_VOLTAGE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.FUEL_RAIL_PRESSURE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.VIN + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.DISTANCE_TRAVELED_MIL_ON + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.STFT2 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.STFT1 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LTFT2 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LTFT1 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_OIL_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.AIR_FUEL_RATIO + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.WIDEBAND_AIR_FUEL_RATIO + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.TIME_MARK + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LAT + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LON + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ALT + TEXT_TYPE + " )";

     private static final String CREAR_TABLA_CAR_DTC =
             "CREATE TABLE " + DatosTabla.TABLA_CAR_DTC + " (" +
                     DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                     DatosTabla.CAR_MODEL + TEXT_TYPE + COMMA_SEP +
                     DatosTabla.VIN_DTC + TEXT_TYPE + COMMA_SEP +
                     DatosTabla.TOUBLE_CODE + TEXT_TYPE + " )";

        private static final String CREAR_TABLA_CAR_PREDICTION =
                "CREATE TABLE " + DatosTabla.TABLA_CAR_PREDICTION + " (" +
                        DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatosTabla.CAR_NAME + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.CAR_MODEL + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.VIN_DTC + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.PREDICTION_CODE + TEXT_TYPE + " )";

        private static final String CREAR_TABLA_CAR_PROMEDIUM =
                "CREATE TABLE " + DatosTabla.TABLA_CAR_PROMEDIUM + " (" +
                        DatosTabla.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatosTabla.CAR_NAME + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.CAR_MODEL + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.VIN_DTC + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.AIR_INTAKE_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_COOLANT_TEMP + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.INTAKE_MANIFOLD_PRESSURE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.MAF + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_LOAD + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.ENGINE_RPM + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.TIMING_ADVANCE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.CONTROL_MODULE_VOLTAGE + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.STFT2 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.STFT1 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LTFT2 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.LTFT1 + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.AIR_FUEL_RATIO + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLA_HISTORICO;

        private static final String SQL_DELETE_ENTRIES_DTC =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLA_CAR_DTC;

        private static final String SQL_DELETE_ENTRIES_PREDICTIONS =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLA_CAR_PREDICTION;
        private static final String SQL_DELETE_ENTRIES_PROMEDIUM =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLA_CAR_PROMEDIUM;
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MiBasedeDatos.db";

    public ControladorSQLite(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.CREAR_TABLA_HISTORICO);
        db.execSQL(DatosTabla.CREAR_TABLA_CAR_DTC);
        db.execSQL(DatosTabla.CREAR_TABLA_CAR_PREDICTION);
        db.execSQL(DatosTabla.CREAR_TABLA_CAR_PROMEDIUM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES_DTC);
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES_PREDICTIONS);
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES_PROMEDIUM);
        onCreate(db);
    }
}