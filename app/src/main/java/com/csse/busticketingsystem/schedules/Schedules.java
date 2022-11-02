package com.csse.busticketingsystem.schedules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.csse.busticketingsystem.MainActivity;
import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.adapters.ScheduleAdapter;
import com.csse.busticketingsystem.buses.Buses;
import com.csse.busticketingsystem.database.DBHelper;

import com.csse.busticketingsystem.routes.Routes;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Schedules extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ImageView empty_imageview;
    TextView no_data;
    Bundle bundle;

    DBHelper db;
    ArrayList<String> sid,rid,bid,start_time,end_time,status;
    ScheduleAdapter scheduleAdapter;
    MaterialToolbar materialToolbar;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        try {
            bundle = getIntent().getExtras();
            String getstatus = bundle.getString("status");
            Snackbar snackbar = Snackbar.make(findViewById(R.id.routesLayout), getstatus, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.btn_ok, v -> snackbar.dismiss());
            snackbar.show();
        } catch (Exception ignore) { }

        Log.d("workflow","Schedule on_create method Called");

        recyclerView=findViewById(R.id.recyclerView3);
        empty_imageview=findViewById(R.id.empty_image);
        no_data = findViewById(R.id.no_data);

        db =new DBHelper(this);
        sid = new ArrayList<>();
        rid = new ArrayList<>();
        bid = new ArrayList<>();
        start_time = new ArrayList<>();
        end_time = new ArrayList<>();
        status = new ArrayList<>();

        storeDataInArrays();
        Log.d("workflow","Schedules storeDataInArrays method called");

        scheduleAdapter = new ScheduleAdapter(this,this,sid,
                rid,
                bid,
                start_time,
                end_time,
                status);

        recyclerView.setAdapter(scheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        materialToolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(materialToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.schedules);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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
                Log.d("workflow","Routes bottom navigation method Called");
                return false;
            }
        });


        //start code for Add new route button
        //pls replace below code after float button in UI

        fab = findViewById(R.id.btn_add_route);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAddNewRoute();
                Log.d("workflow","Routes Float Button Clicked");
            }
        });



    }   //close the tag pls place over this any code related to on start

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();    //refresh if data not fetched
            Log.d("workflow","Routes onActivityResult method Called");
        }
    }

    private void storeDataInArrays() {
        Log.d("workflow","Routes storeDataInArrays method Called");
        Cursor cursor=db.readAllRoutes();
        if(cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            Resources resources=getResources();
            while(cursor.moveToNext()){
                sid.add(cursor.getString(0));
                rid.add(cursor.getString(1));
                bid.add(cursor.getString(2));
                start_time.add(cursor.getString(3).substring(0, 1).toUpperCase() + cursor.getString(1).substring(1));
                end_time.add(cursor.getString(4).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));
                Log.d("workflow",cursor.getString(4));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


    //Add new route

    public void openAddNewRoute() {
        Log.d("workflow","Routes openAddNewRoute method Called");
        Intent intent = new Intent(this, AddSchedule.class);
        startActivity(intent);
        Log.i("Lifecycle", "Add route button clicked");
    }


    //View route

    public void openViewRoute()
    {
        Log.d("workflow","Routes openViewRoute method Called");
        Intent intent = new Intent(this,ModifySchedule.class);
        startActivity(intent);
        Log.i("workflow","Add route button clicked");
    }

}



