package u.in.ac.bmsce.rjfoundation.Profile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionMenu;

import u.in.ac.bmsce.rjfoundation.R;

public class DeveloperActivty extends AppCompatActivity {

    private static final String TAG = "DevelopersActivity";

    //floating Action button
    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton fab_fb, fab_linkedin, fab_insta,fab_github, fab_web, fab_help;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_activty);
        init();

//
//// --------------------------------------Floating Action ------------------------------------------------------------------
//        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
//        fab_fb = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_facebook);
//        fab_linkedin = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_linkedin);
//        fab_insta= (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_instagram);
//        fab_github = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_github);
//        fab_web = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_web);
//        fab_help = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_help);
//
//        fab_fb.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu first item clicked
//                Intent facebookIntent = getOpenFacebookIntent(getApplicationContext());
//                startActivity(facebookIntent);
//
//            }
//        });
//        fab_linkedin.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//                Intent linkdinIntent = getOpenLinkdinIntent(getApplicationContext());
//                startActivity(linkdinIntent);
//
//
//            }
//        });
//        fab_insta.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                //TODO something when floating action menu second item clicked
//                Intent instagramIntent = getOpenInstagramIntent(getApplicationContext());
//                startActivity(instagramIntent);
//
//            }
//        });
//
//        fab_github.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu first item clicked
//                Intent googleplusIntent = getOpenGithubIntent(getApplicationContext());
//                startActivity(googleplusIntent);
//            }
//        });
//        fab_web.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//                Intent linkdinIntent = getOpenWebIntent(getApplicationContext());
//                startActivity(linkdinIntent);
//
//            }
//        });
//        fab_help.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//                Intent intent = new Intent(getApplicationContext(), AccountSettingsActivity.class);
//                startActivity(intent);
//            }
//        });
//



    }


    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        DeveloperFragment fragment = new DeveloperFragment();
        FragmentTransaction transaction = DeveloperActivty.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container1, fragment);
        transaction.addToBackStack(getString(R.string.Developer_fragment));
        transaction.commit();
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


}
