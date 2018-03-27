package com.example.avneet.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class Delete extends AppCompatActivity {

    private Firebase fref;
    private int userId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        userId = getIntent().getExtras().getInt("id",1);

        Backend db = new Backend(getApplicationContext());
        db.createDatabase(getApplicationContext());

        Toast.makeText(this,userId+" Deleted",Toast.LENGTH_SHORT).show();

        db.deleteRow(userId,"Users");

        Firebase.setAndroidContext(this);
        fref=new Firebase("https://myexampledb-54576.firebaseio.com/");
        Query applesQuery = fref.child("Users").orderByChild("User Id").equalTo(userId);

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

        Intent ob=new Intent(this,View_Users.class);
        startActivity(ob);
    }
}
