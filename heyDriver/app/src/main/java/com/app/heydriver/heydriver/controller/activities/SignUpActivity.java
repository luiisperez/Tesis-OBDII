package com.app.heydriver.heydriver.controller.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * A login screen that offers login via email/password.
 */
public class SignUpActivity extends AppCompatActivity {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private SignUpActivity.UserSignUpTask mAuthTask = null;

    // UI references.
    private EditText mFirstNameView;
    private EditText mLastNameView;
    private EditText mEmailView;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mConfirmPwdView;
    private View mProgressView;
    private View mSignUpFormView;


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final String VALID_NAME_REGEX = "^((?=[A-Za-z0-9ñÑáéíóúÁÉÍÓÚ ])(?![_\\\\-]).)*$";

    public static final String VALID_USERNAME_PASSWORD_REGEX = "^((?=[A-Za-z0-9@._-])(?![_\\\\-]).)*$";

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateName(String name) {
        return name.matches(VALID_NAME_REGEX);
    }

    public static boolean validateUsernameOrPassword(String string) {
        return string.matches(VALID_USERNAME_PASSWORD_REGEX);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        Intent myintent = new Intent(SignUpActivity.this, StartUpActivity.class);
        finish();
        startActivity(myintent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Set up the login form.
        mFirstNameView = (EditText) findViewById(R.id.et_rg_firstname);
        mLastNameView = (EditText) findViewById(R.id.et_rg_lastname);
        mEmailView = (EditText) findViewById(R.id.et_rg_email);
        mUsernameView = (EditText) findViewById(R.id.et_rg_user);

        mPasswordView = (EditText) findViewById(R.id.et_rg_password);
        mConfirmPwdView = (EditText) findViewById(R.id.et_rg_confirmpwd);

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_rg_sign_in);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });

        mSignUpFormView = findViewById(R.id.signup_form);
        mProgressView = findViewById(R.id.signup_progress);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Typeface ralewayBoldFont = Typeface.createFromAsset(getAssets(),"fonts/Raleway/Raleway-Bold.ttf");
        mEmailSignInButton.setTypeface(ralewayBoldFont);

        Typeface typeface = mPasswordView.getTypeface();
        mPasswordView.setTransformationMethod(new PasswordTransformationMethod());
        mPasswordView.setTypeface(typeface);

        typeface = mConfirmPwdView.getTypeface();
        mConfirmPwdView.setTransformationMethod(new PasswordTransformationMethod());
        mConfirmPwdView.setTypeface(typeface);
    }


    private void attemptSignUp() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mUsernameView.setError(null);
        mFirstNameView.setError(null);
        mLastNameView.setError(null);
        mConfirmPwdView.setError(null);

        // Store values at the time of the sign up attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String username = mUsernameView.getText().toString();
        String firstname = mFirstNameView.getText().toString();
        String lastname = mLastNameView.getText().toString();
        String pwdconfirmation = mConfirmPwdView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid name
        if (TextUtils.isEmpty(firstname)) {
            mFirstNameView.setError(getString(R.string.error_field_required));
            focusView = mFirstNameView;
            cancel = true;
        }else if (!validateName(firstname)) {
            mFirstNameView.setError(getString(R.string.error_special_char));
            focusView = mFirstNameView;
            cancel = true;
        }else if (firstname.startsWith(" ")) {
            mFirstNameView.setError(getString(R.string.error_starts_with_space));
            focusView = mFirstNameView;
            cancel = true;
        }
        if (TextUtils.isEmpty(lastname)) {
            mLastNameView.setError(getString(R.string.error_field_required));
            focusView = mLastNameView;
            cancel = true;
        }else if (!validateName(lastname)) {
            mLastNameView.setError(getString(R.string.error_special_char));
            focusView = mLastNameView;
            cancel = true;
        }else if (lastname.startsWith(" ")) {
            mLastNameView.setError(getString(R.string.error_starts_with_space));
            focusView = mLastNameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        // Check for a valid username.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!validateUsernameOrPassword(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }else if (!validateUsernameOrPassword(password)) {
            mPasswordView.setError(getString(R.string.error_characters_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid password
        if (TextUtils.isEmpty(pwdconfirmation)) {
            mConfirmPwdView.setError(getString(R.string.error_field_required));
            focusView = mConfirmPwdView;
            cancel = true;
        }else if (!isPasswordValid(pwdconfirmation)) {
            mConfirmPwdView.setError(getString(R.string.error_invalid_password));
            focusView = mConfirmPwdView;
            cancel = true;
        }else if (!validateUsernameOrPassword(password)) {
            mPasswordView.setError(getString(R.string.error_characters_password));
            focusView = mPasswordView;
            cancel = true;
        }else if (!pwdconfirmation.equals(password)) {
            mConfirmPwdView.setError(getString(R.string.error_matching_passwords));
            focusView = mConfirmPwdView;
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
            mAuthTask = new SignUpActivity.UserSignUpTask(firstname, lastname, email, username, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return validateEmail(email);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mSignUpFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mSignUpFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */
    public class UserSignUpTask extends AsyncTask<Void, Void, Boolean> {
        private final User userToRegister;
        private User response;

        UserSignUpTask(String firstname, String lastname, String email, String username, String password) {
            userToRegister = new User(username, password, email, firstname, lastname);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodSignUpUser(userToRegister);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                //finish();
                if (response != null){
                    ManageInformation storeinfo = new ManageInformation();
                    storeinfo.writeUserInformation(response, getApplicationContext());
                    User u = storeinfo.getUserInformation(getApplicationContext());
                    Intent myintent = new Intent(SignUpActivity.this, HomeActivity.class);
                    finish();
                    startActivity(myintent);
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.welcome_message);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = getString(R.string.error_cant_signup);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            } else {
                Context context = getApplicationContext();
                CharSequence text = getString(R.string.error_bad_communication);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.operation_cancelled);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

