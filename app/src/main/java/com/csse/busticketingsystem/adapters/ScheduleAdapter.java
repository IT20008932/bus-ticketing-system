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
import com.csse.busticketingsystem.schedules.ModifySchedule;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList schedule_id,
            route_id,
            bus_id,
            start_time,
            end_time,
            status;

    public ScheduleAdapter(Activity activity,
                        Context context,
                        ArrayList schedule_id,
                        ArrayList route_id,
                        ArrayList bus_id,
                        ArrayList start_time,
                        ArrayList end_time,
                        ArrayList status)

    {
        Log.d("workflow","ScheduleAdapter Constructor called");
        this.activity=activity;
        this.context=context;
        this.schedule_id=schedule_id;
        this.route_id=route_id;
        this.bus_id=bus_id;
        this.start_time=start_time;
        this.end_time=end_time;
        this.status=status;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row_schedule,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("workflow","RouteAdapter onBindViewHolder method  Called");


        holder.sid_txt.setText(String.valueOf(schedule_id.get(position)));
        holder.rid_txt.setText(String.valueOf(route_id.get(position)));
        holder.bid_txt.setText(String.valueOf(bus_id.get(position)));
        holder.start_time_txt.setText(String.valueOf(start_time.get(position)));
        holder.end_time_txt.setText(String.valueOf(end_time.get(position)));
        holder.status_txt.setText(String.valueOf(status.get(position)));
        //Recyclerview OnclickLister
        //holder.imageView.setVisibility(View.VISIBLE);
        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ModifySchedule.class);
                intent.putExtra("sid",String.valueOf(schedule_id.get(position)));
                intent.putExtra("rid",String.valueOf(route_id.get(position)));
                intent.putExtra("bid",String.valueOf(bus_id.get(position)));
                intent.putExtra("startTime",String.valueOf(start_time.get(position)));
                intent.putExtra("endTime",String.valueOf(end_time.get(position)));
                intent.putExtra("status",String.valueOf(route_id.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("valies",String.valueOf(route_id.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return schedule_id.size();      }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView sid_txt,rid_txt,bid_txt,start_time_txt,end_time_txt, status_txt;
        LinearLayout mainLayout;
        ImageButton imgbtn;
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sid_txt=itemView.findViewById(R.id.sid_txt);
            rid_txt=itemView.findViewById(R.id.rid_txt);
            bid_txt=itemView.findViewById(R.id.bid_txt);
            start_time_txt=itemView.findViewById(R.id.start_time_txt);
            end_time_txt=itemView.findViewById(R.id.end_time_txt);
            status_txt=itemView.findViewById(R.id.status_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            imgbtn=itemView.findViewById(R.id.imageButton);
        }
    }

}
