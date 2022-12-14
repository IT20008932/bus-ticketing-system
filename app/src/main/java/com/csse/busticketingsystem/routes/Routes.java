package com.csse.busticketingsystem.routes;

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
import com.csse.busticketingsystem.adapters.RouteAdapter;
import com.csse.busticketingsystem.buses.Buses;
import com.csse.busticketingsystem.database.DBHelper;

import com.csse.busticketingsystem.schedules.Schedules;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Routes extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ImageView empty_imageview;
    TextView no_data;
    Bundle bundle;

    DBHelper db;
    ArrayList<String> route_id,route_start_location,route_end_location;
    RouteAdapter routeAdapter;
    MaterialToolbar materialToolbar;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        try {
            bundle = getIntent().getExtras();
            String getstatus = bundle.getString("status");
            Snackbar snackbar = Snackbar.make(findViewById(R.id.routesLayout), getstatus, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.btn_ok, v -> snackbar.dismiss());
            snackbar.show();
        } catch (Exception ignore) { }

        Log.d("workflow","Routes on_create method Called");

        recyclerView=findViewById(R.id.recyclerView3);
        empty_imageview=findViewById(R.id.empty_image);
        no_data = findViewById(R.id.no_data);

        db =new DBHelper(this);
        route_id = new ArrayList<>();

        route_start_location = new ArrayList<>();
        route_end_location = new ArrayList<>();

        storeDataInArrays();
        Log.d("workflow","Routes storeDataInArrays method Called");

        routeAdapter = new RouteAdapter(this,this,route_id,
                route_start_location,
                route_end_location);

        recyclerView.setAdapter(routeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        materialToolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(materialToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.routes);

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
                        return true;
                    case R.id.schedules:
                        startActivity(new Intent(getApplicationContext()
                                , Schedules.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                Log.d("workflow","Routes bottom navigation method called");
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
                route_id.add(cursor.getString(0));
                route_start_location.add(cursor.getString(1).substring(0, 1).toUpperCase() + cursor.getString(1).substring(1));
                route_end_location.add(cursor.getString(2).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));
                Log.d("workflow",cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


    //Add new route

    public void openAddNewRoute() {
        Log.d("workflow","Routes openAddNewRoute method Called");
        Intent intent = new Intent(this, AddRoute.class);
        startActivity(intent);
        Log.i("Lifecycle", "Add route button clicked");
    }


    //View route

    public void openViewRoute()
    {
        Log.d("workflow","Routes openViewRoute method Called");
        Intent intent = new Intent(this,ModifyRoute.class);
        startActivity(intent);
        Log.i("workflow","Add route button clicked");
    }

}



