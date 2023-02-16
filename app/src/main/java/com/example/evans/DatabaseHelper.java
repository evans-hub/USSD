package com.example.ussd;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "USSD";
    //database version
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_ussd";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table
        query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT)";
        db.execSQL(query);
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
public void create(){
    String query;
    SQLiteDatabase db = null;
    //creating table
    query = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY, Title TEXT, Description TEXT)";
    db.execSQL(query);
}
    
    //add the new
    public void addUSSD(String title, String des) {
        SQLiteDatabase sqLiteDatabase = this .getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null , values);
        //close database connection
        sqLiteDatabase.close();
    }

    //get the all
    public ArrayList<Model> getUSSDs() {
        ArrayList<Model> arrayList = new ArrayList<>();

        // select all query
        String select_query= "SELECT *FROM " + TABLE_NAME;

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setID(cursor.getString(0));
                model.setTitle(cursor.getString(1));
                model.setDes(cursor.getString(2));
                arrayList.add(model);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }
    //search data
    public ArrayList<Model> search(String query)
    {
      /*  Cursor cusror;

        cursor=db.rawQuery("SELECT * FROM "+ DB_NAME + " WHERE "
                + DB_NAME.id + " = " + DB_NAME.Id + " AND " + DB_NAME.Title +
                " LIKE  '"+search.getText()+"%'");*/
        ArrayList<Model> arrayList=new ArrayList<>();
        String select_query="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(select_query,null);
        if (cursor.moveToFirst()){
            do {
               Model mode=new Model();
               mode.setID(cursor.getString(0));
               mode.setTitle(cursor.getString(1));
               mode.setDes(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        return  arrayList;
    }

    //delete
    public void delete(String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=" + ID, null);
        sqLiteDatabase.close();
    }

    //update
    public void updateUSSD(String title, String des, String ID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put("Title", title);
        values.put("Description", des);
        //updating row
        sqLiteDatabase.update(TABLE_NAME, values, "ID=" + ID, null);
        sqLiteDatabase.close();
    }
}