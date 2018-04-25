package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayUserDetail extends AppCompatActivity {

    private TextView tx,tx1,tx2,tx3,tx4;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user_detail);

        userId = getIntent().getExtras().getInt("id",1);
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Users where user_id="+userId+" and role='user'");
        if(cr.moveToNext())
        {
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
        ((Button)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        });
    }
}
