package u.in.ac.bmsce.rjfoundation.Volunteer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

import u.in.ac.bmsce.rjfoundation.Donate.DonateAGirl;
import u.in.ac.bmsce.rjfoundation.Donate.DonateOldStuff;
import u.in.ac.bmsce.rjfoundation.Donate.DonateOnSpecialOccassion;
import u.in.ac.bmsce.rjfoundation.Donate.DonateStudyMaterial;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.models.TeachInformation;

import static android.support.constraint.Constraints.TAG;

public class VolunteerTeamFragment extends Fragment {

    GridLayout mg1, mg2;
    CardView cv1,cv2,cv3,cv4,cv5;

    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();
    String name,age,profession,hqualification,contact,email,hours;
    Button submit;


    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;



    private ImageView iv_teach, iv_vt, iv_fundrais, iv_awareness;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_volunteer_team, container, false);

//        iv_awareness= view.findViewById(R.id.iv_awareness);
//        iv_fundrais = view.findViewById(R.id.iv_fundraising);
//        iv_teach = view.findViewById(R.id.iv_teach);
//        iv_vt = view.findViewById(R.id.iv_vt);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        mg2 = view.findViewById(R.id.gridl2);
        cv1 = view.findViewById(R.id.vcv1);
        cv2 = view.findViewById(R.id.vcv2);
        cv3 = view.findViewById(R.id.vcv3);
        cv4 = view.findViewById(R.id.vcv4);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_fall_down);
        cv1.startAnimation(animation);
        Animation b = AnimationUtils.loadAnimation(getActivity(), R.anim.swing_up_right);
        cv2.startAnimation(b);
        Animation c = AnimationUtils.loadAnimation(getActivity(), R.anim.swing_up_left);
        cv3.startAnimation(c);
        Animation d = AnimationUtils.loadAnimation(getActivity(), R.anim.tem_animation_from_bottom);
        cv4.startAnimation(d);

        clickSecondGrid(mg2);


        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdialog();

            }
        });


        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialoga();
            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialogb();
            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showdialogc();

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


                Log.d(TAG, "onClick: Submit pressed.");
                name = myName.getText().toString().trim();
                age = myAge.getText().toString().trim();
                profession = myProfession.getText().toString().trim();
                contact = myContact.getText().toString().trim();
                hqualification = myHqualification.getText().toString().trim();
                email = myEmail.getText().toString().trim();
                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "phone number: " + myEmail + "\n"
                );
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                //handle the exception if the EditText fields are null
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
                    TeachInformation teachinginformation = new TeachInformation(mUserItems,name,age,profession,hqualification,contact,email,hours);
                    Random rand = new Random();

// Obtain a number between [0 - 49].
                    int n = rand.nextInt(50);

// Add 1 to the result to get a number from the required range
// (i.e., [1 - 50]).
                    n += 1;



                    String i ="teach"+n;
                    myRef.child("teaching_details").child(userID).setValue(teachinginformation);

                    toastMessage("Congrats !!! your have succefully registered");
                    myName.setText("");
                    myProfession.setText("");
                    myAge.setText("");
                    myContact.setText("");
                    myHqualification.setText("");
                    myEmail.setText("");
                }else {
                    toastMessage("Fill out all the fields");
                }
//                    name = myName.getText().toString().trim();
//                    age = myAge.getText().toString().trim();
//                    profession = myProfession.getText().toString().trim();
//                    contact = myContact.getText().toString().trim();
//                    hqualification = myHqualification.getText().toString().trim();
//                    email = myEmail.getText().toString().trim();
//
//                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
//                        firebaseMethods.addFriends(name,age,profession,hqualification,hours,contact,email,mUserItems);
//                        Toast.makeText(view.getContext(),"Successful",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(view.getContext(),"Please enter all the information",Toast.LENGTH_SHORT).show();
//                    }
















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

    private void showdialoga() {

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


                Log.d(TAG, "onClick: Submit pressed.");
                name = myName.getText().toString().trim();
                age = myAge.getText().toString().trim();
                profession = myProfession.getText().toString().trim();
                contact = myContact.getText().toString().trim();
                hqualification = myHqualification.getText().toString().trim();
                email = myEmail.getText().toString().trim();
                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "phone number: " + myEmail + "\n"
                );
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                //handle the exception if the EditText fields are null
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
                    TeachInformation teachinginformation = new TeachInformation(mUserItems,name,age,profession,hqualification,contact,email,hours);
                    Random rand = new Random();

// Obtain a number between [0 - 49].
                    int n = rand.nextInt(50);

