package com.sliit.passengerfines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sliit.passengerfines.DB.DBHandler;
import com.sliit.passengerfines.classes.Passenger;

import java.util.ArrayList;

public class ListFines extends AppCompatActivity {

    private RecyclerView recview;
    private PassengerAdapter adapter;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fines);

        dbHandler = new DBHandler(this);

        ArrayList<Passenger> allPassenger = dbHandler.readAllPassenger();

        recview=(RecyclerView) findViewById(R.id.recyclerview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PassengerAdapter(allPassenger);

        adapter.notifyDataSetChanged();
        recview.setAdapter(adapter);

    }
}