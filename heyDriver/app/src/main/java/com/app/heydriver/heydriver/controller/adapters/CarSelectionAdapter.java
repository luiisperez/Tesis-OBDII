package com.app.heydriver.heydriver.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.Car;

import java.util.ArrayList;

/**
 * Created by LAPGrock on 8/14/2017.
 */

public class CarSelectionAdapter extends RecyclerView.Adapter<CarSelectionAdapter.ViewHolder>{

    //private ArrayList<Car> _vehicleList;
    private ArrayList<String> _vehicleList;
    static public Car t;

    public CarSelectionAdapter(ArrayList<String> vehicleList) {
        _vehicleList = vehicleList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Switch vehicleName;
        public TextView vehicleSerial;
        public LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            vehicleName = (Switch) view.findViewById(R.id.sw_vehicle_name_list);
            vehicleSerial = (TextView) view.findViewById(R.id.tv_vehicle_serial_list);
            layout = (LinearLayout) view.findViewById(R.id.adapter_vehicle_list_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vehicle_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final String vehicleName = _vehicleList.get(position);
        holder.vehicleName.setText(vehicleName);
        holder.vehicleSerial.setText("Serial: 25413365239AD");

    }

    @Override
    public int getItemCount() {
        return _vehicleList.size();
    }
}
