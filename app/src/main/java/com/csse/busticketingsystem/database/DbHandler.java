package com.example.myapplication22.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    public static final String TABLE_NAME="Bank";
    public static final String TABLE_NAME2="Worker";
    public static final String TABLE_NAME3="Hire";

    //column names
    public static final String BANK_ID= "bank_id";
    public static final String BANK_NAME="bank_name";
    public static final String HOLDER="account_holder";
    public static final String ACC_NO="account_no";
    public static final String ROU_NO="routing_no";

    //column names
    public static final String WORKER_ID= "worker_id";
    public static final String WORKER_NAME="worker_name";
    public static final String SALARY="salary";
    public static final String CATEGORY="category";

    //column names
    public static final String HIRE_ID= "hire_id";
    //public static final String WORKER_HIRE_ID= "worker_hire_id";
    public static final String CUSTOMER_NAME="customer_name";
    public static final String PHONE="phone";
    public static final String EMAIL="email";
    public static final String LOCATION="work_location";
    public static final String DATE="Starting_date";
    public static final String DURATION="Duration";
    public static final String DES="Description";



    public DbHandler(@Nullable Context context) {
        super(context, "payment.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //table creation query
        String TABLE_CREATE_QUERY="CREATE TABLE "+TABLE_NAME+" "+
                " ("
                +BANK_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                +BANK_NAME+ " TEXT,"
                +HOLDER+ " TEXT,"
                +ACC_NO+ " TEXT,"
                +ROU_NO+ " TEXT"+
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE_CREATE_QUERY);

        //table creation query
        String TABLE2_CREATE_QUERY="CREATE TABLE "+TABLE_NAME2+" "+
                " ("
                +WORKER_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                +WORKER_NAME+ " TEXT,"
                +SALARY+ " TEXT,"
                +CATEGORY+ " TEXT "+
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE2_CREATE_QUERY);

        //table creation query
        String TABLE3_CREATE_QUERY="CREATE TABLE "+TABLE_NAME3+" "+
                " ("
                +HIRE_ID+" INTEGER PRIMARY KEY "+" AUTOINCREMENT,"
                +CUSTOMER_NAME+ " TEXT,"
                +PHONE+ " TEXT,"
                +EMAIL+ " TEXT,"
                +LOCATION+ " TEXT,"
                +DATE+ " TEXT, "
                +DURATION+ " TEXT,"
                +DES+ " TEXT "+
                // +" FOREIGN KEY  ("+WORKER_HIRE_ID+") REFERENCES "+TABLE_NAME2+"("+WORKER_ID+")" +
                ");";//sql semi colon and java semi colon

        //run the query
        db.execSQL(TABLE3_CREATE_QUERY);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//when change the version drop exists table
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
        //drop table if exited
        db.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(db);

        //when change the version drop exists table
        String DROP_TABLE2_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        //drop table if exited
        db.execSQL(DROP_TABLE2_QUERY);
        //create table again
        onCreate(db);

        //when change the version drop exists table
        String DROP_TABLE3_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME3;
        //drop table if exited
        db.execSQL(DROP_TABLE3_QUERY);
        //create table again
        onCreate(db);


    }

    //insert worker
    public void addWorkerDetails(WorkerModel workerModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(WORKER_NAME,workerModel.getWorkerName());
        contentValues.put(SALARY,workerModel.getSalary());
        contentValues.put(CATEGORY,workerModel.getCategory());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }

