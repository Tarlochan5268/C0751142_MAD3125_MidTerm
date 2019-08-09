package com.tarlochan.c0751142_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataStore mDataStore = new DataStore(MainActivity.this);
        mDataStore.processJSON();
        Log.d("Size of List : ",String.valueOf(mDataStore.mSpaceXFlightList.size()));

    }
}
