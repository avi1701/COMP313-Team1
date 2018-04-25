package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RatingsDetails extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;

    private Firebase fref;

    int rid;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings_details);

        Intent ob=getIntent();
        Firebase.setAndroidContext(this);

        rid=ob.getIntExtra("rev_id",0);
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        layout = (LinearLayout)findViewById(R.id.ratingdet);

        Cursor cr = db.excuteMyQuery("select * from Review where r_id="+rid);
        while (cr.moveToNext())
        {
            TextView id=new TextView(this);
            TextView pid=new TextView(this);
            TextView uid=new TextView(this);
            TextView review=new TextView(this);
            TextView ratings=new TextView(this);
            TextView date=new TextView(this);
            TextView person=new TextView(this);

            id.setText("ID: "+cr.getInt(0));
            uid.setText("USERID: "+cr.getInt(1));
            pid.setText("PROPERTY ID:"+cr.getInt(2));
            review.setText("REVIEW: "+cr.getString(3));
            ratings.setText("Rating: "+cr.getInt(4));
            date.setText("Posted on Date: "+cr.getString(5));
            person.setText("Posted By: "+cr.getString(6));

            property(cr.getInt(2));
            layout.addView(id);
            layout.addView(uid);
            layout.addView(pid);
            layout.addView(review);
            layout.addView(ratings);
            layout.addView(date);
            layout.addView(person);
            Button finish=new Button(this);
            finish.setText("DELETE");
            layout.addView(finish);

            rid=cr.getInt(0);

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Backend db = new Backend(getApplicationContext());
                    db.createDatabase(getApplicationContext());
                    db.deleteRow(rid,"Review");
                    firebasedelete(rid);
                    Toast.makeText(RatingsDetails.this,"Deleted",Toast.LENGTH_SHORT).show();

                    Intent o=new Intent(RatingsDetails.this,AdminActivity.class);
                    startActivity(o);
                }
            });
        }
   }

    public void property(int id)
    {
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());
        Cursor cr = db.excuteMyQuery("select * from Property where pic_id="+id+"");
        while (cr.moveToNext())
        {
            ImageView myimg = new ImageView(this);
            myimg.setLayoutParams(new android.view.ViewGroup.LayoutParams(350, 350));
            myimg.setMaxHeight(350);
            myimg.setMaxWidth(350);

            String path = cr.getString(6);

            Bitmap bmp = BitmapFactory.decodeFile(path);
            myimg.setImageBitmap(bmp);

            layout.addView(myimg);
        }

    }

    public void firebasedelete(int rid)
    {
        Firebase.setAndroidContext(this);
        fref=new Firebase("https://myexampledb-54576.firebaseio.com/");
        Query applesQuery = fref.child("Comments").orderByChild("Review ID").equalTo(rid);

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
