package com.app.heydriver.heydriver.controller.activities;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.app.heydriver.heydriver.R;
import com.app.heydriver.heydriver.common.Entities.Car;
import com.app.heydriver.heydriver.common.Entities.ControladorSQLite;
import com.app.heydriver.heydriver.common.Entities.User;
import com.app.heydriver.heydriver.controller.adapters.SynchronizingAdapter;
import com.app.heydriver.heydriver.controller.fragments.CarSelectionFragment;
import com.app.heydriver.heydriver.controller.fragments.HomeFragment;
import com.app.heydriver.heydriver.controller.fragments.ObdReaderFragment;
import com.app.heydriver.heydriver.model.ManageInformation;
import com.app.heydriver.heydriver.model.RestCommunication;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.app.heydriver.heydriver.common.FragmentSwap.changeFragment;
import static com.app.heydriver.heydriver.controller.activities.LauncherActivity.itemPositionStacks;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // UI references.
    private TextView mNameView;
    private TextView mMailView;
    private TextView mUserNameView;
    private Toolbar toolbar;
    private NavigationView navigationView;
    public static ControladorSQLite controladorSQLite;
    private HomeActivity.Synchronizing mSynchronizeTask = null;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ManageInformation storeinfo = new ManageInformation();
        User u = storeinfo.getUserInformation(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(controladorSQLite == null)
            controladorSQLite = new ControladorSQLite(this.getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        mNameView = (TextView) headerView.findViewById(R.id.tv_menu_name);
        mMailView = (TextView) headerView.findViewById(R.id.tv_menu_mail);
        mUserNameView = (TextView) headerView.findViewById(R.id.tv_menu_username);
        mNameView.setText(u.get_firstname() + " " + u.get_lastname());
        mUserNameView.setText(u.get_username());
        mMailView.setText(u.get_email());

        Menu menuNav = navigationView.getMenu();
        MenuItem it = menuNav.findItem(R.id.nav_home);
        it.setChecked(true);
        itemPositionStacks.add(R.id.nav_home);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame
                        , new HomeFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            int count = getFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getFragmentManager().popBackStack();
            }


            if(itemPositionStacks.size() > 1) {
                itemPositionStacks.remove(itemPositionStacks.size() - 1);
                int id = itemPositionStacks.get(itemPositionStacks.size() - 1);
                Menu menuNav = navigationView.getMenu();
                MenuItem it = menuNav.findItem(id);
                it.setChecked(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            try {
                startActivity(new Intent(this, ConfigActivity.class));
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*    public void changeFragment(Fragment newFragment, int id, String tag){
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.animator.slide_in_up, R.animator.slide_out_up, R.animator.slide_out_down, R.animator.slide_in_down)
                .replace(R.id.content_frame
                        , newFragment)
                .addToBackStack(tag)
                .commit();
        itemPositionStacks.add(id);
    }
*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        int previousId = itemPositionStacks.get(itemPositionStacks.size() - 1);


        FragmentManager fragmentManager = getFragmentManager();
        if (id != previousId) {
            if (id == R.id.nav_home) {
                changeFragment(R.id.content_frame, fragmentManager, new HomeFragment(), id, "home");
            } else if (id == R.id.nav_carregistration) {
                changeFragment(R.id.content_frame, fragmentManager, new CarSelectionFragment(), id, "car_selection");
            } else if (id == R.id.nav_gallery) {

            } else if (id == R.id.nav_slideshow) {

            } else if (id == R.id.nav_obdIIscanning)
            {
                ManageInformation info = new ManageInformation();
                Car selectedCar = info.getCarInformation(getApplicationContext());
                if ((selectedCar.get_model() != null) && (selectedCar.get_brand() != null) && (selectedCar.get_serial() != null)) {
                    changeFragment(R.id.content_frame, fragmentManager, new ObdReaderFragment(), id, "reader");
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.error_not_selected_car),Toast.LENGTH_LONG).show();
                }
            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {
                mSynchronizeTask = new Synchronizing();
                mSynchronizeTask.execute((Void) null);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setActionBarTitle(String title){
        toolbar.setTitle(title);
    }

    private class Synchronizing extends AsyncTask<Void, Void, Boolean> {
        private ManageInformation info = new ManageInformation();
        private ProgressDialog dialog = new ProgressDialog(HomeActivity.this);
        private boolean response;
        SynchronizingAdapter sa = new SynchronizingAdapter();

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                RestCommunication con = new RestCommunication();
                response = con.callMethodSynchronization(sa.syncData(getApplicationContext()));
                if (response == true) {
                    return true;
                }
                else return false;
            } catch (Exception e) {
                Toast toast = Toast.makeText(getApplicationContext(), R.string.no_data, Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                if (response == true) {
                    CharSequence text = getString(R.string.sincronized_data);
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }
            else
            {
                if (response==false)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.no_data, Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.error_bad_communication, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}
