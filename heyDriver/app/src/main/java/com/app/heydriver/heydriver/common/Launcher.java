package com.app.heydriver.heydriver.common;

import android.app.Application;

import com.app.heydriver.heydriver.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by LuisAlejandro on 16-07-2017.
 */

public class Launcher extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Raleway/Raleway-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //FontsOverride.setDefaultFont(this, "DEFAULT", "raleway_regular.ttf");
        //  This FontsOverride comes from the example I posted above
    }
}
