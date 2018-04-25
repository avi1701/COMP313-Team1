package com.example.avneet.project;

import android.*;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchProp extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;
    List<String> list = new ArrayList<String>();

    Spinner ss;

    int val;
    ImageView myimg;
    ImageView layoutToAnimate ;
    String path;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_prop);
        Intent ob=getIntent();
        startAnimation();

        type=ob.getStringExtra("prop_type");

        Log.d("INTENT FETCH VALUE",type.toString());

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        myimg=(ImageView)findViewById(R.id.imageView5);
        LinearLayout layout = (LinearLayout)findViewById(R.id.searchlinear);

        ss=new Spinner(this);

        list.add("Rent");
        list.add("Sell");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        ss.setAdapter(dataAdapter);

        Button search=new Button(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ob=new Intent(SearchProp.this,SearchProp.class);
                ob.putExtra("prop_type",ss.getSelectedItem().toString());
                startActivity(ob);
            }
        });
        search.setText("SEARCH PROPERTY");
        search.setMaxWidth(150);
        layout.addView(ss);
        layout.addView(search);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr=db.excuteMyQuery("select * from Property where type='"+type.toString()+"'");

        while (cr.moveToNext())
        {
            Log.d("DATABASE FETCH VALUE",""+cr.getInt(0));

            myimg = new ImageView(this);
            myimg.setLayoutParams(new android.view.ViewGroup.LayoutParams(350, 350));
            myimg.setMaxHeight(350);
            myimg.setMaxWidth(350);
            TextView pr=new TextView(this);
            path = cr.getString(6);

            Bitmap bmp = BitmapFactory.decodeFile(path);
            myimg.setImageBitmap(bmp);
            myimg.setId(cr.getInt(0));
            pr.setText(""+cr.getInt(9));
            layout.addView(myimg);
            layout.addView(pr);
            val=cr.getInt(0);
            myimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ob=new Intent(SearchProp.this,PropertyView.class);
                    ob.putExtra("uid",val);
                    startActivity(ob);
                }
            });
        }


    }
    private void startAnimation()
    {

        layoutToAnimate= (ImageView)findViewById(R.id.imageView4);
        Animation an =  AnimationUtils.loadAnimation(this, R.anim.transparency);
        layoutToAnimate.startAnimation(an);

    }

}
