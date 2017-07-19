package com.app.heydriver.heydriver.common.Util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by LuisAlejandro on 18-07-2017.
 */

public class LoginUtil {
    /**
     * Asi manejo la espera del usuario
     * @param context
     * @return el dialog para que el usuario tenga feedback de la espera
     */
    public static MaterialDialog getInstaceDialog(Context context){
        MaterialDialog instance = new MaterialDialog.Builder(context).
                content("Espere un momento").
                progress(true,0).
                progressIndeterminateStyle(false).
                autoDismiss(false).
                show();
        instance.setCancelable(false);
        instance.setCanceledOnTouchOutside(false);
        return instance;
    }
}
