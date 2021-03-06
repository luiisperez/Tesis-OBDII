package com.app.heydriver.heydriver.controller.activities;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.github.pires.obd.commands.ObdCommand;
import com.github.pires.obd.enums.ObdProtocols;
import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.model.ObdConfig;

import java.util.ArrayList;
import java.util.Set;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ConfigActivity extends PreferenceActivity implements OnPreferenceChangeListener {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };
    public static final String BLUETOOTH_LIST_KEY = "bluetooth_list_preference";
    public static final String UPLOAD_URL_KEY = "upload_url_preference";
    public static final String UPLOAD_DATA_KEY = "upload_data_preference";
    public static final String OBD_UPDATE_PERIOD_KEY = "obd_update_period_preference";
    public static final String VEHICLE_ID_KEY = "vehicle_id_preference";
    public static final String ENGINE_DISPLACEMENT_KEY = "engine_displacement_preference";
    public static final String VOLUMETRIC_EFFICIENCY_KEY = "volumetric_efficiency_preference";
    public static final String IMPERIAL_UNITS_KEY = "imperial_units_preference";
    public static final String COMMANDS_SCREEN_KEY = "obd_commands_screen";
    public static final String PROTOCOLS_LIST_KEY = "obd_protocols_preference";
    public static final String ENABLE_GPS_KEY = "enable_gps_preference";
    public static final String GPS_UPDATE_PERIOD_KEY = "gps_update_period_preference";
    public static final String GPS_DISTANCE_PERIOD_KEY = "gps_distance_period_preference";
    public static final String ENABLE_BT_KEY = "enable_bluetooth_preference";
    public static final String MAX_FUEL_ECON_KEY = "max_fuel_econ_preference";
    public static final String CONFIG_READER_KEY = "reader_config_preference";
    public static final String ENABLE_FULL_LOGGING_KEY = "enable_full_logging";
    public static final String DIRECTORY_FULL_LOGGING_KEY = "dirname_full_logging";
    public static final String DEV_EMAIL_KEY = "dev_email";

    /**
     * @param prefs
     * @return
     */
    public static int getObdUpdatePeriod(SharedPreferences prefs) {
        return 4000;
    }

    /**
     * @param prefs
     * @return
     */
    public static int getGpsUpdatePeriod(SharedPreferences prefs) {
        return 1000;
    }

    /**
     * Min Distance between location updates, in meters
     *
     * @param prefs
     * @return
     */
    public static float getGpsDistanceUpdatePeriod(SharedPreferences prefs) {
        String periodString = prefs
                .getString(ConfigActivity.GPS_DISTANCE_PERIOD_KEY, "5"); // 5 as in meters
        float period = 5; // by default 5 meters

        try {
            period = Float.parseFloat(periodString);
        } catch (Exception e) {
        }

        if (period <= 0) {
            period = 5;
        }

        return period;
    }

///////////////////////////////////////////////////////////////////////
///////////////////////                         ///////////////////////
///////////////////////   INICIO DEL ONCREATE   ///////////////////////
///////////////////////                         ///////////////////////
///////////////////////////////////////////////////////////////////////


    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);

    /*
     * Read preferences resources available at res/xml/preferences.xml
     */
            addPreferencesFromResource(R.layout.preferences);

            checkGps();

            ArrayList<CharSequence> pairedDeviceStrings = new ArrayList<>();
            ArrayList<CharSequence> vals = new ArrayList<>();
            ListPreference listBtDevices = (ListPreference) getPreferenceScreen()
                    .findPreference(BLUETOOTH_LIST_KEY);
//            ArrayList<CharSequence> protocolStrings = new ArrayList<>();
//            ListPreference listProtocols = (ListPreference) getPreferenceScreen()
//                    .findPreference(PROTOCOLS_LIST_KEY);
//            String[] prefKeys = new String[]{ENGINE_DISPLACEMENT_KEY,
//                    VOLUMETRIC_EFFICIENCY_KEY, OBD_UPDATE_PERIOD_KEY, MAX_FUEL_ECON_KEY};
//            for (String prefKey : prefKeys) {
//                EditTextPreference txtPref = (EditTextPreference) getPreferenceScreen()
//                        .findPreference(prefKey);
//                txtPref.setOnPreferenceChangeListener(this);
//            }

    /*
     * Available OBD commands
     *
     * TODO This should be read from preferences database
     */
//            ArrayList<ObdCommand> cmds = ObdConfig.getCommands();
//            PreferenceScreen cmdScr = (PreferenceScreen) getPreferenceScreen()
//                    .findPreference(COMMANDS_SCREEN_KEY);
//            for (ObdCommand cmd : cmds) {
//                CheckBoxPreference cpref = new CheckBoxPreference(this);
//                cpref.setTitle(cmd.getName());
//                cpref.setKey(cmd.getName());
//                cpref.setChecked(true);
//                cmdScr.addPreference(cpref);
//            }

    /*
     * Available OBD protocols
     *
     */
