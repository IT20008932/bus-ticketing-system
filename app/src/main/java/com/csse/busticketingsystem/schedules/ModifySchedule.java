package com.csse.busticketingsystem.schedules;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.csse.busticketingsystem.MainActivity;
import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.buses.Buses;
import com.csse.busticketingsystem.database.DBHelper;
import com.csse.busticketingsystem.routes.Routes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class ModifySchedule extends AppCompatActivity {
    TextInputEditText txt_sid,txt_rid,txt_bid,txt_startTime,txt_endTime,txt_status;

    String sid,rid,bid,startTime,endTime,status;
    String setStatusMsg;

    boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_schedule);
        Log.d("workflow","Modify Route onCreate method called");
        txt_sid=findViewById(R.id.inp_sid);
        txt_rid=findViewById(R.id.inp_rid);
        txt_bid=findViewById(R.id.inp_bid);
        txt_startTime=findViewById(R.id.inp_startTime);
        txt_endTime=findViewById(R.id.inp_endTime);
        txt_status=findViewById(R.id.inp_status);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.schedules);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.buses:
                        startActivity(new Intent(getApplicationContext()
                                , Buses.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.routes:
                        startActivity(new Intent(getApplicationContext()
                                , Routes.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedules:
                        return true;
                }
                Log.d("workflow","Modify Route Bottom Nav method  Called");
                return false;
            }
        });

    }


    void getAndSetIntentData() {

        Intent intent=new Intent();

        Log.d("workflow","Modify Schedule getAndSetIntentData method called");

        if(getIntent().hasExtra("sid") &&
                getIntent().hasExtra("rid") &&
                getIntent().hasExtra("bid") &&
                getIntent().hasExtra("startTime") &&
                getIntent().hasExtra("endTime") &&
                getIntent().hasExtra("status"))
        {


            sid = getIntent().getStringExtra("sid");
            rid = getIntent().getStringExtra("rid");
            bid = getIntent().getStringExtra("bid");
            startTime = getIntent().getStringExtra("startloc");
            endTime = getIntent().getStringExtra("endloc");
            status = getIntent().getStringExtra("status");



            //  Log.d("mvalies",rid);
            txt_sid.setText(sid);
            txt_rid.setText(rid);
            txt_bid.setText(bid);
            txt_startTime.setText(startTime);
            txt_endTime.setText(endTime);
            txt_status.setText(status);
        }
        else{
            Toast.makeText(this, "No data Available", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateSchedule(View view) {


        isfieldsvalidated = CheckAllFields();
        Log.d("workflow","updateSchedule method called");
        if (isfieldsvalidated) {

            DBHelper dbHelper = new DBHelper(this);

            int val = dbHelper.updateSchedule(txt_sid.getText().toString(),
                    txt_rid.getText().toString(),
                    txt_bid.getText().toString(),
                    txt_startTime.getText().toString(),
                    txt_endTime.getText().toString(),
                    txt_status.getText().toString());



            if (val == -1) {
                setStatusMsg = getString(R.string.msg_schedule_update_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_schedule_update_succesfull);
            }

            Intent intent = new Intent(this, Schedules.class).putExtra("status", setStatusMsg);
            startActivity(intent);

            Log.i("BTN Click", "Update schedule confirmation button clicked");
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow","Modify Route CheckAllFields  method  Called");

        //if values are changed pls change in add route as well
        int maxchar=10;
        double maxdistance=999.99;

        Log.d("workflow","Add Route CheckAllFields  method  Called");
        if (txt_rid.length() == 0) {
            txt_rid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_rid.length() == 0) {
            txt_rid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_bid.length() > maxchar) {
            txt_bid.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_bid.length() == 0) {
            txt_bid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_startTime.length() > maxchar) {
            txt_startTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_startTime.length() == 0) {
            txt_startTime.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_startTime.length() > maxchar) {
            txt_startTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_endTime.length() == 0) {
            txt_endTime.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_endTime.length() > maxchar) {
            txt_endTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_status.length() == 0) {
            txt_status.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_status.length() > maxchar) {
            txt_status.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

    public void deleteSchedule(View view){
        confirmDialog();
        Log.d("workflow", "deleteSchedule method called");
    }

    private void errorDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_oops));
        builder.setMessage((getString(R.string.label_route))
                +" "+
                rid
                +" "+
                (getString(R.string.msg_unable_delete))
                +".");
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    void confirmDialog() {

        Log.d("workflow","Modify Route confirmDialog  method  Called");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_are_u_sure));
        builder.setMessage((getString(R.string.msg_confirm_delete))
                +" "+
                (getString(R.string.label_route))
                +" "+
                rid
                +" ? "+
                (getString(R.string.msg_confirm_delete_canot_be_undone)));
        builder.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper=new DBHelper(ModifySchedule.this);
                        int val= dbHelper.deleteSchedule(txt_sid.getText().toString());
                        if (val == 1) {
                            setStatusMsg = getString(R.string.msg_schedule_delete_succesfull);

                        }
                        else {
                            setStatusMsg = getString(R.string.msg_schedule_delete_unsuccesfull);

                        }
                        Intent intent = new Intent(ModifySchedule.this,Schedules.class).putExtra("status", setStatusMsg);
                        startActivity(intent);
                        Log.i("BTN Click", "Update schedule confirmation button clicked");


                    }

                }

        );


        builder.setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}