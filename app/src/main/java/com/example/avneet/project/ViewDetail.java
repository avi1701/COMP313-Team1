package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDetail extends AppCompatActivity {

    private TextView tx,tx1,tx2,tx3,tx4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);

        Intent ob=getIntent();

        String user=ob.getStringExtra("un").toString();

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Users where email='"+user+"' and role='user'");
        if(cr.moveToNext())
        {
            //12-16
            tx=(TextView)findViewById(R.id.textView12);
            tx1=(TextView)findViewById(R.id.textView13);
            tx2=(TextView)findViewById(R.id.textView14);
            tx3=(TextView)findViewById(R.id.textView15);
            tx4=(TextView)findViewById(R.id.textView16);

            tx.setText("First Name: "+cr.getString(1));
            tx1.setText("Last Name: "+cr.getString(2));
            tx2.setText("Email: "+cr.getString(3));
            tx3.setText("Password: "+cr.getString(4));
            tx4.setText("Contact: "+cr.getString(5));
        }

    }
}
