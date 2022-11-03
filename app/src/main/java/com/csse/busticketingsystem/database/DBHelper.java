package com.example.myapplication22.Database;


//import static com.example.work_byte.Database.UserDetails.User.TABLE_NAME;
//import static com.example.work_byte.Database.UserDetails.User.category;
//import static com.example.work_byte.Database.UserDetails.User.email;
//import static com.example.work_byte.Database.UserDetails.User.experience;
//import static com.example.work_byte.Database.UserDetails.User.first_name;
//import static com.example.work_byte.Database.UserDetails.User.last_name;
//import static com.example.work_byte.Database.UserDetails.User.mobile;
//import static com.example.work_byte.Database.UserDetails.User.workArea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
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

    private static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + UserDetails.User.TABLE_NAME + "("
                    +UserDetails.User.worker_id + " INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                    + UserDetails.User.first_name + " NOT NULL," + UserDetails.User.last_name + "  NOT NULL,"
                    + UserDetails.User.email + "  NOT NULL ," + UserDetails.User.mobile + "  NOT NULL,"
                    + UserDetails.User.workArea + " NOT NULL," + UserDetails.User.password + " NOT NULL,"
                    + UserDetails.User.re_password + " NOT NULL,"  + UserDetails.User.address + " NOT NULL,"
                    + UserDetails.User.category + " NOT NULL," + UserDetails.User.experience + " NOT NULL,"
                    + UserDetails.User.salary + " NOT NULL,"
                    + UserDetails.User.pro_image  +")";
                    //+ UserDetails.User.pro_image + " NOT NULL" +")";


    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UserDetails.User.TABLE_NAME;



    public boolean insertData(String f_name, String l_name, String email, String m_number, String work_area, String password, String re_password, String address, String experience,String salary, String category) {
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UserDetails.User.first_name, f_name);
        values.put(UserDetails.User.last_name, l_name);
        values.put(UserDetails.User.email, email);
        values.put(UserDetails.User.mobile, m_number);
        values.put(UserDetails.User.workArea, work_area);
        values.put(UserDetails.User.password, password);
        values.put(UserDetails.User.re_password, re_password);
        values.put(UserDetails.User.address, address);
        values.put(UserDetails.User.experience, experience);
        values.put(UserDetails.User.salary, salary);
        values.put(UserDetails.User.category, category);




// Insert the new row, returning the primary key value of the new row
        long result = db.insert(UserDetails.User.TABLE_NAME, String.valueOf(values), values);
        if (result == -1)
            return false;
        else
            return true;
    }

    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();



// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserDetails.User.first_name,
                UserDetails.User.last_name,
                UserDetails.User.email,
                UserDetails.User.mobile,
                UserDetails.User.workArea,
                UserDetails.User.address,
                UserDetails.User.experience
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserDetails.User.email + " LIKE ?";
//        String[] selectionArgs = { email };

// How you want the results sorted in the resulting Cursor
        String sortOrder = UserDetails.User.email + " DESC";

        Cursor cursor = db.query(
                UserDetails.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List useremails = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.email));
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
                UserDetails.User.first_name,
                UserDetails.User.last_name,
                UserDetails.User.email,
                UserDetails.User.mobile,
                UserDetails.User.workArea,
                UserDetails.User.address,
                UserDetails.User.experience
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserDetails.User.email + " =?";
        String[] selectionArgs = { email };