//    public boolean insertData(String name,int salary,String category){
//        //write data into db
//        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
//
//        //create object to store all inserted data
//        ContentValues contentValues=new ContentValues();
//
//        //colomn names and values
//        contentValues.put(WORKER_NAME,name);
//        contentValues.put(SALARY,salary);
//        contentValues.put(CATEGORY,category);
//
//        //save data to tables.use null to whenever colomn hasn't a value
//       long result= sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);//if error this insrt method return -1.Otherwise return with new row
//        if(result==-1){
//            return false;
//        }else{
//            return true;
//        }
//    }

    //insert bank details
    public void addBankDetails(BankModel bankModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(BANK_NAME,bankModel.getBank_name());
        contentValues.put(HOLDER,bankModel.getAccount_holder());
        contentValues.put(ACC_NO,bankModel.getAccount_no());
        contentValues.put(ROU_NO,bankModel.getRouting_no());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }

    //insert hire
    public void addHireDetails(HireModel hireModel){

        //write data into db
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();

        //create object to store all inserted data
        ContentValues contentValues=new ContentValues();

        //colomn names and values
        contentValues.put(CUSTOMER_NAME,hireModel.getCustomerName());
        contentValues.put(PHONE,hireModel.getPhone());
        contentValues.put(EMAIL,hireModel.getEmail());
        contentValues.put(LOCATION,hireModel.getLocation());
        contentValues.put(DATE,hireModel.getDate());
        contentValues.put(DURATION,hireModel.getDuration());
        contentValues.put(DES,hireModel.getDescription());

        //save data to tables.use null to whenever colomn hasn't a value
        sqLiteDatabase.insert(TABLE_NAME3,null,contentValues);

        //close database
        sqLiteDatabase.close();
    }



    //get all workers
    public List<WorkerModel > getAllWorkers(String c){
        List<WorkerModel> workers=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        //String query=" SELECT * FROM "+TABLE_NAME2 ;
        //Cursor cursor=db.rawQuery(query,null);
        Cursor cursor=db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},CATEGORY+ "=?",new String[]{c},null,null,null);

        //check whether the table has data.go to first raw.if empty return false
        if(cursor.moveToFirst()) {
            do {
                //create empty workerModel object
                WorkerModel workerModel=new WorkerModel();

                //set values
                workerModel.setWorkerId(cursor.getInt(0));
                workerModel.setWorkerName(cursor.getString(1));
                workerModel.setSalary(cursor.getInt(2));
                workerModel.setCategory(cursor.getString(3));


                //add todoModel to the list type object
                workers.add(workerModel);

            } while(cursor.moveToNext());
        }
        return workers;
    }


    //get all hires
    public List<HireModel > getAllHireDetail(){
        List<HireModel> hires=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+TABLE_NAME3 ;
        Cursor cursor=db.rawQuery(query,null);
        //Cursor cursor=db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},CATEGORY+ "=?",new String[]{c},null,null,null);

        //check whether the table has data.go to first raw.if empty return false
        if(cursor.moveToLast()) {
            do {
                //create empty workerModel object
                HireModel hireModel=new HireModel();

                //set values
                hireModel.setId(cursor.getInt(0));
                hireModel.setCustomerName(cursor.getString(1));
                hireModel.setPhone(cursor.getInt(2));
                hireModel.setEmail(cursor.getString(3));
                hireModel.setLocation(cursor.getString(4));
                hireModel.setDate(cursor.getString(5));
                hireModel.setDuration(cursor.getInt(6));
                hireModel.setDescription(cursor.getString(7));



                //add todoModel to the list type object
                hires.add(hireModel);

            } while(cursor.moveToNext());
        }
        return hires;
    }



    //get all from two tables
//    public List<HireModel> getAll(){
//
//        SQLiteDatabase db=getReadableDatabase();
//        String query="SELECT " + "h." + CUSTOMER_NAME +", "+ "h." + LOCATION +", " + "h." + DATE +", " + "h." + DURATION +", " +
//                " w." + WORKER_NAME + ", " + " w." + SALARY
//                + " FROM " + TABLE_NAME3 + " h "
//                + " JOIN " + TABLE_NAME2 + " w "
//                + " ON h." + WORKER_HIRE_ID  + " = w." + WORKER_ID;
//        Cursor cursor=db.rawQuery(query,null);
//
//        //declare variable to hold the result
//        List<HireModel> allHires=new ArrayList<>();
//
//        //check whether the table has data.go to first raw.if empty return false
//        if(cursor.moveToFirst()) {
//            do {
//
//                //get hire data
//                String hCustomer=cursor.getString(0);
//                String hLocation=cursor.getString(1);
//                String hDate=cursor.getString(2);
//                int hDuration= cursor.getInt(3);
//
//                HireModel hireModel=new HireModel();
//                hireModel.setCustomerName(hCustomer);
//                hireModel.setLocation(hLocation);
//                hireModel.setDate(hDate);
//                hireModel.setDuration(hDuration);
//
//                //get worker data
//                String wworker= cursor.getString(4);
//                int wsal=cursor.getInt(5);
//
//                WorkerModel workerModel=new WorkerModel();
//                workerModel.setWorkerName(wworker);
//                workerModel.setSalary(wsal);
//
//                List<WorkerModel> allWorker=new ArrayList<>();
//
//                hireModel.setWorker(allWorker);
//
//            } while(cursor.moveToNext());
//        }
//        return allHires;
//    }

    //get singleHire
    public HireModel getSingleHire(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(TABLE_NAME3,new String[]{HIRE_ID,CUSTOMER_NAME,PHONE,EMAIL,LOCATION,DATE,DURATION,DES},HIRE_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        //inside this String array need gives only the coloums that need to display
        HireModel hireModel;
        if(cursor != null) {
            cursor.moveToLast();
            hireModel = new HireModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7));
            return hireModel;
        }
        return  null;
    }

    //get single Worker
    public WorkerModel getSingleWorker(int id){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=  db.query(TABLE_NAME2,new String[]{WORKER_ID,WORKER_NAME,SALARY,CATEGORY},WORKER_ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        WorkerModel workerModel;
        if(cursor != null) {
            cursor.moveToFirst();
            workerModel = new WorkerModel(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3));

            return workerModel;
        }
        return  null;
    }

    /*update*/
