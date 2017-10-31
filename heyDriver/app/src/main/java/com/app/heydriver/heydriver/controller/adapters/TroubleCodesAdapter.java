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
import com.app.heydriver.heydriver.common.Entities.TroubleCode;

import java.util.ArrayList;

/**
 * Created by Cristian on 10/25/2017.
 */

public class TroubleCodesAdapter extends RecyclerView.Adapter<TroubleCodesAdapter.ViewHolder>{

    private ArrayList<TroubleCode> _troubleList;
    public static ArrayList<ViewHolder> holdersList;


    public TroubleCodesAdapter(ArrayList<TroubleCode> troubleList) {
        _troubleList = troubleList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public ImageView iconTrouble;
        public TextView vehicleName;
        public TextView vehicleSerial;
        public TextView troubleCode;
        public TextView zoneTrouble;
        public TextView descriptionTrouble;
        public RelativeLayout layout;

        public ViewHolder(View view) {
            super(view);
            v = view;
            iconTrouble = (ImageView) view.findViewById(R.id.trouble_image);
            vehicleName = (TextView) view.findViewById(R.id.tv_vehicle_name_list);
            vehicleSerial = (TextView) view.findViewById(R.id.tv_vehicle_tcserial_list);
            troubleCode = (TextView) view.findViewById(R.id.trouble_code_list);
            zoneTrouble = (TextView) view.findViewById(R.id.zone_trouble_list);
            descriptionTrouble = (TextView) view.findViewById(R.id.description_trouble_list);
            layout = (RelativeLayout) view.findViewById(R.id.adapter_trouble_codes_layout);
        }
    }

    public void removeFromHoldersList(int position){
        holdersList.remove(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_trouble_codes_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        try {
            TroubleCode troubleCode = (TroubleCode) _troubleList.get(position);
            View v = holder.v;
            Context context = v.getContext();

            holder.vehicleName.setText(context.getString(R.string.string_car_tc) +troubleCode.get_car());
            holder.vehicleSerial.setText(context.getString(R.string.string_serial_tc) +troubleCode.get_serial());
            holder.troubleCode.setText(context.getString(R.string.string_trouble_tc) +troubleCode.get_dtc());
            holder.descriptionTrouble.setText(context.getString(R.string.string_desc_tc) +troubleCode.get_desc());
            if(troubleCode.get_dtc().startsWith("P")){
                holder.iconTrouble.setImageDrawable(context.getDrawable(R.drawable.ic_report_p_engine));
                holder.zoneTrouble.setText(context.getString(R.string.string_zone_tc)+context.getString(R.string.engine_error));
            }
            else if(troubleCode.get_dtc().startsWith("B")){
                holder.iconTrouble.setImageDrawable(context.getDrawable(R.drawable.ic_report_b_body));
                holder.zoneTrouble.setText(context.getString(R.string.string_zone_tc)+context.getString(R.string.body_error));
            }
            else if(troubleCode.get_dtc().startsWith("C")){
                holder.iconTrouble.setImageDrawable(context.getDrawable(R.drawable.ic_report_c_chassis));
                holder.zoneTrouble.setText(context.getString(R.string.string_zone_tc)+context.getString(R.string.chasis_error));
            }
            else if(troubleCode.get_dtc().startsWith("U")){
                holder.iconTrouble.setImageDrawable(context.getDrawable(R.drawable.ic_report_u_network));
                holder.zoneTrouble.setText(context.getString(R.string.string_zone_tc)+context.getString(R.string.network_error));
            }
            holdersList.add(holder);
        }catch (Exception ex){
            ex.getStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return _troubleList.size();
    }
}
