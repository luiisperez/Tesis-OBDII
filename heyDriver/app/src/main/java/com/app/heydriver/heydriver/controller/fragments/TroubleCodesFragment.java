package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.TroubleCode;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.adapters.TroubleCodesAdapter;
import com.app.heydriver.heydriver.model.ManageInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;

/**
 * Created by Cristian on 31/10/2017.
 */

public class TroubleCodesFragment extends Fragment {

    private View view;
    private RecyclerView rv_trouble_list;
    private ArrayList<TroubleCode> troubleCodesArrayList = new ArrayList<TroubleCode>();
    private TroubleCodesAdapter troubleCodesSelectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.trouble_codes_item_list, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_fragment_trouble_codes));

        rv_trouble_list = (RecyclerView) view.findViewById(R.id.trouble_codes_list);
        manageRecyclerView();
        return view;
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

    // Return an ArrayList of Trouble Codes
    public ArrayList<TroubleCode> getTroubleCodesList() {
        try {
            ManageInformation info = new ManageInformation();
            ArrayList<TroubleCode> troubleCodesArrayList = new ArrayList<TroubleCode>();
            SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();

            Cursor cursor = db.rawQuery("SELECT car_model, vin_dtc, trouble_code_dtc FROM CAR_DTC WHERE vin_dtc='"+info.getCarInformation(getActivity()).get_serial()+"' order by car_model",null);
            if (cursor.moveToFirst()) {
                do {
                    //TODO
                    TroubleCode troubleCode = new TroubleCode();
                    troubleCode.set_car(cursor.getString(0));
                    troubleCode.set_serial(cursor.getString(1));
                    troubleCode.set_dtc(cursor.getString(2));
                    troubleCode.set_desc(getTroubleMessage(cursor.getString(2)));
                    troubleCodesArrayList.add(troubleCode);
                } while (cursor.moveToNext());
            }
            db.close();
            if (troubleCodesArrayList.size() >= 1) {
                return troubleCodesArrayList;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }


    private void manageRecyclerView() {
        troubleCodesArrayList.clear();
        troubleCodesArrayList = getTroubleCodesList();
        if (troubleCodesArrayList != null) {
            troubleCodesSelectionAdapter = new TroubleCodesAdapter(troubleCodesArrayList);
            troubleCodesSelectionAdapter.holdersList = new ArrayList<TroubleCodesAdapter.ViewHolder>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

            rv_trouble_list.setLayoutManager(layoutManager);
            rv_trouble_list.setItemAnimator(new DefaultItemAnimator());
            rv_trouble_list.setAdapter(troubleCodesSelectionAdapter);
        }
        else
        {
            Toast toast = Toast.makeText(getActivity(), R.string.no_trouble_codes, Toast.LENGTH_LONG);
            toast.show();
            FragmentManager fragmentManager = getFragmentManager();
            changeFragment(R.id.content_frame, fragmentManager, new HomeFragment(), R.id.nav_home, "home");
        }
    }
}
