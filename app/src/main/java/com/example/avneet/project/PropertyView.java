package com.example.avneet.project;

import android.*;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertyView extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;
    EditText email,rev;
    Spinner rate;
    LinearLayout layout;
    String path;
    int id;
    String date,person;
    String review;
    private Firebase fref,fref2;
    int univid,proid,ratngs;

    String printadd;

    private List<String> comments=new ArrayList<String>();

    int rating;
    List<Integer> list = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_view);
        Intent ob=getIntent();
        id=ob.getIntExtra("uid",0);
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        Firebase.setAndroidContext(this);

        Log.d("VALUE: ",String.valueOf(id));
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        layout = (LinearLayout)findViewById(R.id.linearlayout);

        Cursor cr = db.excuteMyQuery("select * from Property where pic_id='"+id+"'");
        while(cr.moveToNext())
        {
           ImageView myimg = new ImageView(this);
            myimg.setLayoutParams(new android.view.ViewGroup.LayoutParams(350, 350));
            myimg.setMaxHeight(350);
            myimg.setMaxWidth(350);

            ImageView mapimg=new ImageView(this);
            mapimg.setLayoutParams(new android.view.ViewGroup.LayoutParams(50, 50));
            mapimg.setImageResource(R.drawable.map);

            path = cr.getString(6);

            univid=cr.getInt(1);
            proid=cr.getInt(0);

            Bitmap bmp = BitmapFactory.decodeFile(path);
            myimg.setImageBitmap(bmp);

            printadd=cr.getString(2)+", "+cr.getString(3)+", "+cr.getString(4);
            TextView add=new TextView(this);
            add.setText("Address: "+cr.getString(2)+", "+cr.getString(3)+", "+cr.getString(4)+", "+cr.getString(5));

            TextView tx=new TextView(this);
            tx.setText("Price: "+cr.getInt(9));

            TextView tx1=new TextView(this);
            tx1.setText("Type: "+cr.getString(10));

            TextView tx2=new TextView(this);
            tx2.setText("Description: "+cr.getString(8));

            TextView tx3=new TextView(this);
            tx3.setText("Date Posted: "+cr.getString(7));

            TextView tx4=new TextView(this);
            tx4.setText("FOR UPLOAD YOUR OWN PROPERTY TO SELL OR TO RENT LOGIN OR SIGN UP ");

            TextView tx5=new TextView(this);
            tx5.setText("");

            TextView tx6=new TextView(this);
            tx6.setText("");

            TextView tx7=new TextView(this);
            tx7.setText("");

            TextView tx8=new TextView(this);
            tx8.setText("");

            Button btn=new Button(this);
            btn.setText("CLICK TO LOGIN");

            Button btn1=new Button(this);
            btn1.setText("CLICK TO SIGN-UP");

            layout.addView(mapimg);
            layout.addView(myimg);
            layout.addView(add);
            layout.addView(tx);
            layout.addView(tx1);
            layout.addView(tx2);
            layout.addView(tx3);

            contact(cr.getInt(1));

            layout.addView(tx5);
            layout.addView(tx6);
            layout.addView(tx7);
            layout.addView(tx8);

            layout.addView(tx4);
            layout.addView(btn);
            layout.addView(btn1);

            comment_insert(cr.getInt(0));

            mapimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ob=new Intent(PropertyView.this,MapsActivity.class);
                    ob.putExtra("id",proid);
                    ob.putExtra("name",printadd);
                    startActivity(ob);
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ob=new Intent(PropertyView.this,Credential.class);
                    startActivity(ob);
                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ob=new Intent(PropertyView.this,Register.class);
                    startActivity(ob);
                }
            });
        }

        rate=(Spinner)findViewById(R.id.spinner3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, list);

        rate.setAdapter(dataAdapter);

        rating=Integer.parseInt(rate.getSelectedItem().toString());


        ((Button)findViewById(R.id.button10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                rev=(EditText)findViewById(R.id.editText9);
                email=(EditText)findViewById(R.id.editText8);

                addComm(proid,univid,rev.getText().toString(),rating,email.getText().toString());
            }
        });
        }
        public void contact(int id)
        {
            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());

            Cursor cr = db.excuteMyQuery("select * from Users where user_id='"+id+"'");
            while (cr.moveToNext())
            {
                TextView tx2=new TextView(this);
                tx2.setText("Name: "+cr.getString(1)+" "+cr.getString(2));


                TextView tx3=new TextView(this);
                tx3.setText("Contact: "+cr.getString(5)+", Email: "+cr.getString(3));

                layout.addView(tx2);
                layout.addView(tx3);
            }
        }

        public void comment_insert(int pid)
        {
            GridView grid=new GridView(this);
            comments.add("");
            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());

            Cursor cr = db.excuteMyQuery("select * from Review where pic_id='"+pid+"'");
            while (cr.moveToNext())
            {
                comments.add("Review: "+cr.getString(3));
                comments.add("Rating: "+cr.getInt(4));
                comments.add("Email: "+cr.getString(6));
                comments.add("----------------------------------------------");
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, comments);

            grid.setAdapter(dataAdapter);


            layout.addView(grid);
        }

        public void addComm(int pid,int uid,String review,int rating,String per)
        {
            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());

            date=new Date().toString();
            db.insertIntoReview(uid,pid,review,rating,date,per);
            Log.d("2st","func");
            func2(pid);
            db.close();
            finish();
        }

    public void func2(int id)
    {

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Review where pic_id="+id+"");

        cr.moveToNext();
        Log.d("3rd","func2");

        fref=new Firebase("https://myexampledb-54576.firebaseio.com/Comments/"+cr.getInt(0)+"/");

        Firebase mRef0=fref.child("Review ID");
        mRef0.setValue(cr.getInt(0));

        Firebase mRef=fref.child("User Id");
        mRef.setValue(cr.getInt(1));

        Firebase mRef1=fref.child("Property ID");
        mRef1.setValue(cr.getInt(2));

        Firebase mRef2=fref.child("Review");
        mRef2.setValue(cr.getString(3));

        Firebase mRef3=fref.child("Rating");
        mRef3.setValue(cr.getInt(4));

        Firebase mRef4=fref.child("Date Posted");
        mRef4.setValue(cr.getString(5));

        Firebase mRef5=fref.child("Posted by");
        mRef5.setValue(cr.getString(6));

        Toast.makeText(this,"Value Added to Firebase", Toast.LENGTH_SHORT).show();
        Intent ob=new Intent(this,view_main.class);
        startActivity(ob);

    }

}
