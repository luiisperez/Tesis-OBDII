package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
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
import com.app.heydriver.heydriver.common.Entities.ControladorSQLite;
import com.app.heydriver.heydriver.common.Entities.PredictionRead;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.adapters.PredictionsAdapter;

import java.util.ArrayList;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;

/**
 * Created by Cristian on 2/12/2017.
 */

public class PredictionsFragment extends Fragment {

    private View view;
    private RecyclerView rv_prediction_list;
    private ArrayList<PredictionRead> predictionsCodesArrayList = new ArrayList<PredictionRead>();
    private PredictionsAdapter predictionsSelectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.predictions_codes_item_list, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.menu_predictions));
        rv_prediction_list = (RecyclerView) view.findViewById(R.id.predictios_codes_list);
        manageRecyclerView();

        // (pruebas) justo antes de cada sincronización, esta lista debe ser limpiada
        //saveDTC("KNAMB763386190635","Kia","Sedona","1010100000");
        return view;
    }

    public boolean saveDTC(String vin, String car_name,String car_model, String code) {
        SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(ControladorSQLite.DatosTabla.VIN_DTC,String.valueOf(vin));
        valores.put(ControladorSQLite.DatosTabla.CAR_NAME,String.valueOf(car_name));
        valores.put(ControladorSQLite.DatosTabla.CAR_MODEL,String.valueOf(car_model));
        valores.put(ControladorSQLite.DatosTabla.PREDICTION_CODE,String.valueOf(code));
        try
        {
            Cursor promedium_cursor = db.rawQuery("SELECT count(*) FROM CAR_PROMEDIUM WHERE vin_dtc='"+vin+"'", null);
            if (promedium_cursor.moveToFirst() ) {
                if(promedium_cursor.getInt(0)>=1)
                {
                    long IdUpdate = db.update(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, valores, "vin_dtc='" + vin + "'", null);
                }
                else
                {
                    long IdGuardado = db.insert(ControladorSQLite.DatosTabla.TABLA_CAR_PREDICTION, "id", valores);
                }
            }
            db.close();
            return true;
        }
        catch (Exception e){
            String b = e.toString();
            return false;
        }
    }


    // Return an ArrayList of Trouble Codes
    public ArrayList<PredictionRead> getPredictionsCodesList() {
        try {
            ArrayList<PredictionRead> predictionsCodesArrayList = new ArrayList<PredictionRead>();
            SQLiteDatabase db = HomeActivity.controladorSQLite.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT DISTINCT car_name, car_model, vin_dtc, prediction_code FROM CAR_PREDICTION order by car_model",null);

            // aquí se debe iterar en el string del código binario retornado, del 0 al 10. Cada #1, debe generar una anomalía.
            if (cursor.moveToFirst()) {
                do {
                    String response = cursor.getString(3);

                    for (int i=0;i<response.length();i++)
                    {
                        PredictionRead predictionRead = new PredictionRead();
                        predictionRead.set_car(cursor.getString(0));
                        predictionRead.set_model(cursor.getString(1));
                        predictionRead.set_serial(cursor.getString(2));

                        Character charActual =response.charAt(i);
                        if ( charActual.equals('1'))
                        {
                            if (i==0)
                            {
                                //Consumo Desproporcionado
                                predictionRead.set_prediction(getString(R.string.prediction_highConsumption));
                                predictionRead.set_desc(getString(R.string.desc_highConsumption));
                            }
                            if (i==1)
                            {
                                //Mezcla Pobre
                                predictionRead.set_prediction(getString(R.string.prediction_mix_too_lean));
                                predictionRead.set_desc(getString(R.string.desc_mix_to_lean));
                            }
                            if (i==2)
                            {
                                //Mezcla Rica
                                predictionRead.set_prediction(getString(R.string.prediction_mix_too_rich));
                                predictionRead.set_desc(getString(R.string.desc_mix_too_rich));
                            }
                            if (i==3)
                            {
                                //MAF sucio/averiado
                                predictionRead.set_prediction(getString(R.string.prediction_maf));
                                predictionRead.set_desc(getString(R.string.desc_maf));
                            }
                            if (i==4)
                            {
                                //Inyectores Obstruidos o averiados
                                predictionRead.set_prediction(getString(R.string.prediction_injectors));
                                predictionRead.set_desc(getString(R.string.desc_injector));
                            }
                            if (i==5)
                            {
                                //Bobinas Averiadas
                                predictionRead.set_prediction(getString(R.string.prediction_coil));
                                predictionRead.set_desc(getString(R.string.desc_coil));
                            }
                            if (i==6)
                            {
                                //Bujías Propensas a Averías
                                predictionRead.set_prediction(getString(R.string.prediction_spark));
                                predictionRead.set_desc(getString(R.string.desc_spark));
                            }
                            if (i==7)
                            {
                                //Propensión a Recalentamiento
                                predictionRead.set_prediction(getString(R.string.prediction_temperature));
                                predictionRead.set_desc(getString(R.string.desc_temperature));
                            }
                            if (i==8)
                            {
                                //Radiador Obtruído o Averiado
                                predictionRead.set_prediction(getString(R.string.prediction_radiator));
                                predictionRead.set_desc(getString(R.string.desc_radiator));
                            }
                            if (i==9)
                            {
                                //Alternador Averiado
                                predictionRead.set_prediction(getString(R.string.prediction_alternator));
                                predictionRead.set_desc(getString(R.string.desc_alternator));
                            }

                            predictionsCodesArrayList.add(predictionRead);
                        }
                    }
                } while (cursor.moveToNext());
            }
            db.close();
            if (predictionsCodesArrayList.size() >= 1) {
                return predictionsCodesArrayList;
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
        predictionsCodesArrayList.clear();
        predictionsCodesArrayList = getPredictionsCodesList();
        if (predictionsCodesArrayList != null) {
            predictionsSelectionAdapter = new PredictionsAdapter(predictionsCodesArrayList);
            predictionsSelectionAdapter.holdersList = new ArrayList<PredictionsAdapter.ViewHolder>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

            rv_prediction_list.setLayoutManager(layoutManager);
            rv_prediction_list.setItemAnimator(new DefaultItemAnimator());
            rv_prediction_list.setAdapter(predictionsSelectionAdapter);
        }
        else
        {
            Toast toast = Toast.makeText(getActivity(), R.string.no_prediction_codes, Toast.LENGTH_LONG);
            toast.show();
            FragmentManager fragmentManager = getFragmentManager();
            changeFragment(R.id.content_frame, fragmentManager, new HomeFragment(), R.id.nav_home, "home");
        }
    }

}
