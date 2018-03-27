package com.example.avneet.project;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class login extends AppCompatActivity {

    ImageView layoutToAnimate ;
    Toolbar my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Intent o=getIntent();
        ActionBar actionBar = getActionBar();
        setTitle("CANADIAN WEB SHOP");
        startAnimation();

    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
        }
        return true;
    }
    private void startAnimation()
    {

        layoutToAnimate= (ImageView)findViewById(R.id.imageView4);
        // Load the appropriate animation
        Animation an =  AnimationUtils.loadAnimation(this, R.anim.transparency);
        // Start the animation
        layoutToAnimate.startAnimation(an);

    }
    public void enter(View view)
    {
        Intent ob=new Intent(this,view_main.class);
        startActivity(ob);
    }

    public void reg(View view)
    {
        Intent obj=new Intent(this,Register.class);
        startActivity(obj);
    }
    public void log(View view)
    {
        Intent obj=new Intent(this,Credential.class);
        startActivity(obj);
    }

}
