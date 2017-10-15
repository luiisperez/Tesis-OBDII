package com.app.heydriver.heydriver.controller.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
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
import com.app.heydriver.heydriver.model.AbstractGatewayService;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.MockObdGatewayService;
import com.app.heydriver.heydriver.model.ObdCommandJob;
import com.app.heydriver.heydriver.model.ObdConfig;
import com.app.heydriver.heydriver.model.ObdGatewayService;
import com.app.heydriver.heydriver.model.ObdProgressListener;
import com.app.heydriver.heydriver.model.ObdReading;
import com.app.heydriver.heydriver.model.ObdService;
import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.AvailableCommandNames;

import static com.app.heydriver.heydriver.controller.activities.HomeActivity.controladorSQLite;
import static com.github.pires.obd.enums.AvailableCommandNames.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.Context.SENSOR_SERVICE;
import static com.app.heydriver.heydriver.controller.activities.ConfigActivity.getGpsDistanceUpdatePeriod;
import static com.app.heydriver.heydriver.controller.activities.ConfigActivity.getGpsUpdatePeriod;

public class ObdReaderFragment extends Fragment
        implements ObdProgressListener, LocationListener, GpsStatus.Listener {

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
    private static final int TRIPS_LIST = 10;
    private static final int SAVE_TRIP_NOT_AVAILABLE = 11;
    private static final int REQUEST_ENABLE_BT = 1234;
    private static boolean bluetoothDefaultIsEnable = false;
    private Button b_start_data;
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
                        //Aquí se debería guardar en SQLite
                        //myCSVWriter.writeLineCSV(reading)
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
        //updateTripStatistic(job, cmdID);

    }

    public float stringToFloat(String string) {
        String newString = "";
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= 48 &&
                    string.charAt(i) <= 57 ||
                    string.charAt(i) == '.' ||
                    string.charAt(i) == '-') {
                newString += string.charAt(i);
            } else {
                break;
            }
        }
        return Float.parseFloat(newString);
    }

    // update SQLite Statistics
    public void updateBdStatistic( String sensorName, String value) {
        final ControladorSQLite controladorSQLite = new ControladorSQLite(getActivity().getApplicationContext());
        SQLiteDatabase db = controladorSQLite.getWritableDatabase();
        long mils = System.currentTimeMillis();
        SimpleDateFormat mask = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");
        //Date creationDate = mask.parse(el_string_con_la_fecha);
        double lat = 0;
        double lon = 0;
        double alt = 0;
        final int posLen = 7;
        if (mGpsIsStarted && mLastLocation != null) {
            lat = mLastLocation.getLatitude();
            lon = mLastLocation.getLongitude();
            alt = mLastLocation.getAltitude();
        }

        //Colección para almacenar en BD
        ContentValues valores = new ContentValues();
        ObdData dataSensor = new ObdData();
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
                //dataSensor.setVehicle_Identification_Number((String) e.getValue());
                ManageInformation info = new ManageInformation();
                dataSensor.setVehicle_Identification_Number(info.getCarInformation(getActivity()).get_serial());
            }
            if (e.getKey().equals( TROUBLE_CODES.toString()))
            {
                dataSensor.setTrouble_Codes((String) e.getValue());
            }
            if (e.getKey().equals( TIMING_ADVANCE.toString()))
            {
                dataSensor.setTiming_Advance(stringToFloat((String) e.getValue()));
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
            long IdGuardado = db.insert(ControladorSQLite.DatosTabla.NOMBRE_TABLA, "id", valores);
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
            mLocProvider = mLocService.getProvider(LocationManager.GPS_PROVIDER);
            if (mLocProvider != null) {
                mLocService.addGpsStatusListener(this);
                if (mLocService.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    gpsStatusTextView.setText(getString(R.string.status_gps_ready));
                    return true;
                }
            }
        }
        gpsStatusTextView.setText(getString(R.string.status_gps_no_support));
        Toast toast = Toast.makeText(getActivity(), "Sorry, your device doesn\\'t support or has disabled GPS", Toast.LENGTH_SHORT);
        toast.show();
        return false;
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
                startLiveData();
            }
        });
        return view;
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
        //try&catch
        gpsInit();

        if (!preRequisites) {
            //showDialog(BLUETOOTH_DISABLED);
            btStatusTextView.setText(getString(R.string.status_bluetooth_disabled));
        } else {
            btStatusTextView.setText(getString(R.string.status_bluetooth_ok));
        }
    }
