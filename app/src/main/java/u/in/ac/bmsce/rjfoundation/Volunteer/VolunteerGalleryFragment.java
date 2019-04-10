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
        et_username = view.findViewById(R.id.tusername);
        et_age = view.findViewById(R.id.age);
        et_days = view.findViewById(R.id.days);
        et_hours = view.findViewById(R.id.hours);
        et_profession = view.findViewById(R.id.profession);
        et_qualification = view.findViewById(R.id.qaulification);
        btn_submit =  view.findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tusername =  et_username.getText().toString();
                age =  et_age.getText().toString();
                days =  et_days.getText().toString();
                hours =  et_hours.getText().toString();
                qualification =  et_qualification.getText().toString();
                profession =  et_profession.getText().toString();

                //add new user to the database
                firebaseMethods.addTeaching(tusername,age,profession,qualification,days,hours);

                Toast.makeText(mContext, "Signup successful. Sending verification email.", Toast.LENGTH_SHORT).show();

            }
        });





        return view;
    }


}
