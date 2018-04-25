package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Register extends AppCompatActivity {

    private EditText fn,ln,em,pass,mob;
    Button add,view;
    private Firebase fref,fref2;
    private  String name;
    String date,d;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent ob=getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fn=(EditText)findViewById(R.id.editText);
        ln=(EditText)findViewById(R.id.editText5);
        em=(EditText)findViewById(R.id.editText2);
        pass=(EditText)findViewById(R.id.editText3);
        mob=(EditText)findViewById(R.id.editText4);

        add=(Button) findViewById(R.id.button2);

        Firebase.setAndroidContext(this);

        add=(Button) findViewById(R.id.button2);

        date = new Date().toString();

        Log.d("Date:",date.toString());

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(validation())
                {
                    String role="user";
                    Backend db = new Backend(getApplicationContext());
                    db.createDatabase(getApplicationContext());

                    db.insertIntoUser(fn.getText().toString(),ln.getText().toString(),em.getText().toString(),pass.getText().toString(),mob.getText().toString(),role.toString(),date);

                    db.close();
                    Toast.makeText(Register.this,"***** "+em.getText().toString()+" Added to System",Toast.LENGTH_SHORT).show();

                    func(em.getText().toString());
                // finish();
                }
                else{
                  Toast.makeText(Register.this,"Error Occured! "+em.getText().toString()+" not Added to System",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void func(String email)
    {
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());


        Cursor cr = db.excuteMyQuery("select * from Users where email='"+email+"'");

        cr.moveToNext();

        fref=new Firebase("https://myexampledb-54576.firebaseio.com/Users/"+cr.getInt(0)+"/");

        Firebase mRef0=fref.child("User Id");
        mRef0.setValue(cr.getInt(0));

        Firebase mRef=fref.child("First Name");
        mRef.setValue(cr.getString(1));

        Firebase mRef1=fref.child("Last Name");
        mRef1.setValue(cr.getString(2));

        Firebase mRef2=fref.child("Email");
        mRef2.setValue(cr.getString(3));

        Firebase mRef3=fref.child("Role");
        mRef3.setValue(cr.getString(6));

        Firebase mRef4=fref.child("Password");
        mRef4.setValue(cr.getString(4));

        Firebase mRef5=fref.child("Contact");
        mRef5.setValue(cr.getString(5));

        Firebase mRef6=fref.child("Date");
        mRef6.setValue(cr.getString(7));

        refer(email);
        Intent o=new Intent(this,login.class);
        startActivity(o);
    }
    private boolean validation()
    {
        if(fn.getText().toString().equals("")||ln.getText().toString().equals("")||em.getText().toString().equals("")||pass.getText().toString().equals("")||mob.getText().toString().equals(""))
            return false;

        return true;
    }

    public void refer(String email)
    {

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());


        Cursor cr = db.excuteMyQuery("select * from Users where email='"+email+"'");

        cr.moveToNext();

        fref2=new Firebase("https://myexampledb-54576.firebaseio.com/Users-ex/"+cr.getInt(0)+"/");

        Firebase mRef7=fref2.child("User Id");
        mRef7.setValue(cr.getInt(0));

        Firebase mRef8=fref2.child("First Name");
        mRef8.setValue(cr.getString(1));

        Firebase mRef9=fref2.child("Last Name");
        mRef9.setValue(cr.getString(2));

        Firebase mRef10=fref2.child("Email");
        mRef10.setValue(cr.getString(3));

        Firebase mRef11=fref2.child("Role");
        mRef11.setValue(cr.getString(6));

        Firebase mRef12=fref2.child("Password");
        mRef12.setValue(cr.getString(4));

        Firebase mRef13=fref2.child("Contact");
        mRef13.setValue(cr.getString(5));

        Firebase mRef14=fref2.child("Date");
        mRef14.setValue(cr.getString(7));
        db.close();

    }

}
