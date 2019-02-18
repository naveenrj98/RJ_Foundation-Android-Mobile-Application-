package u.in.ac.bmsce.rjfoundation.Sponsors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import u.in.ac.bmsce.rjfoundation.AboutUs.AboutFounderProfileFragment;
import u.in.ac.bmsce.rjfoundation.AboutUs.AboutUsActivity;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.Utils.BottomNavigationViewHelper;
import u.in.ac.bmsce.rjfoundation.Utils.SectionsStatePagerAdapter;

public class SponsorActivity extends AppCompatActivity {

    private static final String TAG = "SponsorActivity";
    private static final int ACTIVITY_NUM = 4;




    private Context mContext;
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);
        mContext = SponsorActivity.this;
        Log.d(TAG, "onCreate: started.");
        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);

        setupSettingsList();
        setupBottomNavigationView();
        setupFragments();
        getIncomingIntent();

//        //setup the backarrow for navigating back to "ProfileActivity"
//        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
//        backArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
//                finish();
//            }
//        });
    }

    private void getIncomingIntent(){
        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.calling_activity))){
            Log.d(TAG, "getIncomingIntent: received incoming intent from " + getString(R.string.profile_activity));
            setViewPager(pagerAdapter.getFragmentNumber(getString(R.string.edit_profile_fragment)));
        }
    }

    private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new SponsorTrusteeFragment(), "Our Trustees"); //fragment 0

        pagerAdapter.addFragment(new SponsorMemberFragment(), "Our Members"); //fragment 1
        pagerAdapter.addFragment(new SponsorVolunteerFragment(), "Volunteers"); //fragment 2
        pagerAdapter.addFragment(new SponsorCorporateFragment(), "Corporate"); //fragment 3
        pagerAdapter.addFragment(new SponsorInstitutionFragment(), "Institution"); //fragment 4
        pagerAdapter.addFragment(new SponsorOrganisationFragment(), "Organisation"); //fragment 5


    }

    private void setViewPager(int fragmentNumber){
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list.");
        ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<>();
        options.add("                  Our Trustees"); //fragment 0


        options.add("                  Our Members"); //fragement 1
        options.add("                   Volunteers"); //fragement 2
        options.add("                    Corporate"); //fragement 3
        options.add("                   Institution"); //fragement 4
        options.add("                 Organisation"); //fragement 5




        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_activated_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: navigating to fragment#: " + position);
                setViewPager(position);
            }
        });

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