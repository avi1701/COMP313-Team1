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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.File;
import java.util.Random;

public class User extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    private EditText addrs,city,pro,post;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent ob=getIntent();
        String user=ob.getStringExtra("un").toString();

        tx=(TextView)findViewById(R.id.textView10);
        tx.setText(user);

        addrs=(EditText)findViewById(R.id.addrs);
        city=(EditText)findViewById(R.id.city);
        pro=(EditText)findViewById(R.id.pro);
        post=(EditText)findViewById(R.id.post);

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
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            File f = new File(picturePath);
            Bitmap my = BitmapFactory.decodeFile(f.getAbsolutePath());
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageURI(selectedImage);

            Toast.makeText(this,user+ picturePath, Toast.LENGTH_SHORT).show();


            //Intent ob=new Intent(this,);
            Backend db = new Backend(getApplicationContext());
            db.createDatabase(getApplicationContext());
            db.insertIntoPicture(user,addrs.getText().toString(),city.getText().toString(),pro.getText().toString(),post.getText().toString(), picturePath);
            db.close();



           // func(user,selectedImage);

        }
    }

        public void func(String user,Uri path)
        {
            Intent ob=new Intent(this,UserBackend.class);
           ob.putExtra("path",path);
            ob.putExtra("name",user);
            startActivity(ob);

        }


}