//    public boolean updateData(String id,String name,int salary,String category){
//        SQLiteDatabase db=getWritableDatabase();
//
//        //create object to store all inserted data
//        ContentValues contentValues=new ContentValues();
//
//        contentValues.put(WORKER_ID,id);
//        contentValues.put(WORKER_NAME,name);
//        contentValues.put(SALARY,salary);
//        contentValues.put(CATEGORY,category);
//
//        db.update(TABLE_NAME2,contentValues,"worker_id = ?",new String[]{id});//worker_id is colomn name
//return true;
//    }


//get single raw
//public WorkerModel getSingleUserInfo(int id){
//
//    SQLiteDatabase db=getWritableDatabase();
//    Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + WORKER_ID + "=?" + new String[]{String.valueOf(id)},null);
//    cursor.moveToFirst();
//
//    //setting related user info in CustomerDetails Object
//   WorkerModel workerModel=new WorkerModel();
    //user.setUserId(cursor.getInt(cursor.getColumnIndexCOLUMN_USER_ID ));
    //user.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME));
//    user.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME ));
//    user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL ));
//    user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE_NUMBER ));
//    user.setBirthday(cursor.getString(cursor.getColumnIndex(COLUMN_USER_BARTH_DAY ));
//    user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD));
//    user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER ));

//    workerModel.setWorkerId(cursor.getColumnIndex(WORKER_ID));
//    workerModel.setWorkerName(cursor.getString(cursor.getColumnIndex(WORKER_NAME)));
//    workerModel.setSalary(cursor.getColumnIndex(SALARY));
//    workerModel.setCategory(cursor.getString(cursor.getColumnIndex(CATEGORY)));
//
//    //close cursor & database
//    cursor.close();
//    db.close();
//
//    return workerModel;

    //delete hire
    public void deleteHire(String email){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TABLE_NAME3,EMAIL +"=?",new String[]{email});//convert int id to string
        db.close();
    }

    //update todo
//    public int updateHire(HireModel hireModel){
//        SQLiteDatabase db=getWritableDatabase();
//
//        //create object to store all inserted data
//        ContentValues contentValues=new ContentValues();
//
//        //colomn names and values
//        contentValues.put(CUSTOMER_NAME,hireModel.getCustomerName());
//        contentValues.put(PHONE,hireModel.getPhone());
//        contentValues.put(LOCATION,hireModel.getLocation());
//        contentValues.put(DATE,hireModel.getDate());
//        contentValues.put(DURATION,hireModel.getDuration());
//        contentValues.put(DES,hireModel.getDescription());
//
//        int status=db.update(TABLE_NAME3,contentValues,EMAIL +"=?",new String[]{hireModel.getEmail()});
//        db.close();
//        return status;//return no of updated rows
//    }

    //update hire
    public boolean updateHireDetail(String email,String cusname,String phone,String loc,String des,String dur,String s_date){
        SQLiteDatabase db=getWritableDatabase();

        //create object to store all updated data
        ContentValues contentValues=new ContentValues();

        contentValues.put(EMAIL,email);
        contentValues.put(CUSTOMER_NAME,cusname);
        contentValues.put(PHONE,phone);
        contentValues.put(LOCATION,loc);
        contentValues.put(DES,des);
        contentValues.put(DURATION,dur);
        contentValues.put(DATE,s_date);

        db.update(TABLE_NAME3,contentValues,"email = ?",new String[]{email});

        return true;

    }


}
