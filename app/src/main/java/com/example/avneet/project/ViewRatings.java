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

public class ViewRatings extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;

    int rid;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ratings);

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        layout = (LinearLayout)findViewById(R.id.rating);

        //ImageView myimg=(ImageView)findViewById(R.id.imageView5);
        Cursor cr = db.excuteMyQuery("select * from Review");
        while(cr.moveToNext())
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
            finish.setText("OPEN");
            layout.addView(finish);

            rid=cr.getInt(0);

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Intent o=new Intent(ViewRatings.this,RatingsDetails.class);
                    o.putExtra("rev_id",rid);
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
}