//            for (ObdProtocols protocol : ObdProtocols.values()) {
//                protocolStrings.add(protocol.name());
//            }
//            listProtocols.setEntries(protocolStrings.toArray(new CharSequence[0]));
//            listProtocols.setEntryValues(protocolStrings.toArray(new CharSequence[0]));

    /*
     * Let's use this device Bluetooth adapter to select which paired OBD-II
     * compliant device we'll use.
     */
            final BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
            if (mBtAdapter == null) {
                listBtDevices
                        .setEntries(pairedDeviceStrings.toArray(new CharSequence[0]));
                listBtDevices.setEntryValues(vals.toArray(new CharSequence[0]));

                // we shouldn't get here, still warn user
                Toast.makeText(this, "This device does not support Bluetooth.",
                        Toast.LENGTH_LONG).show();

                return;
            }

            final Preference bluetooth = (Preference) findPreference("enable_bluetooth_preference");
            bluetooth.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                public boolean onPreferenceClick(Preference preference) {
                    if (mBtAdapter == null) {
                        Toast.makeText(getApplication(),
                                "This device does not support Bluetooth or it is disabled.",
                                Toast.LENGTH_LONG).show();
                        return false;
                    }else if (!mBtAdapter.isEnabled()) {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivity(enableBtIntent);
                    }else if (mBtAdapter.isEnabled()) {
                        mBtAdapter.disable();
                    }
                    return true;
                }
            });

    /*
     * Listen for preferences click.
     *
     * TODO there are so many repeated validations :-/
     */
            final Activity thisActivity = this;
            listBtDevices.setEntries(new CharSequence[1]);
            listBtDevices.setEntryValues(new CharSequence[1]);
            listBtDevices.setOnPreferenceClickListener(new OnPreferenceClickListener() {
                public boolean onPreferenceClick(Preference preference) {
                    // see what I mean in the previous comment?
                    if (mBtAdapter == null || !mBtAdapter.isEnabled()) {
                        Toast.makeText(thisActivity,
                                "This device does not support Bluetooth or it is disabled.",
                                Toast.LENGTH_LONG).show();
                        return false;
                    }
                    return true;
                }
            });

    /*
     * Get paired devices and populate preference list.
     */
            Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    pairedDeviceStrings.add(device.getName() + "\n" + device.getAddress());
                    vals.add(device.getAddress());
                }
            }
            listBtDevices.setEntries(pairedDeviceStrings.toArray(new CharSequence[0]));
            listBtDevices.setEntryValues(vals.toArray(new CharSequence[0]));
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }



///////////////////////////////////////////////////////////////////////
///////////////////////                         ///////////////////////
///////////////////////    FIN DEL ONCREATE     ///////////////////////
///////////////////////                         ///////////////////////
///////////////////////////////////////////////////////////////////////

    /**
     * OnPreferenceChangeListener method that will validate a preferencen new
     * value when it's changed.
     *
     * @param preference the changed preference
     * @param newValue   the value to be validated and set if valid
     */
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        if (OBD_UPDATE_PERIOD_KEY.equals(preference.getKey())
                || VOLUMETRIC_EFFICIENCY_KEY.equals(preference.getKey())
                || ENGINE_DISPLACEMENT_KEY.equals(preference.getKey())
                || MAX_FUEL_ECON_KEY.equals(preference.getKey())
                || GPS_UPDATE_PERIOD_KEY.equals(preference.getKey())
                || GPS_DISTANCE_PERIOD_KEY.equals(preference.getKey())) {
            try {
                Double.parseDouble(newValue.toString().replace(",", "."));
                return true;
            } catch (Exception e) {
                Toast.makeText(this,
                        "Couldn't parse '" + newValue.toString() + "' as a number.",
                        Toast.LENGTH_LONG).show();
            }
        }
        return false;
    }


    // Check permission for the GPS
    public boolean checkLocationPermission()
    {
        String permission = "android.permission.ACCESS_FINE_LOCATION";
        int res = this.checkCallingOrSelfPermission(permission);

        return (res == PackageManager.PERMISSION_GRANTED);
    }

    // control request permission
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(this, R.string.status_gps_ready, Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Toast toast = Toast.makeText(this, R.string.status_bluetooth_disabled, Toast.LENGTH_SHORT);
                    toast.show();
                }
                return;
            }
        }
    }

    private void checkGps() {
        LocationManager mLocService = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocService != null && checkLocationPermission()) {
            LocationProvider mLocProvider = mLocService.getProvider(LocationManager.NETWORK_PROVIDER);
            if (mLocProvider == null) {
                hideGPSCategory();
            }
        }
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1222);
    }

    private void hideGPSCategory() {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference(getResources().getString(R.string.pref_gps_category));
        if (preferenceCategory != null) {
            preferenceCategory.removeAll();
            preferenceScreen.removePreference((Preference) preferenceCategory);
        }
    }
}
