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
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.activities.HomeActivity;
import com.app.heydriver.heydriver.controller.activities.SignUpActivity;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;

/**
 * Created by LAPGrock on 8/10/2017.
 */

public class CarRegistrationFragment extends Fragment {

    private CarRegistrationFragment.UserAddCarTask Task = null;
    private View view;
    private EditText et_car_rg_serial;
    private EditText et_car_rg_brand;
    private EditText et_car_rg_model;
    private EditText et_car_rg_year;
    private View pb_add_car;
    private View sv_addcar_form;
    //private Spinner spinner;

    public static final String VALID_STRING_REGEX = "^((?=[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ])(?![_\\\\-]).)*$";
    public static final String VALID_SERIAL_REGEX = "^((?=[A-Za-z0-9])(?![_\\\\-]).)*$";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car_registration, container, false);
        ((HomeActivity) getActivity()).setActionBarTitle(getString(R.string.title_fragment_car_registration));
        et_car_rg_serial = (EditText) view.findViewById(R.id.et_car_rg_serial);
        et_car_rg_brand = (EditText) view.findViewById(R.id.et_car_rg_brand);
        et_car_rg_model = (EditText) view.findViewById(R.id.et_car_rg_model);
        et_car_rg_year = (EditText) view.findViewById(R.id.et_car_rg_year);
        pb_add_car = view.findViewById(R.id.pb_add_car);
        sv_addcar_form = view.findViewById(R.id.sv_addcar_form);

        Button btn_car_rg_add = (Button) view.findViewById(R.id.btn_car_rg_add);
        btn_car_rg_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAddCar();
            }
        });
        return view;
    }


    public static boolean validateStrings(String string) {
        return string.matches(VALID_STRING_REGEX);
    }


    public static boolean validateSerial(String string) {
        return string.matches(VALID_SERIAL_REGEX);
    }


    private void attemptAddCar() {
        if (Task != null) {
            return;
        }

        // Reset errors.
        et_car_rg_serial.setError(null);
        et_car_rg_brand.setError(null);
        et_car_rg_model.setError(null);
        et_car_rg_year.setError(null);

        // Store values at the time of the sign up attempt.
        String serial = et_car_rg_serial.getText().toString();
        String brand = et_car_rg_brand.getText().toString();
        String model = et_car_rg_model.getText().toString();
        String year = et_car_rg_year.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid name
        if (TextUtils.isEmpty(serial)) {
            et_car_rg_serial.setError(getString(R.string.error_field_required));
            focusView = et_car_rg_serial;
            cancel = true;
        }else if (!validateSerial(serial)) {
            et_car_rg_serial.setError(getString(R.string.error_invalid_space_char));
            focusView = et_car_rg_serial;
            cancel = true;
        }else if (serial.startsWith(" ")) {
            et_car_rg_serial.setError(getString(R.string.error_starts_with_space));
            focusView = et_car_rg_serial;
            cancel = true;
        } else if (serial.length() != 17){
            et_car_rg_serial.setError(getString(R.string.error_serial_lenght));
            focusView = et_car_rg_serial;
            cancel = true;
        }


        if (TextUtils.isEmpty(brand)) {
            et_car_rg_brand.setError(getString(R.string.error_field_required));
            focusView = et_car_rg_brand;
            cancel = true;
        }else if (!validateStrings(brand)) {
            et_car_rg_brand.setError(getString(R.string.error_special_char));
            focusView = et_car_rg_brand;
            cancel = true;
        }else if (brand.startsWith(" ")) {
            et_car_rg_brand.setError(getString(R.string.error_starts_with_space));
            focusView = et_car_rg_brand;
            cancel = true;
        }


        if (TextUtils.isEmpty(model)) {
            et_car_rg_model.setError(getString(R.string.error_field_required));
            focusView = et_car_rg_model;
            cancel = true;
        }else if (!validateStrings(model)) {
            et_car_rg_model.setError(getString(R.string.error_special_char));
            focusView = et_car_rg_model;
            cancel = true;
        }else if (model.startsWith(" ")) {
            et_car_rg_model.setError(getString(R.string.error_starts_with_space));
            focusView = et_car_rg_model;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(year)) {
            et_car_rg_year.setError(getString(R.string.error_field_required));
            focusView = et_car_rg_year;
            cancel = true;
        } else if (!validateStrings(year)) {
            et_car_rg_year.setError(getString(R.string.error_special_char));
            focusView = et_car_rg_year;
            cancel = true;
        }else if ((Integer.parseInt(year) < 1900) && (Integer.parseInt(year) > 2150)){
            et_car_rg_year.setError(getString(R.string.error_year_rank));
            focusView = et_car_rg_year;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            ManageInformation manageInformation = new ManageInformation();
            User user = manageInformation.getUserInformation(getActivity());
            Car car = new Car (serial.toUpperCase(), brand, model, Integer.parseInt(year));
            Task = new CarRegistrationFragment.UserAddCarTask(user.get_username(), car, this);
            Task.execute((Void) null);
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            sv_addcar_form.setVisibility(show ? View.GONE : View.VISIBLE);
            sv_addcar_form.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    sv_addcar_form.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            pb_add_car.setVisibility(show ? View.VISIBLE : View.GONE);
            pb_add_car.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    pb_add_car.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            pb_add_car.setVisibility(show ? View.VISIBLE : View.GONE);
            sv_addcar_form.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }



    public class UserAddCarTask extends AsyncTask<Void, Void, Boolean> {
        private final String user;
        private final Car carToAdd;
        private int response;
        private final CarRegistrationFragment actualFragment;

        UserAddCarTask(String _user, Car _car, CarRegistrationFragment _actualFragment) {
            this.user = _user;
            this.carToAdd = _car;
            this.actualFragment = _actualFragment;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Thread.sleep(2000);
                RestCommunication con = new RestCommunication();
                response = con.callMethodAddVehicle(user, carToAdd);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            Task = null;
            showProgress(false);

            if (success) {
                //finish();
                if (response == 200){
                    Context context = getActivity();
                    CharSequence text = getString(R.string.added_car_succesfully);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    getFragmentManager().popBackStack();
                }else if (response == 700){
                    Context context = getActivity();
                    CharSequence text = getString(R.string.error_duplicated_car);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else if (response == 500){
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
            showProgress(false);
            Context context = getActivity();
            CharSequence text = getString(R.string.operation_cancelled);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
