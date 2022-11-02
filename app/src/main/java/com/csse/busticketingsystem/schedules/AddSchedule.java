package com.csse.busticketingsystem.schedules;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.csse.busticketingsystem.MainActivity;
import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.buses.Buses;
import com.csse.busticketingsystem.database.DBHelper;
import com.csse.busticketingsystem.routes.Routes;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddSchedule extends AppCompatActivity {
    EditText sid, rid, bid, startTime, endTime, status;
    boolean isfieldsvalidated=false;  //check all field validations
    String setStatusMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        Log.d("workflow","Add Schedule onCreate method called");
        rid=findViewById(R.id.inp_rid);
        bid=findViewById(R.id.inp_bus_id);
        startTime=findViewById(R.id.inp_startTime);
        endTime=findViewById(R.id.inp_endTime);
        status=findViewById(R.id.inp_status);

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
                Log.d("workflow","Add Schedule Bottom Navigation method called");
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addSchedule(View view) {
        Log.d("workflow","Add Route addSchedule method called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addSchedule(rid.getText().toString(),
                    bid.getText().toString(),
                    startTime.getText().toString(),
                    endTime.getText().toString(),
                    status.getText().toString());

            if (val == -1) {
                setStatusMsg = getString(R.string.msg_schedule_add_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_schedule_add_succesfull);
            }

            Intent intent = new Intent(this, Schedules.class).putExtra("status", setStatusMsg);
            startActivity(intent);
            Log.i("BTN Click", "Add route Confirmation button clicked");
        }
    }

    public boolean CheckAllFields() {

        //if values are changed pls change in modify route as well

        int maxchar=50;

        Log.d("workflow","Add Schedule CheckAllFields method called");
        if (rid.length() == 0) {
            rid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (rid.length() == 0) {
            rid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (bid.length() > maxchar) {
            bid.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (bid.length() == 0) {
            bid.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (startTime.length() > maxchar) {
            startTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (startTime.length() == 0) {
            startTime.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (startTime.length() > maxchar) {
            startTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (endTime.length() == 0) {
            endTime.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (endTime.length() > maxchar) {
            endTime.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (status.length() == 0) {
            status.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (status.length() > maxchar) {
            status.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

}
