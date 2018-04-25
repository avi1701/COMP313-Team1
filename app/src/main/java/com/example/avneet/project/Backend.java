package com.example.avneet.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by Avneet on 3/25/2018.
 */

public class Backend extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "comp313.db";
    private static final int DATABASE_VERSION = 1;
    //
    private String tables[] = {"Users","Picture","Review"};

    private static final String tableCreatorString[] =
            {

                    "CREATE TABLE Users (user_id INTEGER PRIMARY KEY AUTOINCREMENT " +
                            ", firstname TEXT" +
                            ", lastname TEXT ," +
                            " email TEXT," +
                            " password TEXT," +
                            "contact TEXT,"+
                            " role TEXT,"+
                            "udate TEXT"+
                            ");",

                    "CREATE TABLE Property (pic_id INTEGER PRIMARY KEY AUTOINCREMENT"+
                            ",user_id INTEGER,"+
                            "address TEXT,"+
                            "city TEXT,"+
                            "province TEXT,"+
                            "postal_code TEXT,"+
                            "path TEXT,"+
                            "date TEXT,"+
                            "description TEXT ,"+
                            "price INTEGER,"+
                            "type TEXT"+
                            ");",

                    "CREATE TABLE Review (r_id INTEGER PRIMARY KEY AUTOINCREMENT"+
                            ",user_id INTEGER,"+
                            "pic_id INTEGER,"+
                            "review TEXT,"+
                            "rating INTEGER,"+
                            "date DATETIME,"+
                            "person TEXT"+
                            ");"

            };


    public Backend(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
         for (int i=0;i<tables.length;i++)
            db.execSQL("DROP TABLE IF EXISTS " + tables[i]);
        for (int i=0;i<tables.length;i++)
            db.execSQL(tableCreatorString[i]);
    }

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

    public void insertIntoUser(String first,String last, String email,String pass, String con ,String role,String d)
    {
        ContentValues value = new ContentValues();
        value.put("firstname",first);
        value.put("lastname",last);
        value.put("email",email);
        value.put("password",pass);
        value.put("contact",con);
        value.put("role",role);
        value.put("udate",d);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Users", null, value);
        db.close();
    }

    public void insertIntoPicture(int user, String add, String city, String prov, String post, String desc, String d,int p,String ch,String pat)
    {
        ContentValues value = new ContentValues();
        value.put("user_id",user);
        value.put("address",add);
        value.put("city",city);
        value.put("province",prov);
        value.put("postal_code",post);
        value.put("path",pat);
        value.put("description",desc);
        value.put("date",d);
        value.put("price",p);
        value.put("type",ch);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Property", null, value);
        db.close();
    }

    public void insertIntoReview(int picid, int user,  String review,int rate, String date,String person)
    {
        ContentValues value = new ContentValues();
        value.put("user_id",user);
        value.put("pic_id",picid);
        value.put("review",review);
        value.put("rating",rate);
        value.put("date",date);
        value.put("person",person);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Review", null, value);
        db.close();
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
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

