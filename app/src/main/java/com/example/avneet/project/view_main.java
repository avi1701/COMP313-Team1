package com.example.avneet.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class view_main extends AppCompatActivity {

    ImageView layoutToAnimate ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);
        setTitle("CANADIAN WEB SHOP");
        startAnimation();
    }
    private void startAnimation()
    {

        layoutToAnimate= (ImageView)findViewById(R.id.imageView4);
        // Load the appropriate animation
        Animation an =  AnimationUtils.loadAnimation(this, R.anim.transparency);
        // Start the animation
        layoutToAnimate.startAnimation(an);

    }
}
