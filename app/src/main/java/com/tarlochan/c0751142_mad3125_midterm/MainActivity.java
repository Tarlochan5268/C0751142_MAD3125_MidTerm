package com.tarlochan.c0751142_mad3125_midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarlochan.c0751142_mad3125_midterm.DataBase.DataStore;
import com.tarlochan.c0751142_mad3125_midterm.FlightRecyclye.FlightAdapter;
import com.tarlochan.c0751142_mad3125_midterm.FlightRecyclye.FlightRow;
import com.tarlochan.c0751142_mad3125_midterm.SpaceXFlight.SpaceXFlight;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FlightAdapter.FlightClickListener
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


        mAdapter = new FlightAdapter(this,flightRowList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareFlightListData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnu_LogOut:
                //Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onFlightClick(int position) {
        Log.d("ON FLIGHT CLICK -->>",flightRowList.get(position).toString()+" position : "+position);
        //flightRowList.get(position);
        Intent intent = new Intent(this,FlightDetailsActivity.class);
        intent.putExtra("position",position);
        FlightRow flightRow = flightRowList.get(position);
        flightRow.setPosition(position);
        intent.putExtra("flightrow",flightRow);
        startActivity(intent);
    }
}
