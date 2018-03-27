package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class UserBackend extends AppCompatActivity {

    private Firebase fref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_backend);

        Firebase.setAndroidContext(this);

        Intent ob=getIntent();
        String user=ob.getStringExtra("name").toString();
        String path=ob.getStringExtra("path").toString();

       // Backend db = new Backend(getApplicationContext());
        //db.createDatabase(getApplicationContext());

  //      Cursor cr = db.excuteMyQuery("select * from Picture where pic_id=3");
//        cr.moveToNext();
    //    Toast.makeText(this, cr.getInt(0), Toast.LENGTH_SHORT).show();

        fref = new Firebase("https://myexampledb-54576.firebaseio.com/Pictures/"+user+"/");
        //Firebase mRef0 = fref.child("Picture Id");
        //mRef0.setValue(cr.getInt(0));

        Firebase mRef = fref.child("Added By Name");
        mRef.setValue(user);

       // Firebase mRef1 = fref.child("Path of image");
        //mRef1.setValue(path);

     //   db.close();
    }
}
