package com.app.heydriver.heydriver.controller.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.adapters.CarSelectionAdapter;
import com.app.heydriver.heydriver.model.ApiNHTSA;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;

/**
 * Created by LAPGrock on 8/10/2017.
 */

public class CarSelectionFragment extends Fragment {

    private CarSelectionFragment.UserGetCarTask Task = null;
    private View view;
    private FloatingActionButton fab_add;
    private RecyclerView rv_vehicle_list;
    private ArrayList<Car> carsArrayList = new ArrayList<Car>();
    private CarSelectionAdapter carSelectionAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car_selection, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_fragment_car_selection));
        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_add);
        rv_vehicle_list = (RecyclerView) view.findViewById(R.id.vehicle_list);
        manageRecyclerView();
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_vehicle();
            }
        });
        return view;
    }

    private void add_vehicle() {

        FragmentManager fragmentManager = getFragmentManager();
        changeFragment(R.id.content_frame, fragmentManager, new CarRegistrationFragment(), R.id.nav_carregistration, "car_selection");

    }


    private void manageRecyclerView(){

        try {
            carsArrayList.clear();
            Task = new CarSelectionFragment.UserGetCarTask();
            Task.execute((Void) null);
        }catch(Exception ex){
            Context context = getActivity();
            CharSequence text = getString(R.string.error_bad_communication);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    public class UserGetCarTask extends AsyncTask<Void, Void, Boolean> {
        private ArrayList<Car> response;
        private ManageInformation info = new ManageInformation();
        private final String user = info.getUserInformation(getActivity()).get_username();

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodGetUserVehicles(info.getUserInformation(getActivity()).get_username());
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            Task = null;

            if (success) {
                //finish();
                if (response != null){
                    carsArrayList = response;
                    carSelectionAdapter = new CarSelectionAdapter(carsArrayList);
                    CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rv_vehicle_list.setLayoutManager(layoutManager);
                    rv_vehicle_list.setItemAnimator(new DefaultItemAnimator());
                    rv_vehicle_list.setAdapter(carSelectionAdapter);
                    SwipeableRecyclerViewTouchListener swipeTouchListener =
                            new SwipeableRecyclerViewTouchListener(rv_vehicle_list,
                                    new SwipeableRecyclerViewTouchListener.SwipeListener() {
                                        @Override
                                        public boolean canSwipeLeft(int position) {
                                            return true;
                                        }

                                        @Override
                                        public boolean canSwipeRight(int position) {
                                            return true;
                                        }

                                        @Override
                                        public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                            for (int position : reverseSortedPositions) {
                                                CarSelectionFragment.UserDeleteCarTask _Task = new CarSelectionFragment.UserDeleteCarTask(carsArrayList.get(position));
                                                _Task.execute((Void) null);

                                                carsArrayList.remove(position);
                                                carSelectionAdapter.notifyItemRemoved(position);
                                                carSelectionAdapter.removeFromHoldersList(position);
                                            }
                                            CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                                            carSelectionAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                            for (int position : reverseSortedPositions) {
                                                carsArrayList.remove(position);
                                                carSelectionAdapter.removeFromHoldersList(position);
                                                carSelectionAdapter.notifyItemRemoved(position);
                                            }
                                            CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                                            carSelectionAdapter.notifyDataSetChanged();
                                        }
                                    });

                    rv_vehicle_list.addOnItemTouchListener(swipeTouchListener);
                }else {
                    Context context = getActivity();
                    CharSequence text = getString(R.string.error_bad_communication);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            } else {
                Context context = getActivity();
                CharSequence text = getString(R.string.error_bad_communication);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            Task = null;
            Context context = getActivity();
            CharSequence text = getString(R.string.operation_cancelled);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }


    public class UserDeleteCarTask extends AsyncTask<Void, Void, Boolean> {
        private Car car;
        private int response;
        private ManageInformation info = new ManageInformation();

        public UserDeleteCarTask(Car _car){
            this.car = _car;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                RestCommunication con = new RestCommunication();
                User u = info.getUserInformation(getActivity());
                response = con.callMethodDeleteVehicle(u.get_username(), car.get_serial());
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            Task = null;

            if (success) {
                //finish();
                if (response == 200){
                    Car storedCar = info.getCarInformation(getActivity());
                    storedCar.set_error(0);
                    car.set_error(0);
                    if (storedCar.equals(car)){
                        info.writeCarInformation(new Car("", "", "", 0), getActivity());
                    }
                    Context context = getActivity();
                    CharSequence text = getString(R.string.deleted_vehicle);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    carsArrayList.add(car);
                    carSelectionAdapter.notifyItemInserted(carsArrayList.size());
                    CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                    carSelectionAdapter.notifyDataSetChanged();

                    Context context = getActivity();
                    CharSequence text = getString(R.string.error_bad_communication);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            } else {
                carsArrayList.add(car);
                carSelectionAdapter.notifyItemInserted(carsArrayList.size());
                CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                carSelectionAdapter.notifyDataSetChanged();

                Context context = getActivity();
                CharSequence text = getString(R.string.error_bad_communication);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            Task = null;
            Context context = getActivity();
            CharSequence text = getString(R.string.operation_cancelled);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
