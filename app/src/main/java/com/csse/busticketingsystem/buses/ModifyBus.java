package com.csse.busticketingsystem.buses;

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
import com.csse.busticketingsystem.database.DBHelper;
import com.csse.busticketingsystem.routes.Routes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class ModifyBus extends AppCompatActivity {
    TextInputEditText txt_busid, txt_busno, txt_desc;

    String bid,bno,desc;
    String setStatusMsg;

    boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bus);
        Log.d("workflow","Modify Bus onCreate method called");
        txt_busid=findViewById(R.id.inp_bus_id);
        txt_busno=findViewById(R.id.inp_bus_no);
        txt_busid=findViewById(R.id.inp_desc);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.buses);

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
                }
                Log.d("workflow","Modify Bus Bottom Nav method called");
                return false;
            }
        });

    }


    void getAndSetIntentData() {

        Intent intent=new Intent();

        Log.d("workflow","Modify Bus getAndSetIntentData method called");

        if(getIntent().hasExtra("bid") &&
                getIntent().hasExtra("bno") &&
                getIntent().hasExtra("desc"))
        {


            bid = getIntent().getStringExtra("bid");
            bno = getIntent().getStringExtra("bno");
            desc = getIntent().getStringExtra("desc");



            //  Log.d("mvalies",rid);
            txt_busid.setText(bid);
            txt_busno.setText(bno);
            txt_desc.setText(desc);
        }
        else{
            Toast.makeText(this, "No data Available", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateBus(View view) {


        isfieldsvalidated = CheckAllFields();
        Log.d("workflow","Modify Bus updateBus method called");
        if (isfieldsvalidated) {

            DBHelper dbHelper = new DBHelper(this);

            int val = dbHelper.updateBus(txt_busid.getText().toString(), txt_busno.getText().toString(), txt_desc.getText().toString());


            if (val == -1) {
                setStatusMsg = getString(R.string.msg_bus_update_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_bus_update_succesfull);
            }

            Intent intent = new Intent(this, Buses.class).putExtra("status", setStatusMsg);
            startActivity(intent);

            Log.i("BTN Click", "Update bus confirmation button clicked");
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow","Modify Route CheckAllFields  method  Called");

        //if values are changed pls change in add route as well
        int maxchar=10;
        double maxdistance=999.99;

        Log.d("workflow","Add Route CheckAllFields  method  Called");
        if (txt_busno.length() == 0) {
            txt_busno.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_desc.length() == 0) {
            txt_desc.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_busno.length() > maxchar) {
            txt_busno.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_desc.length() > maxchar) {
            txt_desc.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

    public void deleteBus(View view){
        confirmDialog();
        Log.d("workflow", "Modify Bus deleteBus method called");
    }

    private void errorDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_oops));
        builder.setMessage((getString(R.string.label_route))
                +" "+
                bid
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

        Log.d("workflow","Modify Bus confirmDialog method called");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_are_u_sure));
        builder.setMessage((getString(R.string.msg_confirm_delete))
                +" "+
                (getString(R.string.label_bus))
                +" "+
                bid
                +" ? "+
                (getString(R.string.msg_confirm_delete_canot_be_undone)));
        builder.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper=new DBHelper(ModifyBus.this);
                        int val= dbHelper.deleteRoute(txt_busid.getText().toString());
                        if (val == 1) {
                            setStatusMsg = getString(R.string.msg_route_delete_succesfull);

                        }
                        else {
                            setStatusMsg = getString(R.string.msg_route_delete_unsuccesfull);

                        }
                        Intent intent = new Intent(ModifyBus.this,Routes.class).putExtra("status", setStatusMsg);
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