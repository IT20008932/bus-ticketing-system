package com.csse.busticketingsystem.buses;

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
import com.csse.busticketingsystem.database.DBHelper;
import com.csse.busticketingsystem.routes.Routes;
import com.csse.busticketingsystem.schedules.Schedules;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddBus extends AppCompatActivity {
    EditText busno,desc;
    boolean isfieldsvalidated=false;  //check all field validations
    String setStatusMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        Log.d("workflow","Add Route onCreate  method  Called");
        busno=findViewById(R.id.inp_busno);
        desc=findViewById(R.id.inp_busdesc);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.routes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.buses:
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
                        startActivity(new Intent(getApplicationContext()
                                , Schedules.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                Log.d("workflow","Add Bus Bottom Navigation method called");
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addBus(View view) {
        Log.d("workflow","Add Bus addBus method called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addBuses(busno.getText().toString(),
                    desc.getText().toString());

            if (val == -1) {
                setStatusMsg = getString(R.string.msg_bus_add_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_bus_add_succesfull);
            }

            Intent intent = new Intent(this, Buses.class).putExtra("status", setStatusMsg);
            startActivity(intent);
            Log.i("BTN Click", "Add bus confirmation button clicked");
        }
    }

    public boolean CheckAllFields() {

        //if values are changed pls change in modify route as well

        int maxchar=100;

        Log.d("workflow","Add Route CheckAllFields  method  Called");
        if (busno.length() == 0) {
            busno.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (desc.length() == 0) {
            desc.setError(getString(R.string.error_msg_mandatory));
            return false;
        }
        if (busno.length() > maxchar) {

            busno.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);

            return false;
        }

        if (desc.length() > maxchar) {
            desc.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

}
