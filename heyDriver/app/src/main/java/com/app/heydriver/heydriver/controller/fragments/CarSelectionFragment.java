package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.adapters.CarSelectionAdapter;
import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;

/**
 * Created by LAPGrock on 8/10/2017.
 */

public class CarSelectionFragment extends Fragment {

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

        /*Context context = super.getActivity();
        CharSequence text = getString(R.string.error_bad_communication);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

    }



    private void manageRecyclerView(){

        try {
            carsArrayList.clear();
            Car car1 = new Car("4564546", "Mazda", "1");
            Car car2 = new Car("4564541", "Mazda", "2");
            Car car3 = new Car("4564542", "Mazda", "3");
            Car car4 = new Car("4564543", "Mazda", "4");
            Car car5 = new Car("4564544", "Mazda", "5");
            Car car6 = new Car("4564545", "Mazda", "6");
            Car car7 = new Car("4564547", "Mazda", "7");

            carsArrayList.add(car1);
            carsArrayList.add(car2);
            carsArrayList.add(car3);
            carsArrayList.add(car4);
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
                                        carsArrayList.remove(position);
                                        carSelectionAdapter.notifyItemRemoved(position);
                                        carSelectionAdapter.removeFromHoldersList(position);
                                    }
                                    CarSelectionAdapter.holdersList = new ArrayList<CarSelectionAdapter.ViewHolder>();
                                    carSelectionAdapter.notifyDataSetChanged();
                                    Context context = getActivity();
                                    CharSequence text = getString(R.string.deleted_vehicle);
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
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
                                    Context context = getActivity();
                                    CharSequence text = getString(R.string.deleted_vehicle);
                                    int duration = Toast.LENGTH_SHORT;
                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            });

            rv_vehicle_list.addOnItemTouchListener(swipeTouchListener);
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
}
