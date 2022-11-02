package com.csse.busticketingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper {

    private Context con;
    private SQLiteDatabase db;

    public DBHelper(Context con) {this.con = con;}

    public DBHelper OpenDB() {
        DBConnector dbCon = new DBConnector(con);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public boolean RegisterUser(User user){
        try {
            ContentValues cv = new ContentValues();
            cv.put("UserName",user.getUserName());
            cv.put("Email",user.getEmail());
            cv.put("Password",user.getPassword());

            db.insert("userInfo",null,cv);
            return true;
        } catch (Exception e) {
            Toast.makeText(con,e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<User> LoginUser(String UserName, String Password) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("Select * from userInfo where UserName= '"+UserName+"' and Password='"+Password+"'",null);
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setUserName(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    userList.add(user);
                }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Toast.makeText(con,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return userList;
    }
}
