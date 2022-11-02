package com.csse.busticketingsystem.buses;

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
import com.csse.busticketingsystem.adapters.BusAdapter;
import com.csse.busticketingsystem.database.DBHelper;

import com.csse.busticketingsystem.routes.Routes;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Buses extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ImageView empty_imageview;
    TextView no_data;
    Bundle bundle;

    DBHelper db;
    ArrayList<String> bus_id,bus_number,desc;
    BusAdapter busAdapter;
    MaterialToolbar materialToolbar;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buses);

        try {
            bundle = getIntent().getExtras();
            String getstatus = bundle.getString("status");
            Snackbar snackbar = Snackbar.make(findViewById(R.id.busesLayout), getstatus, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.btn_ok, v -> snackbar.dismiss());
            snackbar.show();
        } catch (Exception ignore) { }

        Log.d("workflow","Buses on_create method Called");

        recyclerView=findViewById(R.id.recyclerView3);
        empty_imageview=findViewById(R.id.empty_image);
        no_data = findViewById(R.id.no_data);

        db =new DBHelper(this);
        bus_id = new ArrayList<>();

        bus_id = new ArrayList<>();
        desc = new ArrayList<>();

        storeDataInArrays();
        Log.d("workflow","Buses storeDataInArrays method called");

        busAdapter = new BusAdapter(this,this,bus_id, bus_number,
                desc);

        recyclerView.setAdapter(busAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        materialToolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(materialToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.buses);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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
                Log.d("workflow","Buses bottom navigation method Called");
                return false;
            }
        });


        //start code for Add new bus button
        //pls replace below code after float button in UI

        fab = findViewById(R.id.btn_add_bus);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAddNewBus();
                Log.d("workflow","Buses Float Button Clicked");
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
            Log.d("workflow","Buses onActivityResult method Called");
        }
    }

    private void storeDataInArrays() {
        Log.d("workflow","Buses storeDataInArrays method Called");
        Cursor cursor=db.readAllBuses();
        if(cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            Resources resources=getResources();
            while(cursor.moveToNext()){
                bus_id.add(cursor.getString(0));
                desc.add(cursor.getString(2).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));
                Log.d("workflow",cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


    //Add new bus

    public void openAddNewBus() {
        Log.d("workflow","Buses openAddNewBus method Called");
        Intent intent = new Intent(this, AddBus.class);
        startActivity(intent);
        Log.i("Lifecycle", "Add bus button clicked");
    }


    //View bus

    public void openViewBus()
    {
        Log.d("workflow","Buses openViewBus method Called");
        Intent intent = new Intent(this,ModifyBus.class);
        startActivity(intent);
        Log.i("workflow","Add bus button clicked");
    }

}



