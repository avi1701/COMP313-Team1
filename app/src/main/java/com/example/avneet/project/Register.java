package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Register extends AppCompatActivity {

    private EditText fn,ln,em,pass,mob;
    Button add,view;
    private Firebase fref;
    private  String name;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent ob=getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fn=(EditText)findViewById(R.id.editText);
        ln=(EditText)findViewById(R.id.editText5);
        em=(EditText)findViewById(R.id.editText2);
        pass=(EditText)findViewById(R.id.editText4);
        mob=(EditText)findViewById(R.id.editText3);

        add=(Button) findViewById(R.id.button2);

        Firebase.setAndroidContext(this);

        add=(Button) findViewById(R.id.button2);

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

                db.insertIntoUser(fn.getText().toString(),ln.getText().toString(),em.getText().toString(),pass.getText().toString(),mob.getText().toString(),role.toString());

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

        db.close();

        Intent o=new Intent(this,login.class);
        startActivity(o);
    }
    private boolean validation()
    {
        if(fn.getText().toString().equals("")||ln.getText().toString().equals("")||em.getText().toString().equals("")||pass.getText().toString().equals("")||mob.getText().toString().equals(""))
            return false;

        return true;
    }
}
