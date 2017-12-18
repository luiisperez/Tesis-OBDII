package com.app.heydriver.heydriver.controller.fragments;

import android.Manifest;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.ControladorSQLite;
import com.app.heydriver.heydriver.common.Entities.ObdData;
import com.app.heydriver.heydriver.controller.activities.ConfigActivity;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.adapters.SynchronizingAdapter;
import com.app.heydriver.heydriver.model.AbstractGatewayService;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.MockObdGatewayService;
import com.app.heydriver.heydriver.model.ObdCommandJob;
import com.app.heydriver.heydriver.model.ObdConfig;
import com.app.heydriver.heydriver.model.ObdGatewayService;
import com.app.heydriver.heydriver.model.ObdProgressListener;
import com.app.heydriver.heydriver.model.ObdReading;
import com.app.heydriver.heydriver.model.ObdService;
import com.app.heydriver.heydriver.model.RestCommunication;
import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.content.Context.SENSOR_SERVICE;
import static com.app.heydriver.heydriver.controller.activities.ConfigActivity.getGpsDistanceUpdatePeriod;
import static com.app.heydriver.heydriver.controller.activities.ConfigActivity.getGpsUpdatePeriod;
import static com.github.pires.obd.enums.AvailableCommandNames.AIR_FUEL_RATIO;
import static com.github.pires.obd.enums.AvailableCommandNames.AIR_INTAKE_TEMP;
import static com.github.pires.obd.enums.AvailableCommandNames.AMBIENT_AIR_TEMP;
import static com.github.pires.obd.enums.AvailableCommandNames.BAROMETRIC_PRESSURE;
import static com.github.pires.obd.enums.AvailableCommandNames.CONTROL_MODULE_VOLTAGE;
import static com.github.pires.obd.enums.AvailableCommandNames.DISTANCE_TRAVELED_MIL_ON;
import static com.github.pires.obd.enums.AvailableCommandNames.DTC_NUMBER;
import static com.github.pires.obd.enums.AvailableCommandNames.ENGINE_COOLANT_TEMP;
import static com.github.pires.obd.enums.AvailableCommandNames.ENGINE_LOAD;
import static com.github.pires.obd.enums.AvailableCommandNames.ENGINE_OIL_TEMP;
import static com.github.pires.obd.enums.AvailableCommandNames.ENGINE_RPM;
import static com.github.pires.obd.enums.AvailableCommandNames.ENGINE_RUNTIME;
import static com.github.pires.obd.enums.AvailableCommandNames.EQUIV_RATIO;
import static com.github.pires.obd.enums.AvailableCommandNames.FUEL_CONSUMPTION_RATE;
import static com.github.pires.obd.enums.AvailableCommandNames.FUEL_LEVEL;
import static com.github.pires.obd.enums.AvailableCommandNames.FUEL_PRESSURE;
import static com.github.pires.obd.enums.AvailableCommandNames.FUEL_RAIL_PRESSURE;
import static com.github.pires.obd.enums.AvailableCommandNames.FUEL_TYPE;
import static com.github.pires.obd.enums.AvailableCommandNames.INTAKE_MANIFOLD_PRESSURE;
import static com.github.pires.obd.enums.AvailableCommandNames.MAF;
import static com.github.pires.obd.enums.AvailableCommandNames.SPEED;
import static com.github.pires.obd.enums.AvailableCommandNames.THROTTLE_POS;
import static com.github.pires.obd.enums.AvailableCommandNames.TIMING_ADVANCE;
import static com.github.pires.obd.enums.AvailableCommandNames.TROUBLE_CODES;
import static com.github.pires.obd.enums.AvailableCommandNames.VIN;
import static com.github.pires.obd.enums.AvailableCommandNames.WIDEBAND_AIR_FUEL_RATIO;

