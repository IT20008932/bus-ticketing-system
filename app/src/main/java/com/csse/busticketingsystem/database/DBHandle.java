package com.example.myapplication22.Database;

import static com.example.myapplication22.Database.CustomerDetails.Customer.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandle extends SQLiteOpenHelper {

    public DBHandle(Context context) {
        super(context, "work_byte.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

//
    private static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + TABLE_NAME + "("
                    + CustomerDetails.Customer.first_name + " NOT NULL," + CustomerDetails.Customer.last_name + "  NOT NULL,"
                    + CustomerDetails.Customer.email + "  NOT NULL PRIMARY KEY," + CustomerDetails.Customer.mobile + "  NOT NULL,"
                    + CustomerDetails.Customer.password + " NOT NULL,"
                    + CustomerDetails.Customer.re_password + " NOT NULL,"  + CustomerDetails.Customer.address + " NOT NULL"+")";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public boolean insertData(String f_name, String l_name, String email, String m_number, String password, String re_password, String address) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(CustomerDetails.Customer.first_name, f_name);
        values.put(CustomerDetails.Customer.last_name, l_name);
        values.put(CustomerDetails.Customer.email, email);
        values.put(CustomerDetails.Customer.mobile, m_number);
        values.put(CustomerDetails.Customer.password, password);
        values.put(CustomerDetails.Customer.re_password, re_password);
        values.put(CustomerDetails.Customer.address, address);


// Insert the new row, returning the primary key value of the new row
        long result = db.insert(TABLE_NAME, String.valueOf(values), values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List readAllInfo(String s, String toString){
        SQLiteDatabase db = getReadableDatabase();



// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                CustomerDetails.Customer.first_name,
                CustomerDetails.Customer.last_name,
                CustomerDetails.Customer.email,
                CustomerDetails.Customer.mobile,
                CustomerDetails.Customer.address,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = CustomerDetails.Customer.email + " LIKE ?";
//        String[] selectionArgs = { email };

// How you want the results sorted in the resulting Cursor
        String sortOrder = CustomerDetails.Customer.email + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List useremails = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.email));
            useremails.add(user);

        }
        cursor.close();
        return useremails;
    }

    public List readAllInfo (String email){
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                CustomerDetails.Customer.first_name,
                CustomerDetails.Customer.last_name,
                CustomerDetails.Customer.email,
                CustomerDetails.Customer.mobile,
                CustomerDetails.Customer.password,
                CustomerDetails.Customer.re_password,
                CustomerDetails.Customer.address,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = CustomerDetails.Customer.email + " =?";
        String[] selectionArgs = { email };

// How you want the results sorted in the resulting Cursor
        String sortOrder = CustomerDetails.Customer.email + " DESC";

        Cursor cursor = db.query(
                TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String Emailv = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.email));
            String telev = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.mobile));
            String addressv = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.address));
            String pwordv = cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.password));
            String repwordv= cursor.getString(cursor.getColumnIndexOrThrow(CustomerDetails.Customer.re_password));
            userInfo.add(Emailv); //0
            userInfo.add(telev);//3
            userInfo.add(addressv);//4
            userInfo.add(pwordv);//5
            userInfo.add(repwordv);//6

        }
        cursor.close();
        return userInfo;
    }

    public Boolean updateInfo(String email, String address, String m_number, String password, String re_password){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(CustomerDetails.Customer.mobile, m_number);
        values.put(CustomerDetails.Customer.password, password);
        values.put(CustomerDetails.Customer.re_password, re_password);
        values.put(CustomerDetails.Customer.address, address);


// Which row to update, based on the title
        String selection = CustomerDetails.Customer.email + " LIKE ?";
        String[] selectionArgs = { email };

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count > 0 )
            return true;
        else
            return false;
    }




    public boolean checkUseremail (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from customers where email = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from customers where email = ? and password =?", new String[] {email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return  false;
    }

    //Delete Customer
    public boolean deleteCustomer(String email){
        SQLiteDatabase db=getWritableDatabase();
        return db.delete(TABLE_NAME, email + "=" + email, null) > 0;

    }


    //get single Customer
    public CustomerDetails getSingleCustomer(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(TABLE_NAME,new String[]{CustomerDetails.Customer.first_name,
                        CustomerDetails.Customer.last_name,CustomerDetails.Customer.email,CustomerDetails.Customer.mobile,
                        CustomerDetails.Customer.password,CustomerDetails.Customer.re_password,CustomerDetails.Customer.address,}
                ,CustomerDetails.Customer.userId+"=?",new String[]{String.valueOf(id)},null,null,null);
        CustomerDetails workerModel;
        if(cursor != null) {
            cursor.moveToFirst();
            workerModel = new CustomerDetails(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10));
            return workerModel;
        }
        return  null;
    }
}
