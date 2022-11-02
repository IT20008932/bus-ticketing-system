package com.csse.busticketingsystem.routes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;


public class AddRoute extends AppCompatActivity {
    EditText etstartloc,etendloc,etdistance;
    boolean isfieldsvalidated=false;  //check all field validations
    Switch aSwitch;
    String setStatusMsg;
    TextView bdistance;
    //Calculations calc=new Calculations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        Log.d("workflow","Add Route onCreate  method  Called");
        etstartloc=findViewById(R.id.inp_startloc);
        etendloc=findViewById(R.id.inp_endloc);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.routes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.routes:
                        return true;
                }
                Log.d("workflow","Add Route Bottom Navigation  method  Called");
                return false;
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addRoute(View view) {
        Log.d("workflow","Add Route addRoute  method  Called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addRoutes(etstartloc.getText().toString(),
                    etendloc.getText().toString());

            if (val == -1) {
                setStatusMsg = getString(R.string.msg_route_add_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_route_add_succesfull);
            }

            Intent intent = new Intent(this, Routes.class).putExtra("status", setStatusMsg);
            startActivity(intent);
            Log.i("BTN Click", "Add route Confirmation button clicked");
        }
    }

    public boolean CheckAllFields() {

        //if values are changed pls change in modify route as well

        int maxchar=10;
        double maxdistance=999.99;

        Log.d("workflow","Add Route CheckAllFields  method  Called");
        if (etstartloc.length() == 0) {
            etstartloc.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (etendloc.length() == 0) {
            etendloc.setError(getString(R.string.error_msg_mandatory));
            return false;
        }
        if (etstartloc.length() > maxchar) {

            etstartloc.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);

            return false;
        }

        if (etendloc.length() > maxchar) {
            etendloc.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

}