public class ObdReaderFragment extends Fragment
        implements ObdProgressListener, LocationListener, GpsStatus.Listener {



    public static int sync = 0;

    //NOTIFICACIONES EN ANDROID
    public void showFailureNotification(String errorcode, String message) {
        int icono = R.drawable.ic_notification;
        int largeIcono = R.mipmap.icon_driver;

        int apiVersion = android.os.Build.VERSION.SDK_INT;
        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr =(NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(getActivity(), HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, i, 0);
        mBuilder =new NotificationCompat.Builder(getActivity())
                .setContentIntent(pendingIntent)
                .setSmallIcon(icono)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), largeIcono))
                .setContentTitle(getString(R.string.failure_detected_title))
                .setContentText(getString(R.string.failure_detected_message_pt1) + "\"" + errorcode + "\" \n")
                .setSubText(message)
                .setVibrate(new long[] {100, 250, 100, 500})
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.failure_detected_message_pt1) + " \"" + errorcode + "\" \n" +
                                getString(R.string.failure_detected_message_pt2) + " \"" + message + "\""))
                .setAutoCancel(true);

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        mNotifyMgr.notify(m, mBuilder.build());
    }

    private static final String TAG = ObdReaderFragment.class.getName();
    private static final int NO_BLUETOOTH_ID = 0;
    private static final int BLUETOOTH_DISABLED = 1;
    private static final int START_LIVE_DATA = 2;
    private static final int STOP_LIVE_DATA = 3;
    private static final int SETTINGS = 4;
    private static final int GET_DTC = 5;
    private static final int TABLE_ROW_MARGIN = 7;
    private static final int NO_ORIENTATION_SENSOR = 8;
    private static final int NO_GPS_SUPPORT = 9;
    private static final int REQUEST_ENABLE_BT = 1234;
    private static boolean bluetoothDefaultIsEnable = false;
    private Button b_start_data;
    private Button b_stop_data;

    List<ObdData> events = new ArrayList<ObdData>();

    public Map<String, String> commandResult = new HashMap<String, String>();
    boolean mGpsIsStarted = false;
    private LocationManager mLocService;
    private LocationProvider mLocProvider;
    private Location mLastLocation;

    private TextView compass;
    private final SensorEventListener orientListener = new SensorEventListener() {

        // Orientation Sensor
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            String dir = "";
            if (x >= 337.5 || x < 22.5) {
                dir = "N";
            } else if (x >= 22.5 && x < 67.5) {
                dir = "NE";
            } else if (x >= 67.5 && x < 112.5) {
                dir = "E";
            } else if (x >= 112.5 && x < 157.5) {
                dir = "SE";
            } else if (x >= 157.5 && x < 202.5) {
                dir = "S";
            } else if (x >= 202.5 && x < 247.5) {
                dir = "SW";
            } else if (x >= 247.5 && x < 292.5) {
                dir = "W";
            } else if (x >= 292.5 && x < 337.5) {
                dir = "NW";
            }
            updateTextView(compass, dir);
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // do nothing
        }
    };

    // UI Elements
    private TextView btStatusTextView;
    private TextView obdStatusTextView;
    private TextView gpsStatusTextView;
    private LinearLayout vv;
    private TableLayout tl;

    // Sensors Elements
    private SensorManager sensorManager;
    private PowerManager powerManager;
    private SharedPreferences prefs;
    private boolean isServiceBound;
    private AbstractGatewayService service;

    // Sensors Thread
    private final Runnable mQueueCommands = new Runnable() {

        public void run() {
            if (service != null && service.isRunning() && service.queueEmpty()) {
                queueCommands();
                double lat = 0;
                double lon = 0;
                double alt = 0;
                final int posLen = 7;
                if (mGpsIsStarted && mLastLocation != null) {
                    lat = mLastLocation.getLatitude();
                    lon = mLastLocation.getLongitude();
                    alt = mLastLocation.getAltitude();

                    StringBuilder sb = new StringBuilder();
                    sb.append("Lat: ");
                    sb.append(String.valueOf(mLastLocation.getLatitude()).substring(0, posLen));
                    sb.append(" Lon: ");
                    sb.append(String.valueOf(mLastLocation.getLongitude()).substring(0, posLen));
                    sb.append(" Alt: ");
                    sb.append(String.valueOf(mLastLocation.getAltitude()));
                    gpsStatusTextView.setText(sb.toString());
                }
                if (prefs.getBoolean(ConfigActivity.UPLOAD_DATA_KEY, false)) {
                    // Upload the current reading by http
                    final String vin = prefs.getString(ConfigActivity.VEHICLE_ID_KEY, "UNDEFINED_VIN");
                    Map<String, String> temp = new HashMap<String, String>();
                    temp.putAll(commandResult);
                    ObdReading reading = new ObdReading(lat, lon, alt, System.currentTimeMillis(), vin, temp);
                    new UploadAsyncTask().execute(reading);

                } else if (prefs.getBoolean(ConfigActivity.ENABLE_FULL_LOGGING_KEY, false)) {
                    final String vin = prefs.getString(ConfigActivity.VEHICLE_ID_KEY, "UNDEFINED_VIN");
                    Map<String, String> temp = new HashMap<String, String>();
                    temp.putAll(commandResult);
                    //Objeto para transferir
                    ObdReading reading = new ObdReading(lat, lon, alt, System.currentTimeMillis(), vin, temp);
                    if (reading != null) {
                        Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_LONG).show();
                    }
                    ;
                }
                commandResult.clear();
            }
            // run again in period defined in preferences
            new Handler().postDelayed(mQueueCommands, ConfigActivity.getObdUpdatePeriod(prefs));
        }
    };

    private Sensor orientSensor = null;
    private PowerManager.WakeLock wakeLock = null;
    private boolean preRequisites = true;
    private ServiceConnection serviceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder binder) {
            Log.d(TAG, className.toString() + " service is bound");
            isServiceBound = true;
            service = ((AbstractGatewayService.AbstractGatewayServiceBinder) binder).getService();
            service.setContext(getActivity());
            Log.d(TAG, "Starting live data");
            try {
                service.startService();
                if (preRequisites)
                    btStatusTextView.setText(getString(R.string.status_bluetooth_connected));
            } catch (IOException ioe) {
                Log.e(TAG, "Failure Starting live data");
                btStatusTextView.setText(getString(R.string.status_bluetooth_error_connecting));
                doUnbindService();
            }
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        // This method is *only* called when the connection to the service is lost unexpectedly
        // and *not* when the client unbinds (http://developer.android.com/guide/components/bound-services.html)
        // So the isServiceBound attribute should also be set to false when we unbind from the service.
        @Override
        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, className.toString() + " service is unbound");
            isServiceBound = false;
        }
    };
    private Toolbar supportActionBar;

    // Devuelve un String con el nombre del comando detectado
    public static String LookUpCommand(String txt) {
        for (AvailableCommandNames item : AvailableCommandNames.values()) {
            if (item.getValue().equals(txt)) return item.name();
        }
        return txt;
    }

    // Actualiza un TextView con el String Recibido
    public void updateTextView(final TextView view, final String txt) {
        new Handler().post(new Runnable() {
            public void run() {
                view.setText(txt);
            }
        });
    }

    // Actualizar Estado
    @Override
    public void stateUpdate(final ObdCommandJob job) {
        final String cmdName = job.getCommand().getName();
        String cmdResult = "";
        final String cmdID = LookUpCommand(cmdName);

        if (job.getState().equals(ObdCommandJob.ObdCommandJobState.EXECUTION_ERROR)) {
            cmdResult = job.getCommand().getResult();
            if (cmdResult != null && isServiceBound) {
                obdStatusTextView.setText(cmdResult.toLowerCase());
            }
        } else if (job.getState().equals(ObdCommandJob.ObdCommandJobState.BROKEN_PIPE)) {
            if (isServiceBound)
                stopLiveData();
        } else if (job.getState().equals(ObdCommandJob.ObdCommandJobState.NOT_SUPPORTED)) {
            cmdResult = getString(R.string.status_obd_no_support);
        } else {
            cmdResult = job.getCommand().getFormattedResult();
            if (isServiceBound)
                obdStatusTextView.setText(getString(R.string.status_obd_data));
        }
        // si ya existe el TV, lo actualiza
        if (vv.findViewWithTag(cmdID) != null) {
            TextView existingTV = (TextView) vv.findViewWithTag(cmdID);
            existingTV.setText(cmdResult);
        }
        //si no existe el TV, lo crea
        else addTableRow(cmdID, cmdName, cmdResult);
        commandResult.put(cmdID, cmdResult);
        //si ya tengo todos los valores, los almaceno
        if (commandResult.size() == 30)
            updateBdStatistic(cmdID, cmdResult);
    }

    public float stringToFloat(String string) {
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 48 &&
                    string.charAt(i) <= 57 ||
                    string.charAt(i) == '.' ||
                    string.charAt(i) == ',' ||
                    string.charAt(i) == '-')
            {
                if (string.charAt(i) == ',')
                {
                    newString += '.';
                }
                else
                    newString += string.charAt(i);
            } else {
                break;
            }
        }

        return Float.parseFloat(newString);
    }

    Map<String, String> getDict(int keyId, int valId) {
        String[] keys = getResources().getStringArray(keyId);
        String[] vals = getResources().getStringArray(valId);

        Map<String, String> dict = new HashMap<String, String>();
        for (int i = 0, l = keys.length; i < l; i++) {
            dict.put(keys[i], vals[i]);
        }

        return dict;
    }

    private String getTroubleMessage(String code) {
        Map<String, String> dtcVals = getDict(R.array.dtc_keys, R.array.dtc_values);
        String dtcMessage = getString(R.string.unknown_obd_code);
        try{
            if (!dtcVals.get(code).equals("")) {
                dtcMessage = dtcVals.get(code);
            }
            return dtcMessage;
        }catch (Exception ex) {
            return dtcMessage;
        }
    }

    // update SQLite Statistics
    public void updateBdStatistic( String sensorName, String value) {
        final ControladorSQLite controladorSQLite = new ControladorSQLite(getActivity().getApplicationContext());
        SQLiteDatabase db = controladorSQLite.getWritableDatabase();
        long mils = System.currentTimeMillis();
        SimpleDateFormat mask = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");
        double lat = 0;
        double lon = 0;
        double alt = 0;
        final int posLen = 7;
        if (mGpsIsStarted && mLastLocation != null) {
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
            alt = mLastLocation.getAltitude();

        }

/* Rutina para colectar la dirección de cada lectura
        String StreetName=null;
        Geocoder gcd = new Geocoder(getActivity(), Locale.getDefault());
        List<Address>  addresses;
        try {
            addresses = gcd.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 10);
            if (addresses.size() > 0)
                StreetName = addresses.get(0).getThoroughfare();
            String s = "My Currrent Street is:"+StreetName;
            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
        }catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }
*/


        //Colección para almacenar en BD
        ContentValues valores = new ContentValues();
        ObdData dataSensor = new ObdData();
        Iterator et = commandResult.entrySet().iterator();
        while (et.hasNext()) {
            Map.Entry e = (Map.Entry) et.next();
            if (e.getValue().equals("NA") ||e.getValue().equals("NODATA") ||e.getValue().equals("......UNABLETOCONNECT")) {
                e.setValue("0.0");
            }
        }
        Iterator it = commandResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            if (e.getKey().equals(EQUIV_RATIO.toString()))
            {
                dataSensor.setCommand_Equivalence_Ratio(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( AMBIENT_AIR_TEMP.toString()))
            {
                dataSensor.setAmbient_Air_Temperature(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( DISTANCE_TRAVELED_MIL_ON.toString()))
            {
                dataSensor.setDistance_traveled_with_MIL_on(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( SPEED.toString()))
            {
                dataSensor.setVehicle_Speed(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals("Short Term Fuel Trim Bank 2"))
            {
                dataSensor.setSTFT2(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( ENGINE_OIL_TEMP.toString()))
            {
                dataSensor.setEngine_oil_temperature(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( ENGINE_LOAD.toString()))
            {
                dataSensor.setEngine_Load(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( ENGINE_RUNTIME.toString()))
            {
                dataSensor.setEngine_Runtime((String) e.getValue());
            }
            if (e.getKey().equals( VIN.toString()))
            {
                ManageInformation info = new ManageInformation();
                dataSensor.setVehicle_Identification_Number(info.getCarInformation(getActivity()).get_serial());
            }
            if (e.getKey().equals(TROUBLE_CODES.toString())) {
                dataSensor.setTrouble_Codes((String) e.getValue());
                ManageInformation info_car = new ManageInformation();
                if ( !((String) e.getValue()).equals(""))
                {
                    String[] split_codes = e.getValue().toString().split("\n");
                    //String[] split_codes = "P0142\nC0001\nP0171".toString().split("\n");
                    for (String n : split_codes) {
                        if (!findDTC(info_car.getCarInformation(getActivity()).get_serial(),n))
                        {
                            saveDTC(info_car.getCarInformation(getActivity()).get_serial(),
                                    info_car.getCarInformation(getActivity()).get_brand()+" "
                                            +info_car.getCarInformation(getActivity()).get_model(),n);
                            showFailureNotification(n, getTroubleMessage(n));
                        }
                    }
                }
            }
            if (e.getKey().equals( TIMING_ADVANCE.toString()))
            {
                float ta = stringToFloat((String) e.getValue());
                dataSensor.setTiming_Advance(((ta*255.0f)/200.0f)-64.0f);

            }
            if (e.getKey().equals( CONTROL_MODULE_VOLTAGE.toString()))
            {
                dataSensor.setControl_Module_Power_Supply(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals("Short Term Fuel Trim Bank 1"))
            {
                dataSensor.setSTFT1(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( MAF.toString()))
            {
                dataSensor.setMass_Air_Flow(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( FUEL_PRESSURE.toString()))
            {
                dataSensor.setFuel_Pressure(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( ENGINE_RPM.toString()))
            {
                dataSensor.setEngine_RPM(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( THROTTLE_POS.toString()))
            {
                dataSensor.setThrottle_Position(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( FUEL_TYPE.toString()))
            {
                dataSensor.setFuel_type((String) e.getValue());
            }
            if (e.getKey().equals("Long Term Fuel Trim Bank 2"))
            {
                dataSensor.setLTFT2(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( FUEL_CONSUMPTION_RATE.toString()))
            {
                dataSensor.setFuel_Consumption_Rate(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( INTAKE_MANIFOLD_PRESSURE.toString()))
            {
                dataSensor.setIntake_Manifold_Pressure(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( DTC_NUMBER.toString()))
            {
                dataSensor.setDiagnostic_Trouble_Codes((String) e.getValue());

            }
            //AFR
            if (e.getKey().equals( WIDEBAND_AIR_FUEL_RATIO.toString()))
            {
                dataSensor.setWideband_AirFuel_Ratio(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( ENGINE_COOLANT_TEMP.toString()))
            {
                dataSensor.setEngine_Coolant_Temperature(stringToFloat((String) e.getValue()));
            }
            //AFR
            if (e.getKey().equals( AIR_FUEL_RATIO.toString()))
            {
                dataSensor.setAirFuel_Ratio(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals("Long Term Fuel Trim Bank 1"))
            {
                dataSensor.setLTFT1(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( BAROMETRIC_PRESSURE.toString()))
            {
                dataSensor.setBarometric_Pressure(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( FUEL_LEVEL.toString()))
            {
                dataSensor.setFuel_Level(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( AIR_INTAKE_TEMP.toString()))
            {
                dataSensor.setAir_Intake_Temperature(stringToFloat((String) e.getValue()));
            }
            if (e.getKey().equals( FUEL_RAIL_PRESSURE.toString()))
            {
                dataSensor.setFuel_Rail_Pressure(stringToFloat((String) e.getValue()));
            }
            //end_while
        }
        Timestamp time_mark = new Timestamp(mils);

        //Date date = new Date(mils);
        dataSensor.setTime_mark(time_mark);
        dataSensor.setLat(lat);
        dataSensor.setLon(lon);
        dataSensor.setAlt(alt);
        events.add(dataSensor);

        valores.put(ControladorSQLite.DatosTabla.AIR_INTAKE_TEMP,String.valueOf(dataSensor.getAir_Intake_Temperature()));
        valores.put(ControladorSQLite.DatosTabla.AMBIENT_AIR_TEMP,String.valueOf(dataSensor.getAmbient_Air_Temperature()));
        valores.put(ControladorSQLite.DatosTabla.ENGINE_COOLANT_TEMP,String.valueOf(dataSensor.getEngine_Coolant_Temperature()));
        valores.put(ControladorSQLite.DatosTabla.BAROMETRIC_PRESSURE,String.valueOf(dataSensor.getBarometric_Pressure()));
        valores.put(ControladorSQLite.DatosTabla.FUEL_PRESSURE,String.valueOf(dataSensor.getFuel_Pressure()));
        valores.put(ControladorSQLite.DatosTabla.INTAKE_MANIFOLD_PRESSURE,String.valueOf(dataSensor.getIntake_Manifold_Pressure()));
        valores.put(ControladorSQLite.DatosTabla.ENGINE_LOAD,String.valueOf(dataSensor.getEngine_Load()));
        valores.put(ControladorSQLite.DatosTabla.ENGINE_RUNTIME,String.valueOf(dataSensor.getEngine_Runtime()));
        valores.put(ControladorSQLite.DatosTabla.ENGINE_RPM,String.valueOf(dataSensor.getEngine_RPM()));
        valores.put(ControladorSQLite.DatosTabla.SPEED,String.valueOf(dataSensor.getVehicle_Speed()));
        valores.put(ControladorSQLite.DatosTabla.MAF,String.valueOf(dataSensor.getMass_Air_Flow()));
        valores.put(ControladorSQLite.DatosTabla.THROTTLE_POS,String.valueOf(dataSensor.getThrottle_Position()));
        valores.put(ControladorSQLite.DatosTabla.TROUBLE_CODES,String.valueOf(dataSensor.getTrouble_Codes()));
        valores.put(ControladorSQLite.DatosTabla.FUEL_LEVEL,String.valueOf(dataSensor.getFuel_Level()));
        valores.put(ControladorSQLite.DatosTabla.FUEL_TYPE,String.valueOf(dataSensor.getFuel_type()));
        valores.put(ControladorSQLite.DatosTabla.FUEL_CONSUMPTION_RATE,String.valueOf(dataSensor.getFuel_Consumption_Rate()));
        valores.put(ControladorSQLite.DatosTabla.TIMING_ADVANCE,String.valueOf(dataSensor.getTiming_Advance()));
        valores.put(ControladorSQLite.DatosTabla.DTC_NUMBER,String.valueOf(dataSensor.getDiagnostic_Trouble_Codes()));
        valores.put(ControladorSQLite.DatosTabla.EQUIV_RATIO,String.valueOf(dataSensor.getCommand_Equivalence_Ratio()));
        valores.put(ControladorSQLite.DatosTabla.CONTROL_MODULE_VOLTAGE,String.valueOf(dataSensor.getControl_Module_Power_Supply()));
        valores.put(ControladorSQLite.DatosTabla.FUEL_RAIL_PRESSURE,String.valueOf(dataSensor.getFuel_Rail_Pressure()));
        valores.put(ControladorSQLite.DatosTabla.VIN,String.valueOf(dataSensor.getVehicle_Identification_Number()));
        valores.put(ControladorSQLite.DatosTabla.DISTANCE_TRAVELED_MIL_ON,String.valueOf(dataSensor.getDistance_traveled_with_MIL_on()));
        valores.put(ControladorSQLite.DatosTabla.STFT2,String.valueOf(dataSensor.getSTFT2()));
        valores.put(ControladorSQLite.DatosTabla.STFT1,String.valueOf(dataSensor.getSTFT1()));
        valores.put(ControladorSQLite.DatosTabla.LTFT2,String.valueOf(dataSensor.getLTFT2()));
        valores.put(ControladorSQLite.DatosTabla.LTFT1,String.valueOf(dataSensor.getLTFT1()));
        valores.put(ControladorSQLite.DatosTabla.ENGINE_OIL_TEMP,String.valueOf(dataSensor.getEngine_oil_temperature()));
        valores.put(ControladorSQLite.DatosTabla.AIR_FUEL_RATIO,String.valueOf(dataSensor.getAirFuel_Ratio()));
        valores.put(ControladorSQLite.DatosTabla.WIDEBAND_AIR_FUEL_RATIO,String.valueOf(dataSensor.getWideband_AirFuel_Ratio()));
        valores.put(ControladorSQLite.DatosTabla.TIME_MARK,String.valueOf(time_mark.toString()));
        valores.put(ControladorSQLite.DatosTabla.LAT,String.valueOf(dataSensor.getLat()));
        valores.put(ControladorSQLite.DatosTabla.LON,String.valueOf(dataSensor.getLon()));
        valores.put(ControladorSQLite.DatosTabla.ALT,String.valueOf(dataSensor.getAlt()));
        try
        {
            long IdGuardado = db.insert(ControladorSQLite.DatosTabla.TABLA_HISTORICO, "id", valores);
            db.close();
        }
        catch (Exception e){
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
        }

    }

    // Check permission for the GPS
    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = getActivity().checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    // control request permission
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    gpsStatusTextView.setText(getString(R.string.status_gps_ready));

                } else {
                    gpsStatusTextView.setText(getString(R.string.status_bluetooth_disabled));
                }
                return;
            }
        }
    }

    // Start GPS services & Controller permissions
    private boolean gpsInit() {
        mLocService = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if(!checkLocationPermission())
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1222);
        else
        if (mLocService != null ) {
            mLocProvider = mLocService.getProvider(LocationManager.NETWORK_PROVIDER);
            if (mLocProvider != null) {
                mLocService.addGpsStatusListener(this);
                if (mLocService.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    gpsStatusTextView.setText(getString(R.string.status_gps_ready));
                    return true;
                }
                else
                {
                    mLocProvider = mLocService.getProvider(LocationManager.GPS_PROVIDER);
                    if (mLocProvider != null) {
                        mLocService.addGpsStatusListener(this);
                        if (mLocService.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            gpsStatusTextView.setText(getString(R.string.status_gps_ready));
                            return true;
                        }
                    }

                }
            }
        }
        gpsStatusTextView.setText(getString(R.string.status_gps_no_support));
        Toast toast = Toast.makeText(getActivity(), "Sorry, your device doesn\\'t support or has disabled GPS", Toast.LENGTH_SHORT);
        toast.show();
        return false;
    }

    // Ask if an Trouble Code is in SQLite
    public boolean findDTC(String vin, String troble_code) {
        try {
            SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
            Cursor cursor = db.rawQuery(getString(R.string.select_trouble_from_CAR_DTC), new String[] {troble_code,vin});
            cursor.moveToFirst();
            if (cursor.getCount()>0)  {
                return true;
            }
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    // Save an Trouble Code in SQLite
    public boolean saveDTC(String vin, String car_model, String troble_code) {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ControladorSQLite.DatosTabla.VIN_DTC,String.valueOf(vin));
        valores.put(ControladorSQLite.DatosTabla.CAR_MODEL,String.valueOf(car_model));
        valores.put(ControladorSQLite.DatosTabla.TOUBLE_CODE,String.valueOf(troble_code));
        try
        {
            long IdGuardado = db.insert(ControladorSQLite.DatosTabla.TABLA_CAR_DTC, "id", valores);
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    // Reset all Trouble Codes
    public boolean resetDTC(String vin) {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        try
        {
            db.execSQL("delete from "+ ControladorSQLite.DatosTabla.TABLA_CAR_DTC + " where "+ ControladorSQLite.DatosTabla.VIN_DTC+"='"+vin+"'");
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_obd_reader, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_fragment_obd_reader));

        //Starting mobile sensors and power manager
        powerManager = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        prefs = getActivity().getPreferences(Context.MODE_PRIVATE);

        //Starting UI elements
        compass = (TextView) view.findViewById(R.id.compass_text);
        btStatusTextView = (TextView) view.findViewById(R.id.BT_STATUS);
        obdStatusTextView = (TextView) view.findViewById(R.id.OBD_STATUS);
        gpsStatusTextView = (TextView) view.findViewById(R.id.GPS_POS);
        vv = (LinearLayout) view.findViewById(R.id.vehicle_view);
        tl = (TableLayout) view.findViewById(R.id.data_table);
        b_start_data = (Button) view.findViewById(R.id.b_start_data);
        b_stop_data = (Button) view.findViewById(R.id.b_stop_data);

        final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null)
            bluetoothDefaultIsEnable = btAdapter.isEnabled();

        // get Orientation sensor
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (sensors.size() > 0)
            orientSensor = sensors.get(0);
        else {
            Toast toast = Toast.makeText(getActivity(), "No Orientation Sensor", Toast.LENGTH_SHORT);
            toast.show();
        }
        obdStatusTextView.setText(getString(R.string.status_obd_disconnected));
        b_start_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sync = 1;
                startLiveData();
            }
        });
        b_stop_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sync = 0;
                stopLiveData();
                gpsStatusTextView.setText(getString(R.string.status_gps_stopped));
                Toast toast = Toast.makeText(getActivity(), R.string.menu_stop_live_data, Toast.LENGTH_LONG);
                toast.show();
                homologate();
                promediate();
                boolean connected = false;
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

                    if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        SynchronizingTask task = new SynchronizingTask();
                        Toast toast1 = Toast.makeText(getActivity(), R.string.sincronized_data, Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        Toast toast2 = Toast.makeText(getActivity(), R.string.no_internet_error, Toast.LENGTH_LONG);
                        toast2.show();
                    }
                }catch (Exception ex){
                    Toast toast2 = Toast.makeText(getActivity(), R.string.no_internet_error, Toast.LENGTH_LONG);
                    toast2.show();
                }
            }
        });
        return view;
    }
    private void homologate() {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ManageInformation info_car = new ManageInformation();
        Cursor avg_cursor = db.rawQuery("SELECT avg(Air_Intake_Temperature), avg(Engine_Coolant_Temperature), " +
                "avg(Intake_Manifold_Pressure),avg(Mass_Air_Flow), avg(Engine_Load), " +
                "avg(Engine_RPM), avg(Timing_Advance), avg(Control_Module_Power_Supply), " +
                "avg(Short_Term_Fuel_Trim2), avg(Short_Term_Fuel_Trim1), avg(Long_Term_Fuel_Trim2), avg(Long_Term_Fuel_Trim1), " +
                "avg(AirFuel_Ratio) FROM HISTORICO WHERE Vehicle_Identification_Number='"+info_car.getCarInformation(getActivity()).get_serial()+"'", null);
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
                db.update(ControladorSQLite.DatosTabla.TABLA_HISTORICO, cv, "Vehicle_Identification_Number='"+info_car.getCarInformation(getActivity()).get_serial()+"'", null);
            }
            db.close();
        }
    }

    private void promediate() {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ManageInformation info_car = new ManageInformation();
        Cursor avg_cursor = db.rawQuery("SELECT avg(Air_Intake_Temperature), avg(Engine_Coolant_Temperature), " +
                "avg(Intake_Manifold_Pressure),avg(Mass_Air_Flow), avg(Engine_Load), " +
                "avg(Engine_RPM), avg(Timing_Advance), avg(Control_Module_Power_Supply), " +
                "avg(Short_Term_Fuel_Trim2), avg(Short_Term_Fuel_Trim1), avg(Long_Term_Fuel_Trim2), avg(Long_Term_Fuel_Trim1), " +
                "avg(AirFuel_Ratio) FROM HISTORICO WHERE Vehicle_Identification_Number='"+info_car.getCarInformation(getActivity()).get_serial()+"'", null);
        ContentValues cv = new ContentValues();
        if (avg_cursor.moveToFirst()) {
            cv.put(ControladorSQLite.DatosTabla.CAR_NAME,info_car.getCarInformation(getActivity()).get_brand());
            cv.put(ControladorSQLite.DatosTabla.CAR_MODEL,info_car.getCarInformation(getActivity()).get_model());
            cv.put(ControladorSQLite.DatosTabla.VIN_DTC,info_car.getCarInformation(getActivity()).get_serial());
            cv.put("Air_Intake_Temperature",avg_cursor.getDouble(0));
            cv.put("Engine_Coolant_Temperature",avg_cursor.getDouble(1));
            cv.put("Intake_Manifold_Pressure",avg_cursor.getDouble(2));
            cv.put("Mass_Air_Flow",avg_cursor.getDouble(3));
            cv.put("Engine_Load",avg_cursor.getDouble(4));
            cv.put("Engine_RPM",avg_cursor.getDouble(5));
            cv.put("Timing_Advance",avg_cursor.getDouble(6));
            cv.put("Control_Module_Power_Supply",avg_cursor.getDouble(7));
            cv.put("Short_Term_Fuel_Trim2",avg_cursor.getDouble(8));
            cv.put("Short_Term_Fuel_Trim1",avg_cursor.getDouble(9));
            cv.put("Long_Term_Fuel_Trim2",avg_cursor.getDouble(10));
            cv.put("Long_Term_Fuel_Trim1",avg_cursor.getDouble(11));
            cv.put("AirFuel_Ratio",avg_cursor.getDouble(12));
            try
            {
                Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PROMEDIUM WHERE vin_dtc='"+info_car.getCarInformation(getActivity()).get_serial()+"'", null);
                if (promedium_cursor.moveToFirst() ) {
                    if(promedium_cursor.getInt(0)>=1)
                    {
                        long IdUpdate = db.update(ControladorSQLite.DatosTabla.TABLA_CAR_PROMEDIUM, cv, "vin_dtc='" + info_car.getCarInformation(getActivity()).get_serial() + "'", null);
                    }
                    else
                    {
                        long IdInsert = db.insert(ControladorSQLite.DatosTabla.TABLA_CAR_PROMEDIUM, "id", cv);
                    }
                }
                db.close();
            }
            catch (Exception e){
                Log.d(TAG, e.getStackTrace().toString());
                db.close();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Entered onStart...");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mLocService != null) {
            mLocService.removeGpsStatusListener(this);
            mLocService.removeUpdates(this);
        }

        releaseWakeLockIfHeld();
        if (isServiceBound) {
            doUnbindService();
        }

        final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null && btAdapter.isEnabled() && !bluetoothDefaultIsEnable)
            btAdapter.disable();
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Pausing..");
        releaseWakeLockIfHeld();
    }

    /**
     * If lock is held, release. Lock will be held when the service is running.
     */
    private void releaseWakeLockIfHeld() {
        if (wakeLock.isHeld())
            wakeLock.release();
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resuming..");
        sensorManager.registerListener(orientListener, orientSensor,
                SensorManager.SENSOR_DELAY_UI);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
                "ObdReader");

        // get Bluetooth device
        final BluetoothAdapter btAdapter = BluetoothAdapter
                .getDefaultAdapter();

        preRequisites = btAdapter != null && btAdapter.isEnabled();
        if (!preRequisites && prefs.getBoolean(ConfigActivity.ENABLE_BT_KEY, false)) {
            preRequisites = btAdapter != null && btAdapter.enable();
        }
        gpsInit();

        if (!preRequisites) {
            btStatusTextView.setText(getString(R.string.status_bluetooth_disabled));
        } else {
            btStatusTextView.setText(getString(R.string.status_bluetooth_ok));
        }
    }

    private void startLiveData() {
        ManageInformation info_car = new ManageInformation();
        Log.d(TAG, "Starting live data..");
        tl.removeAllViews(); //start fresh
        resetDTC(info_car.getCarInformation(getActivity()).get_serial());
        doBindService();

        // start command execution
        new Handler().post(mQueueCommands);

        try {
            gpsStart();
            gpsInit();
        }
        catch (Exception e) {
            gpsStatusTextView.setText(getString(R.string.status_gps_stopped));
        }
        // screen won't turn off until wakeLock.release()
        wakeLock.acquire();
    }

    private void stopLiveData() {
        Log.d(TAG, "Stopping live data..");
        tl.removeAllViews();
        doUnbindService();
        releaseWakeLockIfHeld();
    }

    //Agrega una fila a la lista de parámetros VER
    private void addTableRow(String id, String key, String val) {

        if (!key.startsWith("Vehicle Identification") &&
                !key.startsWith("Echo Off") &&
                !key.startsWith("Line Feed") &&
                !key.startsWith("Timeout") &&
                !key.startsWith("Select Protocol AUTO") &&
                !key.startsWith("Fuel Type") &&
                !key.startsWith("Fuel Consumption Rate") &&
                !key.startsWith("Engine oil temperature") &&
                !key.startsWith("Diagnostic Trouble Codes") &&
                !val.startsWith("NA"))
        {
            TableRow tr = new TableRow(getActivity());
            // Recordar que eran Wrap_Content
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.setMargins(TABLE_ROW_MARGIN, TABLE_ROW_MARGIN, TABLE_ROW_MARGIN,
                    TABLE_ROW_MARGIN);
            tr.setLayoutParams(params);

            TextView name = new TextView(getActivity());
            name.setGravity(Gravity.LEFT);
            name.setText(key + ": ");
            name.setTextSize(15);
            name.setTextAppearance(getActivity(),R.style.TextAppearance_FontPath);

            //Show
            TextView value = new TextView(getActivity());
            value.setGravity(Gravity.LEFT);
            value.setText(val); // valor
            value.setTag(id); //nombre
            value.setTextSize(15);

            tr.addView(name);
            tr.addView(value);
            tr.setPadding(20,5,1,5);
            tl.addView(tr, params);
        }
    }

    private void queueCommands() {
        if (isServiceBound) {
            for (ObdCommand Command : ObdConfig.getCommands()) {
                if (prefs.getBoolean(Command.getName(), true))
                    service.queueJob(new ObdCommandJob(Command));
            }
        }
    }

    private void doBindService() {
        if (!isServiceBound) {
            Log.d(TAG, "Binding OBD service..");
            if (preRequisites) {
                btStatusTextView.setText(getString(R.string.status_bluetooth_connecting));
                ObdGatewayService.obdFragment = this;
                Intent serviceIntent = new Intent(getActivity(), ObdGatewayService.class);
                getActivity().bindService(serviceIntent, serviceConn, Context.BIND_AUTO_CREATE);
            } else {
                btStatusTextView.setText(getString(R.string.status_bluetooth_disabled));
                MockObdGatewayService.obdFragment = this;
                Intent serviceIntent = new Intent(getActivity(), MockObdGatewayService.class);
                getActivity().bindService(serviceIntent, serviceConn, Context.BIND_AUTO_CREATE);
            }
        }
    }

    private void doUnbindService() {
        if (isServiceBound) {
            if (service.isRunning()) {
                service.stopService();
                if (preRequisites)
                    btStatusTextView.setText(getString(R.string.status_bluetooth_ok));
            }
            Log.d(TAG, "Unbinding OBD service..");
            getActivity().unbindService(serviceConn);
            isServiceBound = false;
            obdStatusTextView.setText(getString(R.string.status_obd_disconnected));
        }
    }

    @Override
    public void onGpsStatusChanged(int event) {
        switch (event) {
            case GpsStatus.GPS_EVENT_STARTED:
                gpsStatusTextView.setText(getString(R.string.status_gps_started));
                break;
            case GpsStatus.GPS_EVENT_STOPPED:
                gpsStatusTextView.setText(getString(R.string.status_gps_stopped));
                break;
            case GpsStatus.GPS_EVENT_FIRST_FIX:
                gpsStatusTextView.setText(getString(R.string.status_gps_fix));
                break;
            case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private synchronized void gpsStart() {
        if (!mGpsIsStarted && mLocProvider != null && mLocService != null && (mLocService.isProviderEnabled(LocationManager.GPS_PROVIDER) || mLocService.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mLocService.requestLocationUpdates(mLocProvider.getName(), getGpsUpdatePeriod(prefs), getGpsDistanceUpdatePeriod(prefs), this);
            mGpsIsStarted = true;
        } else {
            gpsStatusTextView.setText(getString(R.string.status_gps_no_support));
        }
    }

    private synchronized void gpsStop() {
        if (mGpsIsStarted) {
            mLocService.removeUpdates(this);
            mGpsIsStarted = false;
            gpsStatusTextView.setText(getString(R.string.status_gps_stopped));
        }
    }

    public void setSupportActionBar(Toolbar supportActionBar) {
        this.supportActionBar = supportActionBar;
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    /**
     * Uploading asynchronous task
     */
    private class UploadAsyncTask extends AsyncTask<ObdReading, Void, Void> {

        @Override
        protected Void doInBackground(ObdReading... readings) {
            Log.d(TAG, "Uploading " + readings.length + " readings..");
            // instantiate reading service client
            final String endpoint = prefs.getString(ConfigActivity.UPLOAD_URL_KEY, "");
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .build();
            ObdService service = restAdapter.create(ObdService.class);
            // upload readings
            for (ObdReading reading : readings) {
                try {
                    Response response = service.uploadReading(reading);
                    assert response.getStatus() == 200;
                } catch (RetrofitError re) {
                    Log.e(TAG, re.toString());
                }
            }
            Log.d(TAG, "Done");
            return null;
        }

    }

    private class SynchronizingTask extends AsyncTask<Void, Void, String> {
        private ManageInformation info = new ManageInformation();
        private int response = 0;
        SynchronizingAdapter sa = new SynchronizingAdapter();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodSynchronization(sa.syncData());
                if (response == 1){
                    return getString(R.string.sincronized_data);
                }else{
                    return getString(R.string.no_data);
                }
            }
            catch (Exception e) {
                return getString(R.string.error_bad_communication);
            }
        }

        @Override
        protected void onPostExecute(final String success) {
            Toast.makeText(getActivity(), success,Toast.LENGTH_LONG).show();
        }
    }
}