package com.app.heydriver.heydriver.common;

import android.app.Fragment;
import android.app.FragmentManager;

import com.app.heydriver.heydriver.R;

import static com.app.heydriver.heydriver.controller.activities.LauncherActivity.itemPositionStacks;

/**
 * Created by LAPGrock on 8/14/2017.
 */

public class FragmentSwap {

    public static void changeFragment(int idcontent, FragmentManager _fragmentManager, Fragment newFragment, int id, String tag){
        FragmentManager fragmentManager = _fragmentManager;
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_out_down, R.animator.slide_in_down)
                .replace(idcontent
                        , newFragment)
                .addToBackStack(tag)
                .commit();
        itemPositionStacks.add(id);
    }

}
