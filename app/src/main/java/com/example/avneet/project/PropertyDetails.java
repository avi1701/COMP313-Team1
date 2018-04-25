package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class PropertyDetails extends AppCompatActivity {

    int univid;

    private int STORAGE_PERMISSION_CODE = 23;

    LinearLayout layout;
    private Firebase fref,fref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_details);

        Intent ob=getIntent();

        univid=ob.getIntExtra("id",0);

        Firebase.setAndroidContext(this);

        layout = (LinearLayout)findViewById(R.id.prodetails);

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Property where pic_id="+univid);
        while(cr.moveToNext()) {
            ImageView myimg = new ImageView(this);
            myimg.setLayoutParams(new android.view.ViewGroup.LayoutParams(350, 350));
            myimg.setMaxHeight(350);
            myimg.setMaxWidth(350);

            String path = cr.getString(6);

            univid = cr.getInt(0);

            Bitmap bmp = BitmapFactory.decodeFile(path);
            myimg.setImageBitmap(bmp);

            layout.addView(myimg);

            TextView pro_id = new TextView(this);
            TextView address = new TextView(this);
            TextView date = new TextView(this);
            TextView description = new TextView(this);
            TextView price = new TextView(this);
            TextView type = new TextView(this);

            pro_id.setText("PROPERTY ID: " + cr.getInt(0));
            address.setText("Address: " + cr.getString(2) + " " + cr.getString(3) + " " + cr.getString(4) + " " + cr.getString(5));
            date.setText("Date Added: " + cr.getString(7));
            description.setText("Description: " + cr.getString(8));
            price.setText("Price: " + cr.getInt(9));
            type.setText("TYPE: " + cr.getString(10));

            layout.addView(pro_id);
            layout.addView(address);
            layout.addView(date);
            layout.addView(description);
            layout.addView(price);
            layout.addView(type);

            univid=cr.getInt(0);

            userdet(univid);


            Button open=new Button(this);
            open.setText("DELETE");
            layout.addView(open);

            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Backend db = new Backend(getApplicationContext());
                    db.createDatabase(getApplicationContext());
                    db.deleteRow(univid,"Property");
                    firebasedelete(univid);
                    Toast.makeText(PropertyDetails.this,"Deleted",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), View_Property.class);
                    startActivity(i);
                }
            });

        }
    }

    public void userdet(int uid)
    {
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Users where user_id="+uid);
        while(cr.moveToNext())
        {
            TextView name = new TextView(this);
            TextView email = new TextView(this);
            TextView text = new TextView(this);
            TextView contact = new TextView(this);

            text.setText("USER DETAILS:");
            name.setText("Name: "+cr.getString(1)+" "+cr.getString(2));
            email.setText("Email: "+cr.getString(3));
            contact.setText("Contact: "+cr.getString(5));

            layout.addView(text);
            layout.addView(name);
            layout.addView(email);
            layout.addView(contact);
        }
    }

    public void firebasedelete(int id)
    {
        Firebase.setAndroidContext(this);
        fref=new Firebase("https://myexampledb-54576.firebaseio.com/");
        Query applesQuery = fref.child("Property").orderByChild("Property Id").equalTo(id);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
