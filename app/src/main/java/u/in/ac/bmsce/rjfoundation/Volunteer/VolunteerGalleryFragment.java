package u.in.ac.bmsce.rjfoundation.Volunteer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import u.in.ac.bmsce.rjfoundation.Firebase.FirebaseMethods;
import u.in.ac.bmsce.rjfoundation.R;

public class VolunteerGalleryFragment extends Fragment {


    private String tusername;
    private String age;



    private String days;
    private String hours;
    private String qualification;
    private String profession;

    EditText et_username, et_age, et_days, et_hours, et_profession, et_qualification;
    Button btn_submit;
    private Context mContext;
    private FirebaseMethods firebaseMethods;

    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_volunteer_galler, container, false);


        return view;
    }


}
