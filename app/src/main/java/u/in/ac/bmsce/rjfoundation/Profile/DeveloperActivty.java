package u.in.ac.bmsce.rjfoundation.Profile;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import u.in.ac.bmsce.rjfoundation.R;

public class DeveloperActivty extends AppCompatActivity {

    private static final String TAG = "DevelopersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_activty);
        init();




    }


    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        DeveloperFragment fragment = new DeveloperFragment();
        FragmentTransaction transaction = DeveloperActivty.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container1, fragment);
        transaction.addToBackStack(getString(R.string.Developer_fragment));
        transaction.commit();
    }
}
