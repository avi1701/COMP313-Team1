package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class View_prop_det extends AppCompatActivity {

    private ImageView img;
    private TextView tx;
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prop_det);


        userId = getIntent().getExtras().getInt("id",1);
        tx=(TextView)findViewById(R.id.textView11);
        img=(ImageView)findViewById(R.id.imgView);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Picture where pic_id="+userId);

        cr.moveToNext();
        Bitmap b = BitmapFactory.decodeFile(cr.getString(2));
        img.setImageBitmap(b);



    }
}
