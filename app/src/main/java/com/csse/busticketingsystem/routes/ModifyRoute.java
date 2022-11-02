package com.csse.busticketingsystem.routes;

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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class ModifyRoute extends AppCompatActivity {
    TextInputEditText etrouteid,etstartloc,etendloc,etdistance,etcreateddate,etmodifieddate;

    String rid,startloc,endloc,distance,created,modified;
    String setStatusMsg;

    boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_route);
        Log.d("workflow","Modify Route onCreate method  Called");
        etrouteid=findViewById(R.id.inp_rid1);
        etstartloc=findViewById(R.id.inp_startlocup1);
        etendloc=findViewById(R.id.inp_endlocup1);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.routes);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.routes:
                        return true;
                }
                Log.d("workflow","Modify Route Bottom Nav method  Called");
                return false;
            }
        });

    }


    void getAndSetIntentData() {

        Intent intent=new Intent();

        Log.d("workflow","Modify Route getAndSetIntentData  method  Called");

        if(getIntent().hasExtra("rid") &&
                getIntent().hasExtra("startloc") &&
                getIntent().hasExtra("endloc"))
        {


            rid = getIntent().getStringExtra("rid");
            startloc = getIntent().getStringExtra("startloc");
            endloc = getIntent().getStringExtra("endloc");



            //  Log.d("mvalies",rid);
            etrouteid.setText(rid);
            etstartloc.setText(startloc);
            etendloc.setText(endloc);
        }
        else{
            Toast.makeText(this, "No data Available", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateRoute(View view) {


        isfieldsvalidated = CheckAllFields();
        Log.d("workflow","Modify Route updateRoute  method  Called");
        if (isfieldsvalidated) {

            DBHelper dbHelper = new DBHelper(this);

            int val = dbHelper.updateRoute(etrouteid.getText().toString(), etstartloc.getText().toString(),
                    etendloc.getText().toString());



            if (val == -1) {
                setStatusMsg = getString(R.string.msg_route_update_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_route_update_succesfull);
            }

            Intent intent = new Intent(this, Routes.class).putExtra("status", setStatusMsg);
            startActivity(intent);

            Log.i("BTN Click", "Update route Confirmation button clicked");
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow","Modify Route CheckAllFields  method  Called");

        //if values are changed pls change in add route as well
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

    public void deleteRoute(View view){
        confirmDialog();
        Log.d("workflow", "Modify Route deleteRoute  method  Called");
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
                        DBHelper dbHelper=new DBHelper(ModifyRoute.this);
                        int val= dbHelper.deleteRoute(etrouteid.getText().toString());
                        if (val == 1) {
                            setStatusMsg = getString(R.string.msg_route_delete_succesfull);

                        }
                        else {
                            setStatusMsg = getString(R.string.msg_route_delete_unsuccesfull);

                        }
                        Intent intent = new Intent(ModifyRoute.this,Routes.class).putExtra("status", setStatusMsg);
                        startActivity(intent);
                        Log.i("BTN Click", "Add route Confirmation button clicked");


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