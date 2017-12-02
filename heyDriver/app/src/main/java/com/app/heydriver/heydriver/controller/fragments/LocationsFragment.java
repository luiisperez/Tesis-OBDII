package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
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

/**
 * Created by Cristian on 28/11/2017.
 */

public class LocationsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Spinner spinner;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_locations, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_activity_locations));

        spinner = (Spinner) view.findViewById(R.id.spinner);


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.add(getString(R.string.select_ubication));

        //ejemplos
        spinnerAdapter.add("Avenida Páez El Paraíso");
        spinnerAdapter.add("Autopista Caracas La Guaira");
        spinnerAdapter.add("Autopista Francisco Fajardo");

        spinner.setOnItemSelectedListener(new ItemSelectedListener());
        spinnerAdapter.notifyDataSetChanged();

        return view;
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos == 0) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        getString(R.string.you_selected) + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings config = mMap.getUiSettings();
        config.setZoomControlsEnabled(true);

        // location
        try {
            LatLng coordenada = new LatLng(10.47706413269043f, -66.94752502441406f);
            // apuntador y icon
            mMap.addMarker(new MarkerOptions().position(coordenada).title("Carro de Luis")).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_mark));
            mMap.setTrafficEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenada));

            //zoom
            float zoom =16;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada,zoom));
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
}
