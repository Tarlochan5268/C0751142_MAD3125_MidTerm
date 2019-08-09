package com.tarlochan.c0751142_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private FlightAdapter mAdapter;
    ImageView imageView;
    DataStore mDataStore;
    private List<FlightRow> flightRowList = new ArrayList<>();
    public static ArrayList<SpaceXFlight> staticSpaceXFlightList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        mDataStore = new DataStore(MainActivity.this);
        mDataStore.processJSON();
        MainActivity.staticSpaceXFlightList = mDataStore.mSpaceXFlightList;
        Log.d("Size of SS space List :",String.valueOf(MainActivity.staticSpaceXFlightList.size()));
        Log.d("Size of mSpaceList:",String.valueOf(mDataStore.mSpaceXFlightList.size()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recycleView);


        mAdapter = new FlightAdapter(this,flightRowList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareFlightListData();

    }

    private void prepareFlightListData()
    {
        for(SpaceXFlight flight : mDataStore.mSpaceXFlightList)
        {
            FlightRow flightRow = new FlightRow(flight.getLinks().getMission_patch_small(),flight.getMission_name(),flight.getLaunch_year());
            flightRowList.add(flightRow);
        }
        mAdapter.notifyDataSetChanged();
    }
}
