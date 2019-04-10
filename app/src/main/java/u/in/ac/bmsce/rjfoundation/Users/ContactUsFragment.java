package u.in.ac.bmsce.rjfoundation.Users;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import u.in.ac.bmsce.rjfoundation.Firebase.FirebaseMethods;
import u.in.ac.bmsce.rjfoundation.Profile.AccountSettingsActivity;
import u.in.ac.bmsce.rjfoundation.R;
import u.in.ac.bmsce.rjfoundation.models.ContactUs;

import static android.support.constraint.Constraints.TAG;

public class ContactUsFragment extends Fragment {

private Context mContext;

    EditText name, email, subject, message;
    Button submit;


    //floating Action button
    FloatingActionMenu materialDesignFAM;
    com.github.clans.fab.FloatingActionButton fab_fb, fab_linkedin, fab_insta,fab_github, fab_web, fab_help;

    FirebaseMethods firebaseMethods;

    private String cname,cemail,csubject,cmessage;
    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);


           name = view.findViewById(R.id.input_cname);
           email = view.findViewById(R.id.input_cemail);
        subject = view.findViewById(R.id.input_subject);
        message = view.findViewById(R.id.input_message);


        submit = view.findViewById(R.id.btn_csubmit);

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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.d(TAG, "onClick: Submit pressed.");
                String cname = name.getText().toString();
               String cemail = email.getText().toString();
                String csubject = subject.getText().toString();
               String cmessage = message.getText().toString();
                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + name + "\n" +
                        "email: " + email + "\n" +
                        "phone number: " + subject + "\n"
                );
                FirebaseUser user = mAuth.getCurrentUser();
                   String userID = user.getUid();
                //handle the exception if the EditText fields are null
                if(!cname.equals("") && !cemail.equals("") && !csubject.equals("") && !cmessage.equals("")){
                    ContactUs contactinformation = new ContactUs(cname,cemail,csubject,cmessage);
                    myRef.child("contact_details").child(userID).setValue(contactinformation);
                    toastMessage("Congrats !!! your Enquiry has been recoreded");
                    name.setText("");
                    email.setText("");
                    subject.setText("");
                    message.setText("");
                }else {
                    toastMessage("Fill out all the fields");

                }
//  ------------------------------------- THIS METHOD IS FOR ADDING DATA TO DATABSE ------------------------------------------
//                Log.d(TAG, "onClick: Attempting to add object to database.");
//                String cname = name.getText().toString();
//                String cemail = email.getText().toString();
//                String csubject = subject.getText().toString();
//                String cmessage = message.getText().toString();
//
//                if(!cname.equals("")){
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String userID = user.getUid();
//                    myRef.child(getString(R.string.contact_details))
//                            .child(userID)
//                            .child("name").setValue(cname);
//                    toastMessage("Adding " + cname + " to database...");
//                    //reset the text
//                    name.setText("");
//                }
//                if(!cemail.equals("")){
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String userID = user.getUid();
//                    myRef.child(getString(R.string.contact_details))
//
//                            .child(userID).child("email").setValue(cemail);
//
//                    //reset the text
//                    email.setText("");
//                }
//                if(!csubject.equals("")){
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String userID = user.getUid();
//                    myRef.child(getString(R.string.contact_details))
//
//                            .child(userID).child("subject").setValue(csubject);
//
//                    //reset the text
//                    subject.setText("");
//                }
//                if(!cmessage.equals("")){
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    String userID = user.getUid();
//                    myRef.child(getString(R.string.contact_details))
//
//                            .child(userID).child("message").setValue(cmessage);
//
//                    //reset the text
//                    message.setText("");
//                }
//                toastMessage("Congrats !!! your Enquiry has been recoreded");
            }

        });








        //back arrow for navigating back to "ProfileActivity"
        ImageView backArrow = (ImageView) view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                getActivity().finish();
            }
        });



// --------------------------------------Floating Action ------------------------------------------------------------------
        materialDesignFAM = (FloatingActionMenu) view.findViewById(R.id.social_floating_menu);
        fab_fb = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_facebook);
        fab_linkedin = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_linkedin);
        fab_insta= (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_instagram);
        fab_github = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_github);
        fab_web = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_web);
        fab_help = (com.github.clans.fab.FloatingActionButton) view.findViewById(R.id.floating_help);

        fab_fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(getActivity());
                startActivity(facebookIntent);

            }
        });
        fab_linkedin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent linkdinIntent = getOpenLinkdinIntent(getActivity());
                startActivity(linkdinIntent);


            }
        });
        fab_insta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //TODO something when floating action menu second item clicked
                Intent instagramIntent = getOpenInstagramIntent(getActivity());
                startActivity(instagramIntent);

            }
        });

        fab_github.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent googleplusIntent = getOpenGithubIntent(getActivity());
                startActivity(googleplusIntent);
            }
        });
        fab_web.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent linkdinIntent = getOpenWebIntent(getActivity());
                startActivity(linkdinIntent);

            }
        });
        fab_help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                startActivity(intent);
            }
        });


        return view;
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
    ///-------------------------------------------Floating Action button methods for intent------------
    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/profile.php?id=100007105339078")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/profile.php?id=100007105339078")); //catches and opens a url to the desired page
        }
    }



    public static Intent getOpenLinkdinIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.linkedin.android", 0); //Checks if Linkdin is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/naveen-r-817234130/")); //Trys to make intent with Linkdin's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/naveen-r-817234130/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenGithubIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.github.android", 0); //Checks if G+ is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/naveenrj98/RJ_Foundation")); //Trys to make intent with G+'s URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/naveenrj98/RJ_Foundation")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenWebIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube", 0); //Checks if YT is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://sites.google.com/view/rj-foundation/projects/android-app?authuser=0")); //Trys to make intent with YT's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://sites.google.com/view/rj-foundation/projects/android-app?authuser=0")); //catches and opens a url to the desired page
        }
    }




}