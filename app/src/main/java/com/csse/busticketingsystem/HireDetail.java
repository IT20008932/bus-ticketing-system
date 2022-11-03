package com.example.myapplication22;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication22.Database.DbHandler;
import com.example.myapplication22.Database.HireModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class HireDetail extends AppCompatActivity {

    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    private Button btn1,btn2;
    private DbHandler dbHandler;
    private Context context;
    private List<HireModel> hires;

    private boolean success = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);


        t1=findViewById(R.id.customer);
        t2=findViewById(R.id.worker);
        t3=findViewById(R.id.date);
        t4=findViewById(R.id.location);
        t5=findViewById(R.id.s_date);
        t6=findViewById(R.id.tot);
        t8=findViewById(R.id.bal);
        t9=findViewById(R.id.duraa);

        btn2=findViewById(R.id.btn2);
        context=this;
        dbHandler=new DbHandler(context);

        hires=dbHandler.getAllHireDetail();



        /*
        used this kind of code architect to save the data from
        previous activity. any error will lead the app to crash.
         */
        String bankHolder = getIntent().getStringExtra("bankHolder");
        String bankName = getIntent().getStringExtra("bankName");
        String account = getIntent().getStringExtra("account");
        String routing = getIntent().getStringExtra("routing");

        t1.setText(bankHolder);
        t2.setText(bankName);
        t3.setText(account);
        t4.setText(routing);



//        Bundle b = getIntent().getExtras();
//        if (b != null){
//            String tot = b.getString("total");
//            String adv = b.getString("advance");
//            String cus=b.getString("cusName");
//            String loc=b.getString("cusLoc");
//            String time=b.getString("data2");
//            String worker=b.getString("worker");
//            String date=b.getString("uDate");
//            Calendar calendar=Calendar.getInstance();
//            String currntdate= DateFormat.getDateInstance().format(calendar.getTime());
//
//            if (tot!=null && adv!=null){
//                double bal=Double.parseDouble(tot)-Double.parseDouble(adv);
//                t8.setText("Rs."+String.valueOf(bal));
//                t6.setText("Rs."+tot);
//                t7.setText("Rs."+adv);
//            }
//            t1.setText(cus);
//            t2.setText(worker);
//            t3.setText(currntdate);
//            t4.setText(loc);
//            t5.setText(date);
//            t9.setText(time);
//        }

        btn2.setOnClickListener(v ->  returnToast() );
    }

    public void returnToast(){
        if (success){
            Toast toast = Toast.makeText(HireDetail.this,"Payment Successful", Toast.LENGTH_SHORT);
            View view = toast.getView();
            TextView text = view.findViewById(android.R.id.message);
            text.setTextSize(15);
            text.setTextColor(Color.WHITE);
            view.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            toast.show();
        }else {
            Toast toast = Toast.makeText(HireDetail.this,"Payment Failed", Toast.LENGTH_SHORT);
            View view = toast.getView();
            TextView text = view.findViewById(android.R.id.message);
            text.setTextSize(15);
            text.setTextColor(Color.WHITE);
            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            toast.show();
        }

        finish();
    }
}