package com.app.heydriver.heydriver.controller.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.model.ManageInformation;

import java.util.ArrayList;

/**
 * Created by LAPGrock on 8/14/2017.
 */

public class CarSelectionAdapter extends RecyclerView.Adapter<CarSelectionAdapter.ViewHolder>{

    private ArrayList<Car> _vehicleList;
    public static ArrayList<ViewHolder> holdersList;
    private ManageInformation storedInformation = new ManageInformation();

    public CarSelectionAdapter(ArrayList<Car> vehicleList) {
        _vehicleList = vehicleList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public Switch vehicleName;
        public TextView vehicleYear;
        public TextView vehicleSerial;
        public LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            v = view;
            vehicleName = (Switch) view.findViewById(R.id.sw_vehicle_name_list);
            vehicleYear = (TextView) view.findViewById(R.id.tv_vehicle_year_list);
            vehicleSerial = (TextView) view.findViewById(R.id.tv_vehicle_serial_list);
            layout = (LinearLayout) view.findViewById(R.id.adapter_vehicle_list_layout);
            vehicleName.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String brand_model = vehicleName.getText().toString();
                    String _serial = vehicleSerial.getText().toString();
                    String year = vehicleYear.getText().toString().split(": ")[1];
                    boolean checkSelected = checkSelectedCar(brand_model, _serial);
                    if ((vehicleName.isChecked()) && (!checkSelected)){
                        String brand = brand_model.split("/")[0];
                        String model = brand_model.split("/")[1];
                        String serial = vehicleSerial.getText().toString().split(": ")[1];
                        Car selectedCar = new Car(serial, brand, model, Integer.parseInt(year));
                        storedInformation.writeCarInformation(selectedCar, v.getContext());
                    } else if (vehicleName.isChecked() && (checkSelected)){

                        vehicleName.setChecked(false);
                        boolean t = vehicleName.isChecked();
                        String h = (String) vehicleName.getText();
                        Context context = v.getContext();
                        CharSequence text = context.getString(R.string.selected_vehicle);
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }

                }
            });
        }
    }

    public boolean checkSelectedCar(String model_brand, String _serial){
        boolean selected = false;
        for (ViewHolder vh: holdersList) {
            String car = vh.vehicleName.getText().toString();
            String serial = vh.vehicleSerial.getText().toString();
            boolean checked = vh.vehicleName.isChecked();
            if (checked && (!car.equals(model_brand)) && (!serial.equals(_serial))){
                selected = true;
            }
        }
        return selected;
    }

    public void removeFromHoldersList(int position){
        holdersList.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vehicle_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Car car = _vehicleList.get(position);
        View v = holder.v;
        Context context = v.getContext();
        Car storedCar = storedInformation.getCarInformation(context);
        if (storedCar.get_serial().equals(car.get_serial())){
            holder.vehicleName.setChecked(true);
        }else{
            holder.vehicleName.setChecked(false);
        }
        final String vehicleName = car.get_brand() + "/" + car.get_model();
        holder.vehicleName.setText(vehicleName);
        CharSequence text = context.getString(R.string.year);
        holder.vehicleYear.setText(text + " " + String.valueOf(car.get_year()));
        holder.vehicleSerial.setText("Serial: " + car.get_serial());
        holdersList.add(holder);
    }

    @Override
    public int getItemCount() {
        return _vehicleList.size();
    }
}
