package com.app.heydriver.heydriver.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;

/**
 * Created by LAPGrock on 8/10/2017.
 */

public class HomeFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_activity_home));
        return view;
    }
}
