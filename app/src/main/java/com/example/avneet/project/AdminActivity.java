package com.example.avneet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Intent ob=getIntent();
    }
    public void view_user(View view)
    {
        Intent obj=new Intent(this,View_Users.class);
        startActivity(obj);
    }
    public void view_prop(View view)
    {
        Intent obj=new Intent(this,View_Property.class);
        startActivity(obj);
    }

}
