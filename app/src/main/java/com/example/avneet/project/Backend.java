package com.example.avneet.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Avneet on 3/25/2018.
 */

public class Backend extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "comp313.db";
    private static final int DATABASE_VERSION = 1;
    //
    private String tables[] = {"Users","Picture"};

    private static final String tableCreatorString[] =
            {

                    "CREATE TABLE Users (user_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                            ", firstname TEXT" +
                            ", lastname TEXT ," +
                            " email TEXT," +
                            " password TEXT," +"contact TEXT,"+
                            " role TEXT);",

                    "CREATE TABLE Picture (pic_id INTEGER PRIMARY KEY AUTOINCREMENT"+
                            ",user_id TEXT,"+
                            "address TEXT,"+
                            "city TEXT"+
                            "province TEXT"+
                            "postal_code TEXT"+
                            "path TEXT);"
            };


    public Backend(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Drop existing tables
        for (int i=0;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        //create them
        for (int i=0;i<tables.length;i++)
            db.execSQL(tableCreatorString[i]);
    }
    //create the database
    public void createDatabase(Context context)
    {
        SQLiteDatabase mDatabase;
        mDatabase = context.openOrCreateDatabase(
                DATABASE_NAME,
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
    }
    public void deleteDatabase(Context context)
    {
        context.deleteDatabase(DATABASE_NAME);
    }

    public void insertIntoUser(String first,String last, String email,String pass, String con ,String role)
    {
        ContentValues value = new ContentValues();
        value.put("firstname",first);
        value.put("lastname",last);
        value.put("email",email);
        value.put("password",pass);
        value.put("contact",con);
        value.put("role",role);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Users", null, value);
        db.close(); //close database connection
    }
    public void insertIntoPicture(String user,String add,String city,String prov,String post,String path)
    {
        ContentValues value = new ContentValues();
        value.put("user_id",user);
        value.put("address",add);
        value.put("city",city);
        value.put("province",prov);
        value.put("postal_code",post);
        value.put("path",path);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Picture", null, value);
        db.close(); //close database connection
    }

    public void deleteRow(int value,String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table+ " WHERE user_id='"+value+"'");
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean authenticate_user(String table, String username, String password)
    {
        String selectQuery = "SELECT  * FROM " + table + " where email = '"+username+"' and " + "password= '"+password+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToNext())
        {   return true;
        }
        else{
            return false;
        }
    }

    public Cursor excuteMyQuery(String q)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        return cursor;
    }
}

