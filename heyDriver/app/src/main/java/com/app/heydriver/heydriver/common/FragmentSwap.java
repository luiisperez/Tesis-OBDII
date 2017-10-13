package com.app.heydriver.heydriver.common;

import android.app.Fragment;
import android.app.FragmentManager;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;


import com.app.heydriver.heydriver.R;

import roboguice.fragment.RoboFragment;

import static com.app.heydriver.heydriver.controller.activities.LauncherActivity.itemPositionStacks;

/**
 * Created by LAPGrock on 8/14/2017.
 */

public class FragmentSwap {

    public static void changeFragment(int idcontent, android.app.FragmentManager _fragmentManager, android.app.Fragment newFragment, int id, String tag){
        android.app.FragmentManager fragmentManager = _fragmentManager;

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_out_down, R.animator.slide_in_down)
                .replace(idcontent
                        , newFragment)
                .addToBackStack(tag)
                .commit();
        itemPositionStacks.add(id);
    }

}
