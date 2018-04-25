package com.example.avneet.project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class User extends AppCompatActivity {

    Spinner ss;
    private static int RESULT_LOAD_IMAGE = 1;
    private int temp;
    private EditText pr,addrs,city,pro,post,desc;
    private Firebase fref,fref2;
    String picturePath;
    List<String> list = new ArrayList<String>();
    TextView tx;
    String date,user;
    int un;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent ob=getIntent();
        user=ob.getStringExtra("un").toString();

        Firebase.setAndroidContext(this);

        ss = (Spinner) findViewById(R.id.spinner2);

        tx=(TextView)findViewById(R.id.textView10);
        tx.setText(user);

        addrs=(EditText)findViewById(R.id.addrs);
        city=(EditText)findViewById(R.id.city);
        pro=(EditText)findViewById(R.id.pro);
        post=(EditText)findViewById(R.id.post);
        desc=(EditText)findViewById(R.id.desc);
        pr=(EditText)findViewById(R.id.pr);

        Button buttonLoadImage = (Button) findViewById(R.id.button6);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        list.add("Rent");
        list.add("Sell");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        ss.setAdapter(dataAdapter);

        bt=(Button)findViewById(R.id.add);
        bt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Backend db = new Backend(getApplicationContext());
                db.createDatabase(getApplicationContext());
                Cursor cr = db.excuteMyQuery("select user_id from Users where email='"+user+"'");
                cr.moveToNext();
                un=cr.getInt(0);
                Log.d("1st","db add button");
                func(un,ss.getSelectedItem().toString());
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent ob=getIntent();
        String user=ob.getStringExtra("un").toString();

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            File f = new File(picturePath);
            Bitmap my = BitmapFactory.decodeFile(f.getAbsolutePath());
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageURI(selectedImage);

            Toast.makeText(this,user+ picturePath, Toast.LENGTH_SHORT).show();
        }
    }



         public void func(int uid,String ch)
        {
            temp=Integer.parseInt(pr.getText().toString());
            date=new Date().toString();
            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());
            db.insertIntoPicture(uid,addrs.getText().toString(),city.getText().toString(),pro.getText().toString(),post.getText().toString(),desc.getText().toString(),date.toString(),temp,ch,picturePath);
            Log.d("2st","func");
            func2(uid);
            db.close();
        }

        public void func2(int id)
        {

            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());

            Cursor cr = db.excuteMyQuery("select * from Property where user_id="+id+"");

            cr.moveToNext();
            Log.d("3rd","func2");

            fref=new Firebase("https://myexampledb-54576.firebaseio.com/Property/"+cr.getInt(0)+"/");

            Firebase mRef0=fref.child("Property Id");
            mRef0.setValue(cr.getInt(0));

            Firebase mRef=fref.child("User Id");
            mRef.setValue(cr.getInt(1));

            Firebase mRef1=fref.child("Address");
            mRef1.setValue(cr.getString(2));

            Firebase mRef2=fref.child("City");
            mRef2.setValue(cr.getString(3));

            Firebase mRef3=fref.child("Province");
            mRef3.setValue(cr.getString(4));

            Firebase mRef4=fref.child("Postal Code");
            mRef4.setValue(cr.getString(5));

            Firebase mRef5=fref.child("Image");
            mRef5.setValue(cr.getString(6));

            Firebase mRef6=fref.child("Date");
            mRef6.setValue(cr.getString(7));

            Firebase mRef7=fref.child("Description");
            mRef7.setValue(cr.getString(8));

            Firebase mRef8=fref.child("Price");
            mRef8.setValue(cr.getInt(9));

            Firebase mRef9=fref.child("Type");
            mRef9.setValue(cr.getString(10));

            Toast.makeText(this,"Value Added to Firebase", Toast.LENGTH_SHORT).show();
            Intent ob=new Intent(this,UserMenu.class);
            ob.putExtra("un",user);
            startActivity(ob);

        }


}
