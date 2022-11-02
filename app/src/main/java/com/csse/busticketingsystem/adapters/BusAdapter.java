package com.csse.busticketingsystem.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csse.busticketingsystem.R;
import com.csse.busticketingsystem.buses.ModifyBus;

import java.util.ArrayList;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList bus_id,
                      bus_number,
                      desc;

    public BusAdapter(Activity activity,
                        Context context,
                        ArrayList bus_id,
                        ArrayList bus_number,
                        ArrayList desc)

    {
        Log.d("workflow","BusAdapter Constructor Called");
        this.activity=activity;
        this.context=context;
        this.bus_id=bus_id;
        this.bus_number=bus_number;
        this.desc=desc;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row_bus,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("workflow","BusAdapter onBindViewHolder method  Called");


        holder.bid_txt.setText(String.valueOf(bus_id.get(position)));
        holder.bno_txt.setText(String.valueOf(bus_number.get(position)));
        holder.desc_txt.setText(String.valueOf(desc.get(position)));
        //Recyclerview OnclickLister
        //holder.imageView.setVisibility(View.VISIBLE);
        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ModifyBus.class);
                intent.putExtra("bid",String.valueOf(bus_id.get(position)));
                intent.putExtra("bno",String.valueOf(bus_number.get(position)));
                intent.putExtra("desc",String.valueOf(desc.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("valies",String.valueOf(bus_id.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return bus_id.size();      }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bid_txt, bno_txt, desc_txt;
        LinearLayout mainLayout;
        ImageButton imgbtn;
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bid_txt=itemView.findViewById(R.id.bus_no_txt);
            //desc_txt=itemView.findViewById(R.id.desc_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            imgbtn=itemView.findViewById(R.id.imageButton);
        }
    }

}
