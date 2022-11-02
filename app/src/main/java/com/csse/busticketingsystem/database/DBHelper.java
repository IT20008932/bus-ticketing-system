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

        String SQL_CREATE_BUSES_TABLE =
                "CREATE TABLE "
                        + BusMaster.BusesT.TABLE_NAME +
                        " ("
                        + BusMaster.BusesT.COLUMN_NAME_BUS_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + BusMaster.BusesT.COLUMN_NAME_BUS_NUMBER +
                        " TEXT, "
                        + BusMaster.BusesT.COLUMN_NAME_DESCRIPTION +
                        " TEXT" + ")";

        String SQL_CREATE_SCHEDULES_TABLE =
                "CREATE TABLE "
                        + ScheduleMaster.SchedulesT.TABLE_NAME +
                        " ("
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_SCHEDULE_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_ROUTE_ID +
                        " TEXT, "
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_BUS_ID +
                        " TEXT, "
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_START_TIME +
                        " TEXT, "
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_END_TIME +
                        " TEXT, "
                        + ScheduleMaster.SchedulesT.COLUMN_NAME_STATUS +
                        " TEXT" + ")";

        //defining the sql query
        db.execSQL(SQL_CREATE_ROUTES_TABLE);//Execute the route table creation
        Log.d("workflow", "Routes table created successfully");
        db.execSQL(SQL_CREATE_BUSES_TABLE);//Execute the bus table creation
        Log.d("workflow", "Buses table created successfully");
        db.execSQL(SQL_CREATE_SCHEDULES_TABLE);//Execute the schedule table creation
        Log.d("workflow", "Schedules table created successfully");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("workflow", "DB Onupgrade method Called");
        db.execSQL("DROP TABLE IF EXISTS " + RouteMaster.RoutesT.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BusMaster.BusesT.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ScheduleMaster.SchedulesT.TABLE_NAME);

        //  Create tables again
        onCreate(db);
    }

    // Route CRUD methods

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addRoutes(String startloc, String endloc) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addRoutes method Called");
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

    // Bus CRUD methods

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addBuses(String busno, String desc) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addRoutes method Called");
        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values , where column names the key
        values.put(BusMaster.BusesT.COLUMN_NAME_BUS_NUMBER, busno);
        values.put(BusMaster.BusesT.COLUMN_NAME_DESCRIPTION, desc);

        long newRowID = db.insert(BusMaster.BusesT.TABLE_NAME, null, values); //Insert a new row and returning the primary
        //key values of the new row

        Log.d("workflow", "DB addBuses method call finished");

        return newRowID;
    }

    public List<String> getBuslist() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + BusMaster.BusesT.TABLE_NAME
                + " ORDER BY " +
                BusMaster.BusesT.COLUMN_NAME_BUS_ID;

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


    public int deleteBus(String busid) {
        Log.d("workflow", "DB delete bus method Called");

        SQLiteDatabase db = getReadableDatabase();
        String selection = BusMaster.BusesT.COLUMN_NAME_BUS_ID + " = ? ";
        String[] selectionArgs = {busid};


        int status = db.delete(BusMaster.BusesT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                      //selection clause
        );
        return status;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updateBus(String busid, String busno, String desc) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update route method Called");
        //  update route set is_default=0 where is_default=1
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(BusMaster.BusesT.COLUMN_NAME_BUS_ID, busid);
        values.put(BusMaster.BusesT.COLUMN_NAME_BUS_NUMBER, busno);
        values.put(BusMaster.BusesT.COLUMN_NAME_DESCRIPTION, desc);

        String selection = BusMaster.BusesT.COLUMN_NAME_BUS_ID + " = ? ";
        String[] selectionArgs = {busid};

        int count = db.update(BusMaster.BusesT.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Cursor readAllBuses() {
        Log.d("workflow", "DB read All Routes method Called");


        String query = "SELECT " + "*" + " From "
                + BusMaster.BusesT.TABLE_NAME;

        Log.d("workflow", query);

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

    // Schedule CRUD methods

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addSchedule(String routeId, String busId, String startTime, String endTime, String status) //enter all the parameter to be added to DB
    {
        Log.d("workflow", "DB addSchedules method Called");
        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values , where column names the key
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_ROUTE_ID, routeId);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_BUS_ID, busId);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_START_TIME, startTime);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_END_TIME, endTime);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_STATUS, status);

        long newRowID = db.insert(ScheduleMaster.SchedulesT.TABLE_NAME, null, values); //Insert a new row and returning the primary
        //key values of the new row

        Log.d("workflow", "DB addSchedules method call finished");

        return newRowID;
    }


    public int deleteSchedule(String scheduleId) {
        Log.d("workflow", "DB delete schedule method called");

        SQLiteDatabase db = getReadableDatabase();
        String selection = ScheduleMaster.SchedulesT.COLUMN_NAME_SCHEDULE_ID + " = ? ";
        String[] selectionArgs = {scheduleId};


        int status = db.delete(ScheduleMaster.SchedulesT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                      //selection clause
        );
        return status;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updateSchedule(String scheduleId, String routeId, String busId, String startTime, String endTime, String status) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update schedule method called");
        //  update route set is_default=0 where is_default=1
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_ROUTE_ID, routeId);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_BUS_ID, busId);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_START_TIME, startTime);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_END_TIME, endTime);
        values.put(ScheduleMaster.SchedulesT.COLUMN_NAME_STATUS, status);

        String selection = ScheduleMaster.SchedulesT.COLUMN_NAME_SCHEDULE_ID + " = ? ";
        String[] selectionArgs = {scheduleId};

        int count = db.update(ScheduleMaster.SchedulesT.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }

    public Cursor readAllSchedules() {
        Log.d("workflow", "DB readAllSchedules method called");


        String query = "SELECT " + ScheduleMaster.SchedulesT.COLUMN_NAME_SCHEDULE_ID + ", "
                + ScheduleMaster.SchedulesT.COLUMN_NAME_ROUTE_ID + ", "
                + ScheduleMaster.SchedulesT.COLUMN_NAME_BUS_ID + ", "
                + ScheduleMaster.SchedulesT.COLUMN_NAME_START_TIME + ", "
                + ScheduleMaster.SchedulesT.COLUMN_NAME_END_TIME + ","
                + ScheduleMaster.SchedulesT.COLUMN_NAME_STATUS + " From "
                + ScheduleMaster.SchedulesT.TABLE_NAME;

        Log.d("workflow", query);

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }
}
