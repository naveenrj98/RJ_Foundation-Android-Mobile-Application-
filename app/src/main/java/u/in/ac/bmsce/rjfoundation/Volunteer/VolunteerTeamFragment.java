package u.in.ac.bmsce.rjfoundation.Volunteer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import u.in.ac.bmsce.rjfoundation.R;

public class VolunteerTeamFragment extends Fragment {


    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String name,age,profession,hqualification,contact,email,hours;
    Button submit;


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

                showdialog();

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

    private void showdialog() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Register Here");

        LayoutInflater inflater = this.getLayoutInflater();
        View add_menu_layout = inflater.inflate(R.layout.register_volunteer_teach, null);

        final EditText myName = (EditText)add_menu_layout.findViewById(R.id.input_name);
        final EditText myAge = (EditText)add_menu_layout.findViewById(R.id.input_age);
        final EditText myProfession = (EditText)add_menu_layout.findViewById(R.id.input_profession);
        final EditText myHqualification = (EditText)add_menu_layout.findViewById(R.id.input_Hqualification);
        final EditText myContact = (EditText)add_menu_layout.findViewById(R.id.input_contact);
        final EditText myEmail = (EditText)add_menu_layout.findViewById(R.id.input_email);



        Spinner mySpinner = (Spinner) add_menu_layout.findViewById(R.id.spin_hours);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);



        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hours = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        mItemSelected = (TextView) add_menu_layout.findViewById(R.id.tvItemSelected);

        listItems = getResources().getStringArray(R.array.days_of_volunteer_itmes);
        checkedItems = new boolean[listItems.length];

        mItemSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Days of Volunteer");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
//                        if (isChecked) {
//                            if (!mUserItems.contains(position)) {
//                                mUserItems.add(position);
//                            }
//                        } else if (mUserItems.contains(position)) {
//                            mUserItems.remove(position);
//                        }
                        if(isChecked){
                            mUserItems.add(position);
                        }else{
                            mUserItems.remove((Integer.valueOf(position)));
                            mItemSelected.setText("Select Days of Volunteer");
                        }
                    }
                });

                mBuilder.setCancelable(true);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++) {
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        mItemSelected.setText(item);
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        mItemSelected.setText("Select Days of Volunteer");
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });




//        editName = add_menu_layout.findViewById(R.id.editName);
//        edtDescription = add_menu_layout.findViewById(R.id.editDescription);
//        edtPrice = add_menu_layout.findViewById(R.id.editPrice);
//        edtDiscount = add_menu_layout.findViewById(R.id.editDiscount);
//





        alertDialog.setView(add_menu_layout);
      //  alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();


            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        alertDialog.show();



    }
}
