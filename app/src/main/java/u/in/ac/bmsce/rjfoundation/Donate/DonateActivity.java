package u.in.ac.bmsce.rjfoundation.Donate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONException;
import org.json.JSONObject;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;
import u.in.ac.bmsce.rjfoundation.Gallery.GalleryActivity;
import u.in.ac.bmsce.rjfoundation.HomeActivity;
import u.in.ac.bmsce.rjfoundation.News.NewsActivity;
import u.in.ac.bmsce.rjfoundation.Profile.AccountSettingsActivity;
import u.in.ac.bmsce.rjfoundation.Profile.DeveloperActivty;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.Utils.BottomNavigationViewHelper;

public class DonateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 2;

    private Context mContext = DonateActivity.this;
    private EditText input_pay;
    private String pay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_activty);
        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();


        //----------------------------------------Payment link ----------------------------------------------

        input_pay = findViewById(R.id.input_pay);
       pay = input_pay.getText().toString();
        Button btn_pay = findViewById(R.id.btn_pay);

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callInstamojoPay("naveekumarr97@gmail.com", "8073141525", pay, "Donation to RJ", "buyername");

            }
        });



        //-----------------------------------------Instamojo payment link--------------------------------------



        //--------------------------------------Navigation related code------------------------------------------------------------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Feeling Very Bad to do Alone, Every one left,But i never give up", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    //--------------------------Instmojo payment link-----------------------------------------

    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
            }
        };
    }

    /*
    ------------------------Navigation Bar Action methods----------------------------------------
     */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent intent = new Intent(this,AccountSettingsActivity.class) ;
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_gallery) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);



        } else if (id == R.id.nav_donation) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, DonateActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_ourwork) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_aboutus) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, DeveloperActivty.class);
            startActivity(intent);

        }else if (id == R.id.nav_partners) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_share) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, AccountSettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_developer) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, DeveloperActivty.class);
            startActivity(intent);

        }else if (id == R.id.nav_guid) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}