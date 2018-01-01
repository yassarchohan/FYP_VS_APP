package com.example.yassarchohan.virtual_shopping_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up extends AppCompatActivity {

    EditText edt,edt1,edt2,edt3;
    Button btn;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    private Getter_methods gm;
    FirebaseDatabase fb;
    private DatabaseReference mDatabase;
    private FirebaseUser user;

    String str_user;
    String str_pass;
    String str_email;
    String str_contacts;
   String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fAuth = FirebaseAuth.getInstance();



        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };



        edt = (EditText) findViewById(R.id.forname);
        edt1 = (EditText) findViewById(R.id.foremail);
        edt2 = (EditText) findViewById(R.id.forcontacts);
        edt3 = (EditText) findViewById(R.id.forpass);


        edt3.setFilters(new InputFilter[]{filter});

        btn = (Button) findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                str_user =edt.getText().toString();
                 str_pass=edt3.getText().toString();
                str_email =edt1.getText().toString();
                str_contacts  = edt2.getText().toString();

                String encrypt;
                String key = "yassar";

//                encrypt = str_pass.substring(0,4);
//                encrypt.concat(key);
//                String final = encrypt.concat(str_pass.substring(5,7));

                if (str_user.isEmpty()) {
                    edt.setError("Please enter name");
                    return;
                }
                if (str_email.isEmpty()) {
                    edt1.setError("Please enter email");
                    return;
                }
                if (str_contacts.isEmpty()) {
                    edt2.setError("Please enter number");
                    return;
                }
                if (str_pass.isEmpty()) {
                    edt3.setError("Please enter password");
                    return;
                }


                forsignup();
//                if( str_user!= null && str_email !=null  && str_contacts != null && str_pass != null) {
//
//
//                    Toast.makeText(Sign_up.this, "thanks for using our App", Toast.LENGTH_SHORT).show();
//
//                }

//                else {
//                    AlertDialog alert = new AlertDialog.Builder(Sign_up.this).create();
//                    alert.setMessage("Please fill all fields to create your account,");
//                    alert.setTitle("Failed To Create Account!");
//                    alert.setIcon(R.drawable.common_google_signin_btn_icon_dark_disabled);
//                    alert.show();
//
//
//                }




            }
        });

        Authsetup();

    }


    public void Authsetup() {


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(Sign_up.this, "login succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_up.this, Home_screen.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Toast.makeText(Sign_up.this, "Please sign in first to continue", Toast.LENGTH_SHORT).show();

                }


            }

        };
    }
    @Override
    public void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            fAuth.removeAuthStateListener(mAuthListener);

        }
    }



    public void forsignup() {
        fAuth.createUserWithEmailAndPassword(edt1.getText().toString(), edt3.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Toast.makeText(Sign_up.this, "createUserWithEmail:onComplete: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                        if (task.isSuccessful()) {
                            Toast.makeText(Sign_up.this, "you are registered",
                                    Toast.LENGTH_LONG).show();

                           id = fAuth.getCurrentUser().getUid();



                            mDatabase = FirebaseDatabase.getInstance().getReference().child("CurrentUsers").push();
                         //   mDatabase2 = fb.getReference().child("Users").child("Groupusers").child(currentuser);

                            if (!task.isComplete()) {
                                Toast.makeText(Sign_up.this, "eorror", Toast.LENGTH_SHORT).show();

                            }


                            edt.setText("");
                            edt2.setText("");
                            edt3.setText("");
                            edt1.setText("");




                        }
                        else {
                          //  Toast.makeText(Sign_up.this, "registration inwith:failed" + task.getException(), Toast.LENGTH_SHORT).show();
                            //  Log.d("FAuth", "Registration InWithEmail:failed"+task.getException());
                            // Log.d("FAuth", "Registration Failed");
                            edt.setText("");
                            edt2.setText("");
                            edt3.setText("");
                            edt1.setText("");
                            AlertDialog alert = new AlertDialog.Builder(Sign_up.this).create();
                            alert.setMessage("Please fill all fields to create your account or check email order");
                            alert.setTitle("Failed To Create Account!");
                            alert.setIcon(R.drawable.common_google_signin_btn_icon_dark_disabled);
                            alert.show();
//                            txt.setText("sorry to register you sir please enter correct email or enter strong password with at least 6 chars");
                        }

                        user = FirebaseAuth.getInstance().getCurrentUser();



                        if( str_user!= null && str_email !=null  && str_contacts != null && str_pass != null && id !=null) {
                            gm = new Getter_methods(str_user, str_email, str_contacts, str_pass, id);
                            mDatabase.setValue(gm);
                        }

                        }
                        // ...

                });
    }
}
