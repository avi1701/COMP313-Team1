package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Credential extends AppCompatActivity {

    private EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        Intent obj=getIntent();
    }

    public void OnClick(View view)
    {

        user=(EditText)findViewById(R.id.editText6);
        pass=(EditText)findViewById(R.id.editText7);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Users where email='"+user.getText().toString()+"' and role='admin'");
        Cursor cr1 = db.excuteMyQuery("select * from Users where email='"+user.getText().toString()+"' and role='user'");

        if(cr.moveToNext())
        {

            Intent ob=new Intent(this,AdminActivity.class);
            startActivity(ob);
        }
        else if(cr1.moveToNext())
        {
            Intent ob=new Intent(this,UserMenu.class);
            ob.putExtra("un",user.getText().toString());
            startActivity(ob);
        }
        else
        {
            Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
        }


    }
}
