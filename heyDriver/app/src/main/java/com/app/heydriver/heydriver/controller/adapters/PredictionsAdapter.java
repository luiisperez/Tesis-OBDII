package com.app.heydriver.heydriver.controller.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.PredictionRead;

import java.util.ArrayList;

/**
 * Created by Cristian on 2/12/2017.
 */

public class PredictionsAdapter extends RecyclerView.Adapter<PredictionsAdapter.ViewHolder>{

    private ArrayList<PredictionRead> _predictionsList;
    public static ArrayList<PredictionsAdapter.ViewHolder> holdersList;
    public PredictionsAdapter(ArrayList<PredictionRead> predictionsList) {
        _predictionsList = predictionsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public ImageView iconPrediction;
        public TextView vehicleName;
        public TextView vehicleSerial;
        public TextView predictionName;
        public TextView descriptionPrediction;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            v = view;
            iconPrediction = (ImageView) view.findViewById(R.id.prediction_image);
            vehicleName = (TextView) view.findViewById(R.id.tv_vehicle_name_list_prediction);
            vehicleSerial = (TextView) view.findViewById(R.id.tv_vehicle_tcserial_list_prediction);
            predictionName = (TextView) view.findViewById(R.id.tv_prediction_name_list);
            descriptionPrediction = (TextView) view.findViewById(R.id.tv_description_predition_list);
            layout = (RelativeLayout) view.findViewById(R.id.adapter_prediction_layout);
        }
    }

    public void removeFromHoldersList(int position){
        holdersList.remove(position);
    }

    @Override
    public PredictionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_prediction_list, parent, false);

        return new PredictionsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PredictionsAdapter.ViewHolder holder, int position) {

        try {
            PredictionRead _prediction = (PredictionRead) _predictionsList.get(position);
            View v = holder.v;
            Context context = v.getContext();

            holder.vehicleName.setText(context.getString(R.string.string_car_tc) +_prediction.get_car()+" "+_prediction.get_model());
            holder.vehicleSerial.setText(context.getString(R.string.string_serial_tc) +_prediction.get_serial());
            holder.predictionName.setText(context.getString(R.string.string_anomaly_tc) +_prediction.get_prediction());
            holder.descriptionPrediction.setText(context.getString(R.string.string_desc_tc) +_prediction.get_desc());

            if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_highConsumption)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_compsumption));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_mix_too_lean)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_mix_too_lean));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_mix_too_rich)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_mix_too_rich));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_maf)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_dirty_maf));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_injectors)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_injector));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_coil)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_coil));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_spark)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_spark));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_temperature)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_prediction_temperature));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_radiator)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_radiator));
            }
            else if(_prediction.get_prediction().startsWith(context.getString(R.string.prediction_alternator)))
            {
                holder.iconPrediction.setImageDrawable(context.getDrawable(R.drawable.ic_alternator));
            }


            holdersList.add(holder);
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return _predictionsList.size();
    }

}
