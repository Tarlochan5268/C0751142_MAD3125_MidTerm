package com.tarlochan.c0751142_mad3125_midterm;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class DataStore
{
    Context context;
    public ArrayList<SpaceXFlight> mSpaceXFlightList ;

    public DataStore(Context context)
    {
        this.context = context;
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = context.getAssets().open("User.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int count = is.read(buffer);
            is.close();
            Log.d("-- COUNT --", String.format("%d Bytes",count));
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void processJSON()
    {
        String jsonString = this.loadJSONFromAsset();
        if (jsonString != null)
        {
            try {
                JSONArray mJSONArray = new JSONArray(jsonString);
                mSpaceXFlightList = new ArrayList<SpaceXFlight>();

                for (int i = 0; i < mJSONArray.length(); i++) {

                    SpaceXFlight mSpaceXFlight = this.getFlightObjectFromJSON(mJSONArray.getJSONObject(i));
                    mSpaceXFlightList.add(mSpaceXFlight);
                    Log.d("-- JSON -- ", mSpaceXFlight.toString());

                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
        }
    }

    private SpaceXFlight getFlightObjectFromJSON(JSONObject userJsonObject) throws JSONException
    {
        String flight_number = userJsonObject.getString("flight_number");
        String mission_name = userJsonObject.getString("mission_name");
        String upcoming = userJsonObject.getString("upcoming");
        String launch_year = userJsonObject.getString("launch_year");
        String launch_window = userJsonObject.getString("launch_window");
        String details = userJsonObject.getString("details");

        //Read Rocket
        JSONObject rocket = new JSONObject(userJsonObject.getJSONObject("rocket").toString());
        String rocket_id = rocket.getString("rocket_id");
        String rocket_name = rocket.getString("rocket_name");
        String rocket_type = rocket.getString("rocket_type");

        //Read Launch Site
        JSONObject launchSite = new JSONObject(userJsonObject.getJSONObject("launch_site").toString());
        String site_id = launchSite.getString("site_id");
        String site_name = launchSite.getString("site_name");
        String site_name_long = launchSite.getString("site_name_long");

        //Read Links
        JSONObject links = new JSONObject(userJsonObject.getJSONObject("links").toString());
        String mission_patch = links.getString("mission_patch");
        String mission_patch_small = links.getString("mission_patch_small");
        String article_link = links.getString("article_link");
        String wikipedia = links.getString("wikipedia");
        String video_link = links.getString("video_link");

        //start creating User object
        SpaceXFlight mSpaceXFlight = new SpaceXFlight();
        mSpaceXFlight.setFlight_number(flight_number);
        mSpaceXFlight.setMission_name(mission_name);
        mSpaceXFlight.setUpcoming(upcoming);
        mSpaceXFlight.setLaunch_year(launch_year);
        mSpaceXFlight.setLaunch_window(launch_window);
        mSpaceXFlight.setDetails(details);

        Rocket mRocket = new Rocket();
        mRocket.setRocket_id(rocket_id);
        mRocket.setRocket_name(rocket_name);
        mRocket.setRocket_type(rocket_type);
        mSpaceXFlight.setRocket(mRocket);

        LaunchSite mLaunchSite = new LaunchSite();
        mLaunchSite.setSite_id(site_id);
        mLaunchSite.setSite_name(site_name);
        mLaunchSite.setSite_name_long(site_name_long);
        mSpaceXFlight.setLaunchSite(mLaunchSite);

        Links mLinks = new Links();
        mLinks.setMission_patch(mission_patch);
        mLinks.setMission_patch_small(mission_patch_small);
        mLinks.setArticle_link(article_link);
        mLinks.setWikipedia(wikipedia);
        mLinks.setVideo_link(video_link);
        mSpaceXFlight.setLinks(mLinks);

        return mSpaceXFlight;
    }

}
