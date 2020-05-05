package com.example.sqlitedbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;



public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager.db";
    private static final String TABLE_NAME = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(sqLiteDatabase);
    }





     //// insertData Method

    public String insertData(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getContact()); // Contact Phone

        // Inserting Row

          long res=db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
           if(res == -1)
           {
             return "Failed";
           }
           else
           {
               return "SuccessFully Inserted";
           }
    }

   ///////read data from sqlite

    public ArrayList<Contact> readData()
    {
        ArrayList<Contact> contactsList=new ArrayList<>();

        String query="SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor=db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
           do {
             Contact contact=new Contact();
             contact.setId(Integer.parseInt(cursor.getString(0)));
             contact.setName(cursor.getString(1));
             contact.setContact(cursor.getString(2));
             contactsList.add(contact);

           }
           while (cursor.moveToNext());
        }
       return contactsList;
    }



   ///////delete by id
   public String deleteById(String id)
   {
       SQLiteDatabase db=this.getWritableDatabase();
       int re=db.delete(TABLE_NAME,KEY_ID+ " = ?",new String[] {id});
       Log.i("intValueHere",String.valueOf(re));
       if(re==-1) {
           return "Unsuccessfully Delete";
       }
       else
       {
           return "Successfully Delete";
       }
   }

}
