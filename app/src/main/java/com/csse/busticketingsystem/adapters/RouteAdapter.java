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
import com.csse.busticketingsystem.routes.ModifyRoute;

import java.util.ArrayList;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList route_id,
            route_start_location,
            route_end_location;

    public RouteAdapter(Activity activity,
                        Context context,
                        ArrayList route_id,
                        ArrayList route_start_location,
                        ArrayList route_end_location)

    {
        Log.d("workflow","RouteAdapter Constructor Called");
        this.activity=activity;
        this.context=context;
        this.route_id=route_id;
        this.route_start_location=route_start_location;
        this.route_end_location=route_end_location;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RouteAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("workflow","RouteAdapter onBindViewHolder method  Called");


        holder.rid_txt.setText(String.valueOf(route_id.get(position)));
        holder.start_loc_txt.setText(String.valueOf(route_start_location.get(position)));
        holder.end_loc_txt.setText(String.valueOf(route_end_location.get(position)));
        //Recyclerview OnclickLister
        //holder.imageView.setVisibility(View.VISIBLE);
        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ModifyRoute.class);
                intent.putExtra("rid",String.valueOf(route_id.get(position)));
                intent.putExtra("startloc",String.valueOf(route_start_location.get(position)));
                intent.putExtra("endloc",String.valueOf(route_end_location.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("valies",String.valueOf(route_id.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return route_id.size();      }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rid_txt,start_loc_txt,end_loc_txt;
        LinearLayout mainLayout;
        ImageButton imgbtn;
        ImageView imageView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rid_txt=itemView.findViewById(R.id.rid_txt);
            start_loc_txt=itemView.findViewById(R.id.start_loc_txt);
            end_loc_txt=itemView.findViewById(R.id.end_loc_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            imgbtn=itemView.findViewById(R.id.imageButton);
        }
    }

}
