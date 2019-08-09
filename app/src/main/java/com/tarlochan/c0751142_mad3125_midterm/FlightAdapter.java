package com.tarlochan.c0751142_mad3125_midterm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.MyViewHolder>
{
    public List<FlightRow> flightRowsList;
    ImageView flightImage;
    TextView flightName,flightYear;

    private static final String TAG = "FlightAdapter";

    AppCompatActivity activity;

    public FlightAdapter(AppCompatActivity activity_main, List<FlightRow> flightRowsList)
    {
        this.flightRowsList = flightRowsList;
        this.activity = activity_main;
        Log.e(TAG, "FlightAdapter: "+flightRowsList.size() );

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_flight_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        FlightRow mFlightRow = flightRowsList.get(i);
        String photoUrl = MainActivity.staticSpaceXFlightList.get(i).getLinks().getMission_patch_small();
        Glide.with(myViewHolder.flightImage)  //2
                .load(photoUrl) //3
                .centerCrop() //4
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_notload) //6
                .fallback(R.drawable.img_placeholder) //7
                .into(myViewHolder.flightImage);
        //myViewHolder.flightImage.setImageResource(mFlightRow.getImageId());
        //icon.setImageResource(context.getResources().getIdentifier(animal.getAnimalName().toLowerCase(),"drawable",context.getPackageName()));
        myViewHolder.flightName.setText(mFlightRow.getFlightName());
        myViewHolder.flightYear.setText(mFlightRow.getFlightYear());
    }

    @Override
    public int getItemCount() {
        return flightRowsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView flightImage;
        TextView flightName,flightYear;

        public MyViewHolder(View view)
        {
            super(view);
            flightImage =  view.findViewById(R.id.imageView);
            flightName =  view.findViewById(R.id.txtFlightName);
            flightYear =  view.findViewById(R.id.txtFlightYear);
        }
    }
}