// How you want the results sorted in the resulting Cursor
        String sortOrder = UserDetails.User.email + " DESC";

        Cursor cursor = db.query(
                UserDetails.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String Emailv = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.email));
            String workAreav = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.workArea));
            String Experiencev = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.experience));
            String telev = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.mobile));
            String addressv = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.address));
            String pwordv = cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.password));
            String repwordv= cursor.getString(cursor.getColumnIndexOrThrow(UserDetails.User.re_password));
            userInfo.add(Emailv); //0
            userInfo.add(workAreav);//1
            userInfo.add(Experiencev);//2
            userInfo.add(telev);//3
            userInfo.add(addressv);//4
            userInfo.add(pwordv);//5
            userInfo.add(repwordv);//6

        }
        cursor.close();
        return userInfo;
    }

    public Boolean updateInfo (String email, String work_area, String m_number, String password, String re_password, String address, String experience){
        SQLiteDatabase db = getWritableDatabase();

// New value for one column
        ContentValues values = new ContentValues();
        values.put(UserDetails.User.workArea, work_area);
        values.put(UserDetails.User.mobile, m_number);
        values.put(UserDetails.User.password, password);
        values.put(UserDetails.User.re_password, re_password);
        values.put(UserDetails.User.address, address);
        values.put(UserDetails.User.experience, experience);


// Which row to update, based on the title
        String selection = UserDetails.User.email + " LIKE ?";
        String[] selectionArgs = { email };

        int count = db.update(
                UserDetails.User.TABLE_NAME,
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
        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[] {email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password =?", new String[] {email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return  false;
    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //get all workers as list when click category
    public List<UserDetails> getAllWorkers(String category){
    List<UserDetails> workers=new ArrayList<>();
    SQLiteDatabase db=getReadableDatabase();
    //String query=" SELECT * FROM "+UserDetails.User.TABLE_NAME;
    //Cursor cursor=db.rawQuery(query,null);
    Cursor cursor=db.query(UserDetails.User.TABLE_NAME,new String[]{UserDetails.User.worker_id,UserDetails.User.first_name,
            UserDetails.User.last_name,UserDetails.User.email,UserDetails.User.mobile,
            UserDetails.User.workArea,UserDetails.User.password,UserDetails.User.re_password,UserDetails.User.address,
            UserDetails.User.experience,UserDetails.User.salary,UserDetails.User.category},UserDetails.User.category+ "=?",new String[]{category},null,null,null);

    //check whether the table has data.go to first raw.if empty return false
    if(cursor.moveToFirst()) {
        do {
            //create empty workerModel object
            UserDetails workerModel=new UserDetails();

            //set values
            workerModel.setUserId(cursor.getInt(0));
            workerModel.setFirst_name(cursor.getString(1));
            workerModel.setLast_name(cursor.getString(2));
            workerModel.setEmail(cursor.getString(3));
            workerModel.setMobile(cursor.getString(4));
            workerModel.setWorkArea(cursor.getString(5));
            workerModel.setPassword(cursor.getString(6));
            workerModel.setRepassword(cursor.getString(7));
            workerModel.setAddress(cursor.getString(8));
            workerModel.setExperience(cursor.getString(9));
            workerModel.setSalary(cursor.getInt(10));
            workerModel.setCategory(cursor.getString(11));


            //add todoModel to the list type object
            workers.add(workerModel);

        }while(cursor.moveToNext());
    }
    return workers;
}


    public List<UserDetails> getAllWorkers(){
        List<UserDetails> workers=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+UserDetails.User.TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        //Cursor cursor=db.query(UserDetails.User.TABLE_NAME,new String[]{UserDetails.User.worker_id,UserDetails.User.first_name,
                //UserDetails.User.last_name,UserDetails.User.email,UserDetails.User.mobile,
                //UserDetails.User.workArea,UserDetails.User.password,UserDetails.User.re_password,UserDetails.User.address,
                //UserDetails.User.experience,UserDetails.User.category},UserDetails.User.category+ "=?",new String[]{category},null,null,null);

        //check whether the table has data.go to first raw.if empty return false
        if(cursor.moveToFirst()) {
            do {
                //create empty workerModel object
                UserDetails workerModel=new UserDetails();

                //set values
                workerModel.setUserId(cursor.getInt(0));
                workerModel.setFirst_name(cursor.getString(1));
                workerModel.setLast_name(cursor.getString(2));
                workerModel.setEmail(cursor.getString(3));
                workerModel.setMobile(cursor.getString(4));
                workerModel.setWorkArea(cursor.getString(5));
                workerModel.setPassword(cursor.getString(6));
                workerModel.setRepassword(cursor.getString(7));
                workerModel.setAddress(cursor.getString(8));
                workerModel.setExperience(cursor.getString(9));
                workerModel.setCategory(cursor.getString(10));
                workerModel.setSalary(cursor.getInt(11));


                //add todoModel to the list type object
                workers.add(workerModel);

            }while(cursor.moveToNext());
        }
        return workers;
    }
    //get single Worker
    public UserDetails getSingleWorkerbyEmail(String email){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(UserDetails.User.TABLE_NAME,new String[]{UserDetails.User.worker_id,UserDetails.User.first_name,
                UserDetails.User.last_name,UserDetails.User.email,UserDetails.User.mobile,
                UserDetails.User.workArea,UserDetails.User.password,UserDetails.User.re_password,UserDetails.User.address,
                UserDetails.User.category,UserDetails.User.experience,UserDetails.User.salary},UserDetails.User.email+"=?",new String[]{email},null,null,null);

        UserDetails workerModel;
        if(cursor != null) {
            cursor.moveToFirst();
            workerModel = new UserDetails(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getInt(11));

            return workerModel;
        }
        return  null;
    }

//    public UserDetails getSingleWorkerbyId(int id){
//        SQLiteDatabase db=getWritableDatabase();
//        Cursor cursor=  db.query(UserDetails.User.TABLE_NAME,new String[]{UserDetails.User.worker_id,UserDetails.User.first_name,
//                UserDetails.User.last_name,UserDetails.User.email,UserDetails.User.mobile,
//                UserDetails.User.workArea,UserDetails.User.password,UserDetails.User.re_password,UserDetails.User.address,
//                UserDetails.User.experience,UserDetails.User.salary,UserDetails.User.category},UserDetails.User.worker_id+"=?",new String[]{String.valueOf(id)},null,null,null);
//
//        UserDetails workerModel;
//        if(cursor != null) {
//            cursor.moveToFirst();
//            workerModel = new UserDetails(cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getString(5),
//                    cursor.getString(6),
//                    cursor.getString(7),
//                    cursor.getString(8),
//                    cursor.getString(9),
//                    cursor.getInt(10),
//                    cursor.getString(11));
//
//            return workerModel;
//        }
//        return  null;
    }



