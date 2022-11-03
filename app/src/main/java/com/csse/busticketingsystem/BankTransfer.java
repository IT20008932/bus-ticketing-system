package com.example.myapplication22;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication22.Database.BankModel;
import com.example.myapplication22.Database.DbHandler;
import com.example.myapplication22.Database.HireModel;

import java.util.List;

public class BankTransfer extends AppCompatActivity {

    private final String CHANNEL_ID = "Prsonal Notifcation";
    private final int NOTI_ID = 001;

    private EditText holder;
    private EditText name;
    private EditText acc_no;
    private EditText rou_no;
    //private TextView t1,t2,t3,t4,t5,t6,t7,t8;
    private Button btn;
    private Context context;
    private DbHandler dbHandler;
    private int position;
    private List<HireModel> hires;
    //private var binding:FragmentFi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        holder=findViewById(R.id.bnk_holder);
        name=findViewById(R.id.b_name);
        acc_no = findViewById(R.id.accNo);
        rou_no = findViewById(R.id.routNo);
        btn = findViewById(R.id.bank_save);
//        t1=findViewById(R.id.bank_topText);
//        t2=findViewById(R.id.bank_holder);
//        t3=findViewById(R.id.bank_nme);
//        t4=findViewById(R.id.bank_accNo);
//        t5=findViewById(R.id.bank_routing);
//        t6=findViewById(R.id.bank_bottonText);
//        t7=findViewById(R.id.acc_no);
//        t8=findViewById(R.id.rout_no);
        context = this;
        dbHandler = new DbHandler(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //when user click save store all the inserted data
                String bankHolder = holder.getText().toString();
                String bankName = name.getText().toString();
                String accountNum = acc_no.getText().toString();
                String routingNum = rou_no.getText().toString();
                int account = Integer.parseInt(accountNum);
                int routing = Integer.parseInt(routingNum);

                //create model type object and put all the inserted vales
                BankModel bankModel = new BankModel(account,routing, bankName, bankHolder);

                dbHandler.addBankDetails(bankModel);
                hires = dbHandler.getAllHireDetail();

//                HireModel hire = hires.get(position);

                finish();
                Intent intent = new Intent(BankTransfer.this, HireDetail.class);
                intent.putExtra("bankHolder",bankHolder);
                intent.putExtra("bankName",bankName);
                intent.putExtra("account",accountNum);
                intent.putExtra("routing",routingNum);
                startActivity(intent);

            }
        });

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_sms_24);
        builder.setContentTitle("Payment Successful");
        builder.setContentText("Your advance payment make successfully.");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTI_ID,builder.build());

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Persianl Noti";
            String des="Inlcud all";
            int importantace= NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importantace);
            notificationChannel.setDescription(des);

            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
    public void navToPayment(){
        finish();
        Intent intent = new Intent(this, HireDetail.class);

//        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }

}