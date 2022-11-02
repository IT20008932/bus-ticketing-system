package com.sliit.passengerfines;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sliit.passengerfines.DB.DBHandler;
import com.sliit.passengerfines.classes.Passenger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddFines extends AppCompatActivity {

    final Calendar myCalendar= Calendar.getInstance();
    EditText pid,date,fines;
    DBHandler dbHandler;
    Spinner route,qr;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fines);

        pid = findViewById(R.id.insertId);
        date = findViewById(R.id.insertDate);
        route = findViewById(R.id.insertRoute);
        qr = findViewById(R.id.insertQr);
        fines = findViewById(R.id.insertFines);
        submit = findViewById(R.id.insertBtn);

        dbHandler = new DBHandler(this);

        DatePickerDialog.OnDateSetListener dates =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                String myFormat="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
                date.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddFines.this,dates,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String[] data = new String[] {"Select Route","route 1","route 2","route 3","route 4","route 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        route.setAdapter(adapter);

        String[] data1 = new String[] {"Have a QR?","YES","NO"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, data1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qr.setAdapter(adapter1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(pid.getText().toString().equals("")||pid.getText().toString().equals(null))){
                    if(!(route.getSelectedItem().toString().equals(null)||route.getSelectedItem().toString().equals("Select Route"))){
                        if(!(date.getText().toString().equals("")||date.getText().toString().equals(null))){
                            if(!(qr.getSelectedItem().toString().equals(null)||qr.getSelectedItem().toString().equals("Have a QR?"))){
                                if(!(fines.getText().toString().equals("")||fines.getText().toString().equals(null))){
                                    Passenger passenger = new Passenger();
                                    passenger.setpId(pid.getText().toString());
                                    passenger.setRoute(route.getSelectedItem().toString());
                                    passenger.setDate(date.getText().toString());
                                    passenger.setQr(qr.getSelectedItem().toString());

                                    double total = Double.parseDouble(fines.getText().toString());
                                    if (qr.getSelectedItem().toString().equals("NO")){
                                        total = total+500;
                                    }

                                    passenger.setFines(total);

                                    boolean res = dbHandler.addPassenger(passenger);
                                    if (res){
                                        clearAll();
                                        Toast.makeText(AddFines.this,"Successful",Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(AddFines.this,"Unsuccessful",Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(AddFines.this, "Fines ID Required!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(AddFines.this, "Have a QR?", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddFines.this, "Select Date!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(AddFines.this, "Select Route!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddFines.this, "Passenger ID Required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void clearAll(){
        qr.setSelection(0);
        route.setSelection(0);
        pid.setText("");
        date.setText("");
        fines.setText("");
    }
}