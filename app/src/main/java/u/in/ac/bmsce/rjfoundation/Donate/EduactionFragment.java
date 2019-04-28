package u.in.ac.bmsce.rjfoundation.Donate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import u.in.ac.bmsce.rjfoundation.DonorActivity;
import u.in.ac.bmsce.rjfoundation.HomeActivity;
import u.in.ac.bmsce.rjfoundation.News.NewsActivity;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.Sponsors.SponsorActivity;

public class EduactionFragment extends Fragment {

    private TextView tv_donate;
    GridLayout mg1, mg2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_education, container, false);

        mg1 = view.findViewById(R.id.gridl1);
        mg2 = view.findViewById(R.id.gridl2);

        clickdonateNow(mg1);
        clickSecondGrid(mg2);

//        tv_donate = (TextView) view.findViewById(R.id.tv_edonate);
//
//        tv_donate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DonorActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void clickSecondGrid(GridLayout mg2) {

        for (int i = 0; i < mg2.getChildCount(); i++) {


            CardView cv = (CardView) mg2.getChildAt(i);
            final int finali = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (finali == 0) {
                       // Log.d(getActivity(), "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                        Intent intent = new Intent(getActivity(), DonateStudyMaterial.class);
                        startActivity(intent);

                    } else if (finali == 1) {

                        //Log.d(getActivity(), "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                        Intent intent = new Intent(getActivity(), DonateAGirl.class);
                        startActivity(intent);

                    }
                    else if (finali == 2) {

                        //Log.d(getActivity(), "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                        Intent intent = new Intent(getActivity(), DonateOldStuff.class);
                        startActivity(intent);

                    }
                    else if (finali == 3) {

                        //Log.d(getActivity(), "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                        Intent intent = new Intent(getActivity(), DonateOnSpecialOccassion.class);
                        startActivity(intent);

                    }

                }
            });

        }

    }

    private void clickdonateNow(GridLayout mg1) {

        for (int i = 0; i < mg1.getChildCount(); i++) {


            CardView cv = (CardView) mg1.getChildAt(i);
            final int finali = i;
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   // Log.d(getActivity(), "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                    Intent intent = new Intent(getActivity(), DonorActivity.class);
                    startActivity(intent);

                }
            });

        }

    }

}
