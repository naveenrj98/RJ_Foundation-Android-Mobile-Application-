package u.in.ac.bmsce.rjfoundation.Donate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import u.in.ac.bmsce.rjfoundation.DonorActivity;
import u.in.ac.bmsce.rjfoundation.R;

public class EduactionFragment extends Fragment {

    private TextView tv_donate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_education, container, false);

        tv_donate = (TextView) view.findViewById(R.id.tv_edonate);

        tv_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DonorActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    }
