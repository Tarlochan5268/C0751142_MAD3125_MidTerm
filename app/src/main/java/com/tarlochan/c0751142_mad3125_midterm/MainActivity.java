package com.tarlochan.c0751142_mad3125_midterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity
{
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);


        DataStore mDataStore = new DataStore(MainActivity.this);
        mDataStore.processJSON();
        Log.d("Size of List : ",String.valueOf(mDataStore.mSpaceXFlightList.size()));

        String photoUrl = mDataStore.mSpaceXFlightList.get(1).getLinks().getMission_patch_small();
        Glide.with(imageView)  //2
                .load(photoUrl) //3
                .centerCrop() //4
                .placeholder(R.drawable.ImagePlaceHolder) //5
                .error(R.drawable.imageNotLoad) //6
                .fallback(R.drawable.ImagePlaceHolder) //7
                .into(imageView);

    }
}
