package com.example.avneet.project;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.*;


public class View_prop_det extends AppCompatActivity {

    private ImageView img;
    private int p;
    private String ch;
    private TextView tx;
    private int userId;
    private GridView gridView;
    String u_id,pic_id;
    private int user_id,p_id;
    private String address,city,province,postal_code,path,date,description;

    private List<String> list= new ArrayList<String>();

    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prop_det);

        gridView = (GridView) findViewById(R.id.gridView1);

        //list=getAll();

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());
        Cursor cr = db.excuteMyQuery("select * from Property");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        if(cr.moveToFirst())
        {
            do
            {
                p_id=cr.getInt(0);
                pic_id=String.valueOf(p_id);
                user_id=cr.getInt(1);
                //u_id=String.valueOf(user_id);
                address=cr.getString(2);
                city=cr.getString(3);
                province=cr.getString(4);
                postal_code=cr.getString(5);
                path=cr.getString(6);
                date=cr.getString(7);
                description=cr.getString(8);
                list.add("User Id: "+String.valueOf(user_id));
                list.add("PropertyId: "+pic_id);
                list.add("Address: "+address);
                list.add("City: "+city);
                list.add("Province: "+province);
                list.add("Postal Code: "+postal_code);
                list.add("Date Posted: "+date);
                list.add("Description: "+description);
                list.add("Price: "+cr.getInt(9));
                list.add("Type: "+cr.getString(10));
                list.add("-----------------------------------------");
                //det(userId);
                //list.add("Price: "+p);
                //list.add("TYPE: "+ch);
            }while(cr.moveToNext());//Move the cursor to the next row.
        }

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)findViewById(R.id.cl)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
     }



    public String adduser(int id)
    {
        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());
        Cursor cr = db.excuteMyQuery("select email from Users where user_id="+id+"");
        cr.moveToNext();
        return cr.getString(0);
    }


}
