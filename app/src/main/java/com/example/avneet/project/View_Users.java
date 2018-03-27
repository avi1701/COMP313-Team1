package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class View_Users extends AppCompatActivity {

    Spinner ss;
    ArrayList<Integer> id;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__users);

        Intent ob=getIntent();

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select user_id,email from Users where role='user'");

        id = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();
        while(cr.moveToNext())
        {
            id.add(cr.getInt(0));
            arr.add(cr.getString(1));

        }


        ss = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arr);
        ss.setAdapter(adap);
        //giving default
        ss.setSelection(0);

        ((Button)findViewById(R.id.open)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),User.class);
                i.putExtra("id",id.get(ss.getSelectedItemPosition()));
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.del)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),Delete.class);
                i.putExtra("id",id.get(ss.getSelectedItemPosition()));
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),AdminActivity.class);
                startActivity(i);
            }
        });    }
}
