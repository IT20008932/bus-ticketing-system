package com.example.myapplication22.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteMyWorkHandler extends android.database.sqlite.SQLiteOpenHelper {


    public SQLiteMyWorkHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String itemname, byte[] image){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO WORK VALUES (NULL, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, itemname);
        statement.bindBlob(2, image);


        statement.executeInsert();

    }

    //Updating data into sqlite
    public void updateData(String itemname, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE WORK SET name = ?, image = ?  WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, itemname);
        statement.bindBlob(2, image);
        statement.bindDouble(3, (double)id);

        statement.execute();
        database.close();
    }


    //Deleting data in sqlite with id field
    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM WORK WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }


    //get all data from database
    public Cursor getData(String sql){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}