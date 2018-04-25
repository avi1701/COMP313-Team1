package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class View_Property extends AppCompatActivity {

    private int STORAGE_PERMISSION_CODE = 23;

    LinearLayout layout;

    Spinner ss;

    int univid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__property);
        Intent ob = getIntent();

        layout = (LinearLayout)findViewById(R.id.propertylist);

        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Cursor cr = db.excuteMyQuery("select * from Property");
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

            Button open=new Button(this);
            open.setText("OPEN");
            layout.addView(open);

            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), PropertyDetails.class);
                    i.putExtra("id", univid);
                    startActivity(i);
                }
            });

        }

    }

}

