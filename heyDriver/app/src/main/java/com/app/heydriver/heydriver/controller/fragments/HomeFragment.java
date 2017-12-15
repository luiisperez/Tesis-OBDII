package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.model.ManageInformation;

/**
 * Created by LAPGrock on 8/10/2017.
 */

public class HomeFragment extends Fragment {

    private View view;
    private TextView tv_selected_car;
    private TextView tv_troubles_number_home;
    private TextView tv_prediction_number_home;
    private TextView tv_locations_number_home;
    private TextView tv_data_number_home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_fragment_home));

        tv_selected_car = (TextView) view.findViewById(R.id.tv_selected_car);
        tv_troubles_number_home = (TextView) view.findViewById(R.id.tv_troubles_number_home);
        tv_prediction_number_home = (TextView) view.findViewById(R.id.tv_prediction_number_home);
        tv_locations_number_home = (TextView) view.findViewById(R.id.tv_locations_number_home);
        tv_data_number_home = (TextView) view.findViewById(R.id.tv_data_number_home);


        manageLayout();
        return view;
    }

    private void manageLayout() {
        ManageInformation info_car = new ManageInformation();
        if (info_car.getCarInformation(getActivity()).get_model()!= null) {
            tv_selected_car.setText(info_car.getCarInformation(getActivity()).get_brand().toString().concat(" ").concat(info_car.getCarInformation(getActivity()).get_model().toString()));

        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        Cursor cursor_dtc = db.rawQuery("SELECT count(*) FROM CAR_DTC WHERE vin_dtc='".concat(info_car.getCarInformation(getActivity()).get_serial().concat("'")),null);
            if (cursor_dtc.moveToFirst()) {
                tv_troubles_number_home.setText(String.valueOf(cursor_dtc.getString(0)));
            }
        Cursor cursor_predictions = db.rawQuery("SELECT DISTINCT prediction_code FROM CAR_PREDICTION WHERE vin_dtc='".concat(info_car.getCarInformation(getActivity()).get_serial().concat("'")),null);
            if (cursor_predictions.moveToFirst()) {
                tv_prediction_number_home.setText(String.valueOf(getCount(cursor_predictions.getString(0))));
            }

        Cursor cursor_locations = db.rawQuery("SELECT car_model, vin_dtc, trouble_code_dtc FROM CAR_DTC order by car_model",null);

        Cursor cursor_data = db.rawQuery("SELECT count(*) FROM HISTORICO WHERE Vehicle_Identification_Number='".concat(info_car.getCarInformation(getActivity()).get_serial().concat("'")),null);
            if (cursor_data.moveToFirst()) {
                tv_data_number_home.setText(String.valueOf(cursor_data.getString(0)));
            }
        }

    }

    private int getCount(String predictiveCode) {
        int a=0;
        for (int i=0;i<predictiveCode.length();i++)
        {
            Character charActual =predictiveCode.charAt(i);
            if ( charActual.equals('1'))
                a++;
        }
        return a;
    }


}
