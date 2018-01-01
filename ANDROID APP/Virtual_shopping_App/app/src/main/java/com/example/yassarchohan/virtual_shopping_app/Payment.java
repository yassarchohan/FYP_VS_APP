package com.example.yassarchohan.virtual_shopping_app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;


public class Payment extends AppCompatActivity {

    DataSnapshot dataSnapshot;
    Button cancl,order;
    private NotificationCompat.Builder notification;

    private FirebaseDatabase firebase;
    private DatabaseReference db,db2;
    private ChildEventListener childevent;
    private FirebaseUser user;
    private  Getter_methods gm;
    private Custom_adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ImageView scan_btn;

        order = (Button) findViewById(R.id.buyproduct);
        cancl = (Button) findViewById(R.id.forordercancel);

        final Intent intent2 = new Intent(this, Home_screen.class);


        user = FirebaseAuth.getInstance().getCurrentUser();
        firebase = FirebaseDatabase.getInstance();
        db = firebase.getReference().child("orders").push();
        db2 = firebase.getReference().child("orders").child("admin").push();






        scan_btn = (ImageView) findViewById(R.id.Scancode);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(true);
                integrator.setBeepEnabled(true);
                integrator.setCaptureActivity(capture.class);
                integrator.initiateScan();
            }
        });

        notification = new NotificationCompat.Builder(Payment.this);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this,Home_screen.class);
                startActivity(intent);

                notification.setAutoCancel(true);
                final PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pi);


                notification.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
                notification.setContentTitle("Virtual_Shopping_app");
                notification.setTicker("order sent");
                notification.setContentText("Thank you for Buying our product please recieve your order");

                notification.setWhen(System.currentTimeMillis());

                notification.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.whatsapp_whistle));
                notification.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE) ;
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                nm.notify(0, notification.build());

            }
        });

        cancl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment.this,Home_screen.class);
                startActivity(intent);
                Toast.makeText(Payment.this, "Your order has been canceled", Toast.LENGTH_SHORT).show();
            }
        });


        final List<Getter_methods> list = new ArrayList<>();






        db.addChildEventListener(new ChildEventListener() {

            // list nai banrahi crash horahi hai
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.clear();
                gm =  dataSnapshot.getValue(Getter_methods.class);
                gm.setId(Integer.parseInt(dataSnapshot.getKey()));
                list.add(gm);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reciptlist);
                mAdapter = new Custom_adapter(list);
                mAdapter.notifyItemInserted(list.size() - 1);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Payment.this);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result !=null)
        {
            TextView txt1 = (TextView) findViewById(R.id.text123);
            TextView txt = (TextView) findViewById(R.id.textrecipt);
            txt.setText("Reciept");

            if(result.getContents()== null){

                Toast.makeText(this,"You cancel the scanning",Toast.LENGTH_LONG).show();
            }


            else{

                String content = result.getContents();
               // txt.setText(content);


                String prize = content.substring(45,49);
                gm = new Getter_methods(user.getEmail(),content,prize);
                gm.setProductname(content);
              //  gm.setId(Integer.parseInt(prize));

                db.setValue(gm);
                db2.setValue(gm);

                txt1.setText(content);

             //   Getter_methods[] abc = {gm};

                Toast.makeText(Payment.this, "Scan successful", Toast.LENGTH_SHORT).show();



            }


        }
        else
        {

            super.onActivityResult(requestCode, resultCode, data);
        }


    }




        }



