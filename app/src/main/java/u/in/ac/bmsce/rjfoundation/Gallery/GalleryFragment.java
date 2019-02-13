package u.in.ac.bmsce.rjfoundation.Gallery;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import u.in.ac.bmsce.rjfoundation.R;

public class GalleryFragment extends Fragment {


    @Nullable

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_gallery, container, false);


        return view;
    }


}