// Add 1 to the result to get a number from the required range
// (i.e., [1 - 50]).
                    n += 1;



                    String i ="teach"+n;
                    myRef.child("vocational_training").child(userID).setValue(teachinginformation);

                    toastMessage("Congrats !!! your have succefully registered");
                    myName.setText("");
                    myProfession.setText("");
                    myAge.setText("");
                    myContact.setText("");
                    myHqualification.setText("");
                    myEmail.setText("");
                }else {
                    toastMessage("Fill out all the fields");
                }
//                    name = myName.getText().toString().trim();
//                    age = myAge.getText().toString().trim();
//                    profession = myProfession.getText().toString().trim();
//                    contact = myContact.getText().toString().trim();
//                    hqualification = myHqualification.getText().toString().trim();
//                    email = myEmail.getText().toString().trim();
//
//                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
//                        firebaseMethods.addFriends(name,age,profession,hqualification,hours,contact,email,mUserItems);
//                        Toast.makeText(view.getContext(),"Successful",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(view.getContext(),"Please enter all the information",Toast.LENGTH_SHORT).show();
//                    }
















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
    private void showdialogb() {

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


                Log.d(TAG, "onClick: Submit pressed.");
                name = myName.getText().toString().trim();
                age = myAge.getText().toString().trim();
                profession = myProfession.getText().toString().trim();
                contact = myContact.getText().toString().trim();
                hqualification = myHqualification.getText().toString().trim();
                email = myEmail.getText().toString().trim();
                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "phone number: " + myEmail + "\n"
                );
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                //handle the exception if the EditText fields are null
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
                    TeachInformation teachinginformation = new TeachInformation(mUserItems,name,age,profession,hqualification,contact,email,hours);
                    Random rand = new Random();

// Obtain a number between [0 - 49].
                    int n = rand.nextInt(50);

// Add 1 to the result to get a number from the required range
// (i.e., [1 - 50]).
                    n += 1;



                    String i ="teach"+n;
                    myRef.child("fund_raising").child(userID).setValue(teachinginformation);

                    toastMessage("Congrats !!! your have succefully registered");
                    myName.setText("");
                    myProfession.setText("");
                    myAge.setText("");
                    myContact.setText("");
                    myHqualification.setText("");
                    myEmail.setText("");
                }else {
                    toastMessage("Fill out all the fields");
                }
//                    name = myName.getText().toString().trim();
//                    age = myAge.getText().toString().trim();
//                    profession = myProfession.getText().toString().trim();
//                    contact = myContact.getText().toString().trim();
//                    hqualification = myHqualification.getText().toString().trim();
//                    email = myEmail.getText().toString().trim();
//
//                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
//                        firebaseMethods.addFriends(name,age,profession,hqualification,hours,contact,email,mUserItems);
//                        Toast.makeText(view.getContext(),"Successful",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(view.getContext(),"Please enter all the information",Toast.LENGTH_SHORT).show();
//                    }
















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
    private void showdialogc() {

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


                Log.d(TAG, "onClick: Submit pressed.");
                name = myName.getText().toString().trim();
                age = myAge.getText().toString().trim();
                profession = myProfession.getText().toString().trim();
                contact = myContact.getText().toString().trim();
                hqualification = myHqualification.getText().toString().trim();
                email = myEmail.getText().toString().trim();
                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "phone number: " + myEmail + "\n"
                );
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                //handle the exception if the EditText fields are null
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
                    TeachInformation teachinginformation = new TeachInformation(mUserItems,name,age,profession,hqualification,contact,email,hours);
                    Random rand = new Random();

// Obtain a number between [0 - 49].
                    int n = rand.nextInt(50);

// Add 1 to the result to get a number from the required range
// (i.e., [1 - 50]).
                    n += 1;



                    String i ="teach"+n;
                    myRef.child("Awareness").child(userID).setValue(teachinginformation);

                    toastMessage("Congrats !!! your have succefully registered");
                    myName.setText("");
                    myProfession.setText("");
                    myAge.setText("");
                    myContact.setText("");
                    myHqualification.setText("");
                    myEmail.setText("");
                }else {
                    toastMessage("Fill out all the fields");
                }
//                    name = myName.getText().toString().trim();
//                    age = myAge.getText().toString().trim();
//                    profession = myProfession.getText().toString().trim();
//                    contact = myContact.getText().toString().trim();
//                    hqualification = myHqualification.getText().toString().trim();
//                    email = myEmail.getText().toString().trim();
//
//                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(hours) ){
//                        firebaseMethods.addFriends(name,age,profession,hqualification,hours,contact,email,mUserItems);
//                        Toast.makeText(view.getContext(),"Successful",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(view.getContext(),"Please enter all the information",Toast.LENGTH_SHORT).show();
//                    }
















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


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
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

}
