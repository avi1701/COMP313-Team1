package com.example.avneet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserMenu extends AppCompatActivity {

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Intent ob=getIntent();
        user=ob.getStringExtra("un").toString();

        ((Button)findViewById(R.id.log)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(UserMenu.this,"Logout Successfully!!!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),login.class);
                 startActivity(i);
            }
        });
        ((Button)findViewById(R.id.view)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),ViewDetail.class);
                i.putExtra("un",user);
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),EditUser.class);
                i.putExtra("un",user);
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),User.class);
                i.putExtra("un",user);
                startActivity(i);
            }
        });
        ((Button)findViewById(R.id.viewpro)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(),view_main.class);
                startActivity(i);
            }
        });
    }


}
