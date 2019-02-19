package u.in.ac.bmsce.rjfoundation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import u.in.ac.bmsce.rjfoundation.AboutUs.AboutUsActivity;
import u.in.ac.bmsce.rjfoundation.Donate.DonateActivity;
import u.in.ac.bmsce.rjfoundation.Volunteer.VolunteerActivity;
import u.in.ac.bmsce.rjfoundation.Profile.DeveloperActivty;
import u.in.ac.bmsce.rjfoundation.News.NewsActivity;
import u.in.ac.bmsce.rjfoundation.Profile.AccountSettingsActivity;
import u.in.ac.bmsce.rjfoundation.Profile.ProfileActivity;
import u.in.ac.bmsce.rjfoundation.Sponsors.SponsorActivity;
import u.in.ac.bmsce.rjfoundation.Users.LoginActivity;
import u.in.ac.bmsce.rjfoundation.Utils.BottomNavigationViewHelper;
import u.in.ac.bmsce.rjfoundation.Utils.UniversalImageLoader;

public class HomeActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;

    //Widgets
    private TextView tv_donate, tv_volunteer, tv_todaysevent, tv_sponsor, tv_profile, tv_developers,tv_abotus;

    //context
    private Context mContext = HomeActivity.this;

    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //floating Action button
    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton fab_fb, fab_linkedin, fab_insta,fab_github, fab_web, fab_help;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting.");

        tv_donate = findViewById(R.id.tv_donate);
        tv_volunteer = findViewById(R.id.tv_volunteer);
        tv_todaysevent = findViewById(R.id.tv_todayevent);
        tv_sponsor = findViewById(R.id.tv_sponsor);
        tv_profile = findViewById(R.id.tv_profile);
        tv_developers = findViewById(R.id.tv_developers);
        tv_abotus = findViewById(R.id.tv_aboutus);
        setupFirebaseAuth();
        initImageLoader();
        setupBottomNavigationView();


// --------------------------------------Floating Action ------------------------------------------------------------------
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        fab_fb = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_facebook);
        fab_linkedin = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_linkedin);
        fab_insta= (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_instagram);
        fab_github = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_github);
        fab_web = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_web);
        fab_help = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_help);

        fab_fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(getApplicationContext());
                startActivity(facebookIntent);

            }
        });
        fab_linkedin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent linkdinIntent = getOpenLinkdinIntent(getApplicationContext());
                startActivity(linkdinIntent);


            }
        });
        fab_insta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(getApplicationContext());
                startActivity(instagramIntent);

            }
        });

        fab_github.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent googleplusIntent = getOpenGithubIntent(getApplicationContext());
                startActivity(googleplusIntent);
            }
        });
        fab_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent linkdinIntent = getOpenWebIntent(getApplicationContext());
                startActivity(linkdinIntent);

            }
        });
        fab_help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent intent = new Intent(getApplicationContext(), AccountSettingsActivity.class);
                startActivity(intent);
            }
        });

 //--------------------------------------Navigation related code------------------------------------------------------------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Feeling Very Bad to do Alone, Every one left,But i never give up", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//-----------------------------------------Onclick Listeners-----------------------------------------------------------------
        tv_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, DonateActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
            }
        });
        tv_sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, SponsorActivity.class);
                startActivity(intent);
            }
        });
        tv_volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, VolunteerActivity.class);
                startActivity(intent);
            }
        });
        tv_todaysevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        tv_developers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, DeveloperActivty.class);
                startActivity(intent);
            }
        });
        tv_abotus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(HomeActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });




    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
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




///-------------------------------------------Floating Action button methods for intent------------
public static Intent getOpenFacebookIntent(Context context) {

    try {
        context.getPackageManager()
                .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
        return new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/profile.php?id=100007105339078")); //Trys to make intent with FB's URI
    } catch (Exception e) {
        return new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/profile.php?id=100007105339078")); //catches and opens a url to the desired page
    }
}



    public static Intent getOpenLinkdinIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.linkedin.android", 0); //Checks if Linkdin is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/naveen-r-817234130/")); //Trys to make intent with Linkdin's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/naveen-r-817234130/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenGithubIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.github.android", 0); //Checks if G+ is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/naveenrj98/RJ_Foundation")); //Trys to make intent with G+'s URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/naveenrj98/RJ_Foundation")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenWebIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube", 0); //Checks if YT is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://sites.google.com/view/rj-foundation/projects/android-app?authuser=0")); //Trys to make intent with YT's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://sites.google.com/view/rj-foundation/projects/android-app?authuser=0")); //catches and opens a url to the desired page
        }
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
            Intent intent = new Intent(this, VolunteerActivity.class);
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
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_partners) {
            Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
            Intent intent = new Intent(this, SponsorActivity.class);
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


/*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * checks to see if the @param 'user' is logged in
     * @param user
     */
    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        if(user == null){
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
        }
    }
    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //check if the user is logged in
                checkCurrentUser(user);

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
