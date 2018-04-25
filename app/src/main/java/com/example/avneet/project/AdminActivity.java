package com.example.avneet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent ob=getIntent();

        ((Button)findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(AdminActivity.this,"SUCCESSFULLY LOGOUT!!!",Toast.LENGTH_SHORT).show();
                Intent o=new Intent(AdminActivity.this,Credential.class);
                startActivity(o);
            }
        });

        ((Button)findViewById(R.id.button6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent o=new Intent(AdminActivity.this,View_Property.class);
                startActivity(o);
            }
        });
        ((Button)findViewById(R.id.button7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent o=new Intent(AdminActivity.this,ViewRatings.class);
                startActivity(o);
            }
        });
    }
    public void view_user(View view)
    {
        Intent obj=new Intent(this,View_Users.class);
        startActivity(obj);
    }
    public void view_prop(View view)
    {
        Intent obj=new Intent(this,View_prop_det.class);
        startActivity(obj);
    }

}
