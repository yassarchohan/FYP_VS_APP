package com.example.yassarchohan.virtual_shopping_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class feed_back extends AppCompatActivity {

    private FirebaseDatabase fb;
    private DatabaseReference db;
    private FirebaseUser user;
    private Getter_methods gm;
    private ChildEventListener childEventListener;
    String title;
    String message;
    private Custom_adapter custom_adapter;
    RecyclerView recyclerView;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


        Button btn = (Button) findViewById(R.id.submissionfeedback);




         user = FirebaseAuth.getInstance().getCurrentUser();
        

        db = FirebaseDatabase.getInstance().getReference().child("FeedbackMessages").child(user.getUid());

        progressDialog = new ProgressDialog(feed_back.this);

        progressDialog.setMessage("Loading Data from Firebase Database");

        progressDialog.show();





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edt = (EditText) findViewById(R.id.todisplaytitle);
                EditText edt2 = (EditText) findViewById(R.id.forfeedbacksubmit);


                    gm = new Getter_methods(user.getEmail(),edt.getText().toString(), edt2.getText().toString());
                    db.setValue(gm);

                    Intent intent = new Intent(feed_back.this,Home_screen.class);
                    startActivity(intent);

                    Toast.makeText(feed_back.this, "Thankx for your good feedback we will work on it", Toast.LENGTH_SHORT).show();



            }
        });



        final List<Getter_methods> list = new ArrayList<>();



         recyclerView = (RecyclerView) findViewById(R.id.list2);

        custom_adapter = new Custom_adapter(list);
        custom_adapter.notifyItemInserted(list.size() - 1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(feed_back.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(custom_adapter);
//ye code jo comment hai is mai issue arha hai crash horhi hai app

//     db.addChildEventListener(new ChildEventListener() {
//         @Override
//         public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//             gm = dataSnapshot.getValue(Getter_methods.class);
//
//             list.add(gm);
////
////
////             custom_adapter = new Custom_adapter(feed_back.this, list);
////
////             recyclerView.setAdapter(custom_adapter);
////
////             progressDialog.dismiss();
//       }
//
//         @Override
//         public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//         }
//
//         @Override
//         public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//         }
//
//         @Override
//         public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//         }
//
//         @Override
//         public void onCancelled(DatabaseError databaseError) {
//
//         }
//     });
    }
}
