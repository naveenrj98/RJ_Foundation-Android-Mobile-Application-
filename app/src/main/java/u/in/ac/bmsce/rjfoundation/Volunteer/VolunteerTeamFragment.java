package u.in.ac.bmsce.rjfoundation.Volunteer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import u.in.ac.bmsce.rjfoundation.R;

public class VolunteerTeamFragment extends Fragment {


    private ImageView iv_teach, iv_vt, iv_fundrais, iv_awareness;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_volunteer_team, container, false);

        iv_awareness= view.findViewById(R.id.iv_awareness);
        iv_fundrais = view.findViewById(R.id.iv_fundraising);
        iv_teach = view.findViewById(R.id.iv_teach);
        iv_vt = view.findViewById(R.id.iv_vt);


        iv_teach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_fundrais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_awareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