/*    private void updateConfig() {
        startActivity(new Intent(getActivity(), ConfigActivity.class));
    }*/

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, START_LIVE_DATA, 0, getString(R.string.menu_start_live_data));
        menu.add(0, STOP_LIVE_DATA, 0, getString(R.string.menu_stop_live_data));
        menu.add(0, GET_DTC, 0, getString(R.string.menu_get_dtc));
        menu.add(0, SETTINGS, 0, getString(R.string.menu_settings));
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
*/

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case START_LIVE_DATA:
                startLiveData();
                return true;
            case STOP_LIVE_DATA:
                stopLiveData();
                return true;
            case SETTINGS:
                updateConfig();
                return true;
            case GET_DTC:
                //getTroubleCodes();
                //esto iría en un fragment
                return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    private void startLiveData() {
        Log.d(TAG, "Starting live data..");

        tl.removeAllViews(); //start fresh
        doBindService();

        //currentTrip = triplog.startTrip();
        //if (currentTrip == null)
        //    showDialog(SAVE_TRIP_NOT_AVAILABLE);


        // start command execution
        new Handler().post(mQueueCommands);

        /*if (prefs.getBoolean(ConfigActivity.ENABLE_GPS_KEY, false))
            gpsStart();
        else
            gpsStatusTextView.setText(getString(R.string.status_gps_stopped));*/


        try {
            gpsStart();
        }
        catch (Exception e) {
            gpsStatusTextView.setText(getString(R.string.status_gps_stopped));
        }
        // screen won't turn off until wakeLock.release()
        wakeLock.acquire();
        /*
        //Aquí, interfaz para guardar datos.
        if (prefs.getBoolean(ConfigActivity.ENABLE_FULL_LOGGING_KEY, false)) {

            // Create the CSV Logger
            long mils = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");

            try {
                myCSVWriter = new LogCSVWriter("Log" + sdf.format(new Date(mils)).toString() + ".csv",
                        prefs.getString(ConfigActivity.DIRECTORY_FULL_LOGGING_KEY,
                                getString(R.string.default_dirname_full_logging))
                );
            } catch (FileNotFoundException | RuntimeException e) {
                Log.e(TAG, "Can't enable logging to file.", e);
            }
        }
        */
    }

    private void stopLiveData() {
        Log.d(TAG, "Stopping live data..");

        gpsStop();

        doUnbindService();
        //endTrip();

        releaseWakeLockIfHeld();
        /*
        //Para enviar datos por email
        final String devemail = prefs.getString(ConfigActivity.DEV_EMAIL_KEY, null);
        if (devemail != null && !devemail.isEmpty()) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            ObdGatewayService.saveLogcatToFile(getApplicationContext(), devemail);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Where there issues?\nThen please send us the logs.\nSend Logs?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }

        if (myCSVWriter != null) {
            myCSVWriter.closeLogCSVWriter();
        }
        */
    }

    /*
    // Dialog (only in Activity class)
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        switch (id) {
            case NO_BLUETOOTH_ID:
                build.setMessage(getString(R.string.text_no_bluetooth_id));
                return build.create();
            case BLUETOOTH_DISABLED:
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                return build.create();
            case NO_ORIENTATION_SENSOR:
                build.setMessage(getString(R.string.text_no_orientation_sensor));
                return build.create();
            case NO_GPS_SUPPORT:
                build.setMessage(getString(R.string.text_no_gps_support));
                return build.create();
            case SAVE_TRIP_NOT_AVAILABLE:
                build.setMessage(getString(R.string.text_save_trip_not_available));
                return build.create();
        }
        return null;
    }
*/
/*
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem startItem = menu.findItem(START_LIVE_DATA);
        MenuItem stopItem = menu.findItem(STOP_LIVE_DATA);
        MenuItem settingsItem = menu.findItem(SETTINGS);
        MenuItem getDTCItem = menu.findItem(GET_DTC);

        if (service != null && service.isRunning()) {
            getDTCItem.setEnabled(false);
            startItem.setEnabled(false);
            stopItem.setEnabled(true);
            settingsItem.setEnabled(false);
        } else {
            getDTCItem.setEnabled(true);
            stopItem.setEnabled(false);
            startItem.setEnabled(true);
            settingsItem.setEnabled(true);
        }

        return true;
    }
*/

    //Agrega una fila a la lista de parámetros VER
    private void addTableRow(String id, String key, String val) {

        TableRow tr = new TableRow(getActivity());
        // Recordar que eran Wrap_Content
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(TABLE_ROW_MARGIN, TABLE_ROW_MARGIN, TABLE_ROW_MARGIN,
                TABLE_ROW_MARGIN);
        tr.setLayoutParams(params);

        TextView name = new TextView(getActivity());
        name.setGravity(Gravity.RIGHT);
        name.setText(key + ": ");
        //Show
        TextView value = new TextView(getActivity());
        value.setGravity(Gravity.LEFT);
        value.setText(val); // valor
        value.setTag(id); //nombre
        tr.addView(name);
        tr.addView(value);
        tl.addView(tr, params);
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

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                btStatusTextView.setText(getString(R.string.status_bluetooth_connected));
            } else {
                Toast.makeText(this, R.string.text_bluetooth_disabled, Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    */

    private synchronized void gpsStart() {
        if (!mGpsIsStarted && mLocProvider != null && mLocService != null && mLocService.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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

/*    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    */


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
}
