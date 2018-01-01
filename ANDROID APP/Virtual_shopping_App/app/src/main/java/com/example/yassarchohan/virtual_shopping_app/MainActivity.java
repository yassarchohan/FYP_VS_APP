package com.example.yassarchohan.virtual_shopping_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity extends AppCompatActivity {

    EditText edt, edt1;
    public SharedPreferences.Editor loginPrefsEditor;
    public  SharedPreferences loginPreferences;
    private Boolean saveLogin;
    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String musername;
    private ChildEventListener mchildlistener;
  //  private firview mMessageAdapter;
    ProgressBar alertDialog;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInResult result;
    private Getter_methods gm;
    private FirebaseStorage mstorage;
    FirebaseDatabase fb;
    private DatabaseReference mDatabase,mDatabase2;
    private FirebaseUser user;
    private String currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.loginforhomepage);
        TextView txt = (TextView) findViewById(R.id.forsignup);
        edt = (EditText) findViewById(R.id.tochkemail);
        edt1 = (EditText) findViewById(R.id.tochkpass);

        /*if (edt.getText().toString() == null || edt1.getText().toString() == null){

        }*/



        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);



        if (saveLogin) {
            edt.setText(loginPreferences.getString("username", ""));
            edt1.setText(loginPreferences.getString("password", ""));


        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertDialog = new ProgressBar(MainActivity.this);
//                Thread thread = new Thread();
//                try {
//                    alertDialog.setVisibility(View.VISIBLE);
//                    thread.sleep(2300);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

//                String username = edt.getText().toString();
//                String password = edt1.getText().toString();
//                String type = "login";
//                loginPreferences.getBoolean("savelogin",true);
//                loginPreferences.getString("username",username);
//                loginPreferences.getString("password",password);
//                loginPrefsEditor.commit();
                String u = edt.getText().toString();
                String p = edt1.getText().toString();
                if (u.isEmpty()) {
                    edt.setError("Please enter email");
                    return;
                }
                if (p.isEmpty()) {
                    edt1.setError("Please enter Password");
                    return;
                }

                forsignin(u,p);

            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sign_up.class);
                startActivity(intent);
            }
        });

        Authsetup();

    }

    public void Authsetup(){

        fAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this, "login succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,Home_screen.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "please login to continue", Toast.LENGTH_SHORT).show();

                }


            }

        };

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(MainActivity.this, "cant continue signin", Toast.LENGTH_SHORT).show();
//    }


    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthListener);

    }

    public void forsignin(String useremail,String password){

        fAuth.signInWithEmailAndPassword(useremail,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {



                Log.d("TAG", "signInWithEmail:onComplete:" + task.isSuccessful());

                if (task.isSuccessful() ) {



                        Toast.makeText(MainActivity.this, "please enter", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Home_screen.class);
                        intent.putExtra("name", String.valueOf(user));
                        startActivityForResult(intent, 0);
                        //    txt.setText("error handling auth" + task.getException());

                }


                else {

                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Failed to Login!");
                alertDialog.setIcon(R.drawable.common_google_signin_btn_icon_dark_disabled);
                alertDialog.setMessage("please enter correct email and password to Sign_in");
                alertDialog.show();

                     Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }



            }




        });
    }


}

