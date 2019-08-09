package com.tarlochan.c0751142_mad3125_midterm;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tarlochan.c0751142_mad3125_midterm.FlightRecyclye.FlightRow;
import com.tarlochan.c0751142_mad3125_midterm.SpaceXFlight.SpaceXFlight;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightDetailsActivity extends AppCompatActivity {
    SpaceXFlight flight;
    @BindView(R.id.imgWikipedia)
    ImageView imgWikipedia;
    @BindView(R.id.imgArticle)
    ImageView imgArticle;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtYear)
    TextView txtYear;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imgYoutube)
    ImageView imgYoutube;
    @BindView(R.id.txtDetails)
    TextView txtDetails;
    @BindView(R.id.txtRocketId)
    TextView txtRocketId;
    @BindView(R.id.txtRocketType)
    TextView txtRocketType;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.txtRocketName)
    TextView txtRocketName;
    @BindView(R.id.txtUpcoming)
    TextView txtUpcoming;
    @BindView(R.id.txtFlightSuccess)
    TextView txtFlightSuccess;
    @BindView(R.id.txtSiteName)
    TextView txtSiteName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlightDetailsActivity.this,ArticleActivity.class);
                intent.putExtra("link", flight.getLinks().getArticle_link());
                startActivity(intent);
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        int position = getIntent().getIntExtra("position", 0);
        Bundle data = getIntent().getExtras();
        FlightRow flightRow = (FlightRow) data.getParcelable("flightrow");
        //Log.d("Flight Row --------->",flightRow.toString());
        flight = MainActivity.staticSpaceXFlightList.get(position);
        txtName.setText(flight.getMission_name());
        txtYear.setText(flight.getLaunch_year());
        String photoUrl = flight.getLinks().getMission_patch_small();
        //Log.d("patch Link ------->>>", flight.getLinks().getMission_patch_small());
        Glide.with(imageView)  //2
                .load(photoUrl) //3
                .centerCrop() //4
                .placeholder(R.drawable.img_placeholder) //5
                .error(R.drawable.img_notload) //6
                .fallback(R.drawable.img_placeholder) //7
                .into(imageView);
        txtDetails.setText(flight.getDetails());
        String upcoming = "";
        if (flight.getUpcoming().equals("false")) {
            upcoming = "No";
            txtUpcoming.setTextColor(Color.RED);

        } else if(flight.getUpcoming().equals("true")) {
            upcoming = "Yes";
            txtUpcoming.setTextColor(Color.BLUE);
        }
        else
        {
            upcoming = "No";
            txtUpcoming.setTextColor(Color.RED);
        }
        txtUpcoming.setText(upcoming);
        txtRocketId.setText(flight.getRocket().getRocket_id());
        txtRocketName.setText(flight.getRocket().getRocket_name());
        txtRocketType.setText(flight.getRocket().getRocket_type());
        //txtSiteId.setText(flight.getLaunchSite().getSite_id());
        String success = "";
        if (flight.getFlight_success().equals("false")) {
            success = "No";
            txtFlightSuccess.setTextColor(Color.RED);

        } else if(flight.getFlight_success().equals("true")) {
            success = "Yes";
            txtFlightSuccess.setTextColor(Color.BLUE);
        }
        else
        {
            success = "No";
            txtFlightSuccess.setTextColor(Color.RED);
        }

        txtFlightSuccess.setText(success);
        txtSiteName.setText(flight.getLaunchSite().getSite_name());

        imgArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightDetailsActivity.this,ArticleActivity.class);
                intent.putExtra("link", flight.getLinks().getArticle_link());
                startActivity(intent);
                finish();
            }
        });

        imgWikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightDetailsActivity.this,ArticleActivity.class);
                intent.putExtra("link", flight.getLinks().getWikipedia());
                startActivity(intent);
                finish();
            }
        });
        imgYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FlightDetailsActivity.this,ArticleActivity.class);
                intent.putExtra("link", flight.getLinks().getVideo_link());
                startActivity(intent);
                finish();
            }
        });
    }

}
