package u.in.ac.bmsce.rjfoundation.Volunteer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import u.in.ac.bmsce.rjfoundation.Firebase.FirebaseMethods;
import u.in.ac.bmsce.rjfoundation.MainActivity;
import u.in.ac.bmsce.rjfoundation.R;

public class VounteerFriendsFragment extends Fragment {



    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String name,age,profession,hqualification,contact,email,hours;
    private FirebaseMethods firebaseMethods;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_volunteer_friends, container, false);


        Spinner mySpinner = (Spinner) view.findViewById(R.id.spin_hours);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        final EditText myName = (EditText)view.findViewById(R.id.input_name);
        final EditText myAge = (EditText)view.findViewById(R.id.input_age);
        final EditText myProfession = (EditText)view.findViewById(R.id.input_profession);
        final EditText myHqualification = (EditText)view.findViewById(R.id.input_Hqualification);
        final EditText myContact = (EditText)view.findViewById(R.id.input_contact);
        final EditText myEmail = (EditText)view.findViewById(R.id.input_email);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hours = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



//        ----------------------Dialogue box of days-------------------------------------

        mItemSelected = (TextView) view.findViewById(R.id.tvItemSelected);

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

        Button myClickHere = view.findViewById(R.id.button);
        myClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    name = myName.getText().toString().trim();
                    age = myAge.getText().toString().trim();
                    profession = myProfession.getText().toString().trim();
                    contact = myContact.getText().toString().trim();
                    hqualification = myHqualification.getText().toString().trim();
                    email = myEmail.getText().toString().trim();

                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
                        firebaseMethods.addFriends(name,age,profession,hqualification,hours,contact,email,mUserItems);
                        Toast.makeText(view.getContext(),"Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(view.getContext(),"Please enter all the information",Toast.LENGTH_SHORT).show();
                    }

            }
        });

        return view;
    }

    }
