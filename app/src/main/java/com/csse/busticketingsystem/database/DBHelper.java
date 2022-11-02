package com.csse.busticketingsystem.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }
    //change the DB version when upgrading the DB


    @Override
    public void onCreate(SQLiteDatabase db) {        //creating the table
        Log.d("workflow", "DB onCreate method Called");
        String SQL_CREATE_ROUTES_TABLE =
                "CREATE TABLE "
                        + RouteMaster.RoutesT.TABLE_NAME +
                        " ("
                        + RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + RouteMaster.RoutesT.COLUMN_NAME_START_LOCATION +
                        " TEXT, "
                        + RouteMaster.RoutesT.COLUMN_NAME_END_LOCATION +
                        " TEXT" + ")";


        //defining the sql query
        db.execSQL(SQL_CREATE_ROUTES_TABLE);//Execute the table creation
        Log.d("workflow", "Routes table created successfully");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("workflow", "DB Onupgrade method Called");
        db.execSQL("DROP TABLE IF EXISTS " + RouteMaster.RoutesT.TABLE_NAME);

        //  Create tables again
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeStamp() {
        Log.d("workflow", "DB gettimestamop method Called");

        LocalDateTime myDateobj = LocalDateTime.now();
        DateTimeFormatter myformatobj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String timeStamp = myDateobj.format(myformatobj);
        return timeStamp;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addRoutes(String startloc, String endloc) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addRoutes method Called");

        String timeadd = getTimeStamp();
        Log.d("workflow", "DB gettimpstamp method Called");

        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values , where column names the key
        values.put(RouteMaster.RoutesT.COLUMN_NAME_START_LOCATION, startloc);
        values.put(RouteMaster.RoutesT.COLUMN_NAME_END_LOCATION, endloc);

        long newRowID = db.insert(RouteMaster.RoutesT.TABLE_NAME, null, values); //Insert a new row and returning the primary
        //key values of the new row

        Log.d("workflow", "DB addRoutes method Called finished");

        return newRowID;
    }

    public List<String> getstartStoplocation() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + RouteMaster.RoutesT.TABLE_NAME
                + " ORDER BY " +
                RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1).substring(0, 1).toUpperCase() + cursor.getString(1).substring(1)
                        + " - " +
                        cursor.getString(2).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));//adding 2nd column data
                Log.d("workflow", cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getroutelist() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + RouteMaster.RoutesT.TABLE_NAME
                + " ORDER BY " +
                RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    public int deleteRoute(String routeid) {
        Log.d("workflow", "DB delete route method Called");

        SQLiteDatabase db = getReadableDatabase();
        String selection = RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID + " = ? ";
        String[] selectionArgs = {routeid};


        int status = db.delete(RouteMaster.RoutesT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                      //selection clause
        );
        return status;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updateRoute(String routeid, String startloc, String endloc) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update route method Called");
        //  update route set is_default=0 where is_default=1
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(RouteMaster.RoutesT.COLUMN_NAME_START_LOCATION, startloc);
        values.put(RouteMaster.RoutesT.COLUMN_NAME_END_LOCATION, endloc);

        String selection = RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID + " = ? ";
        String[] selectionArgs = {routeid};

        int count = db.update(RouteMaster.RoutesT.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Cursor readAllRoutes() {
        Log.d("workflow", "DB read All Routes method Called");


        String query = "SELECT " + RouteMaster.RoutesT.COLUMN_NAME_ROUTE_ID + ", "
                + RouteMaster.RoutesT.COLUMN_NAME_START_LOCATION + ", "
                + RouteMaster.RoutesT.COLUMN_NAME_END_LOCATION + " From "
                + RouteMaster.RoutesT.TABLE_NAME;

        Log.d("workflow", query);

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

}
