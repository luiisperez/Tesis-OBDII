package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristian on 28/11/2017.
 */

public class LocationsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Spinner spinner;
    private View view;
    private LocationsFragment.SynchronizingLocation mSynchronizeTask = null;
    String ActualUbication = "";
    List<Address> addresses;
    ArrayList<String> _response;
    ArrayAdapter<String> spinnerAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_locations, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_activity_locations));



        try {
                mSynchronizeTask = new SynchronizingLocation();
                mSynchronizeTask.execute((Void) null);


        }
        catch (Exception e)
        {
            //respuesta
            Toast.makeText(getActivity(), getActivity().getString(R.string.error_bad_communication), Toast.LENGTH_LONG).show();
        }

        //ejemplos
//        spinnerAdapter.add("Av. José Antonio Páez, Caracas, Distrito Capital, Venezuela");
//        spinnerAdapter.add("Autopista Caracas La Guaira Caracas Venezuela");
//        spinnerAdapter.add("Autopista Francisco Fajardo Caracas Venezuela");

//        spinner.setOnItemSelectedListener(new ItemSelectedListener());
//        spinnerAdapter.notifyDataSetChanged();

        return view;
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        Geocoder geocoder = new Geocoder(getActivity());
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos == 0) {
                ActualUbication = "";
            } else {
                Toast.makeText(parent.getContext(),
                        getString(R.string.you_selected) + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                try {
                    ActualUbication = parent.getItemAtPosition(pos).toString();
                    addresses = geocoder.getFromLocationName(ActualUbication, 2);
                    markInMap(mMap,addresses);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try
        {
            int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
                if (status == ConnectionResult.SUCCESS){
                    MapFragment fragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.map));
                    fragment.getMapAsync(this); //datalistAdapter
                }
                else{
                    Toast.makeText(getActivity(), GooglePlayServicesUtil.getErrorDialog(status,getActivity(),10).toString(), Toast.LENGTH_LONG).show();
                }
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), getActivity().getString(R.string.error_bad_communication), Toast.LENGTH_LONG).show();
        }

    }

    public void markInMap(GoogleMap googleMap, List<Address> address)
    {
        LatLng coordenada,coordenada2 ;
        ArrayList<LatLng> points = new ArrayList<LatLng>();
        PolylineOptions polyLineOptions = new PolylineOptions();

        if (addresses!=null)
        {
            if(addresses.size() > 1) {

                coordenada = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                coordenada2 = new LatLng(addresses.get(1).getLatitude(),addresses.get(1).getLongitude());


                mMap.addMarker(new MarkerOptions().position(coordenada).title(ActualUbication)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_mark));
                mMap.setTrafficEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenada));

                //zoom
                float zoom =16;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,zoom));
            }
            if(addresses.size() == 1) {
                coordenada = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                mMap.addMarker(new MarkerOptions().position(coordenada).title(ActualUbication)).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_mark));

                mMap.setTrafficEnabled(true);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenada));

                //zoom
                float zoom =16;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,zoom));
            }
            if(addresses.size() == 0) {
                // no se ha encontrado la ubicación
                Toast.makeText(getActivity(), getActivity().getString(R.string.error_bad_communication), Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings config = mMap.getUiSettings();
        config.setZoomControlsEnabled(true);

        // location
        try {
            LatLng coordenada = new LatLng(0.0f, 0.0f);
            // apuntador y icon
            mMap.setTrafficEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenada));
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), getActivity().getString(R.string.error_bad_communication), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if (f != null)
            getFragmentManager().beginTransaction().remove(f).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private class SynchronizingLocation extends AsyncTask<Void, Void, Boolean> {
        private ManageInformation info = new ManageInformation();
        private ProgressDialog dialog = new ProgressDialog(getActivity());
        private ArrayList<String> response;

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodLocationStudies(info.getCarInformation(getActivity()).get_serial());
                if (response != null) {
                    _response = response;

                    return true;
                } else return false;
            }
            catch (Exception e) {
                response = null;
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                if (response != null) {
                    _response = response;


                    spinner = (Spinner) view.findViewById(R.id.spinner);
                    spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(spinnerAdapter);
                    spinnerAdapter.add(getString(R.string.select_ubication));

                    for(int x=0;x<_response.size();x++) {
                        spinnerAdapter.add(_response.get(x).toString());
                    }
                    spinner.setOnItemSelectedListener(new ItemSelectedListener());
                    spinnerAdapter.notifyDataSetChanged();




                    CharSequence text = getString(R.string.sincronized_data);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                }
            }
            else
            {
                if (response==null)
                {
                    Toast toast = Toast.makeText(getActivity(), R.string.no_data, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

}
