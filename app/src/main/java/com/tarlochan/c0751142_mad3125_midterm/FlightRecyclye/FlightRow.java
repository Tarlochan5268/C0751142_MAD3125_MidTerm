package com.tarlochan.c0751142_mad3125_midterm.FlightRecyclye;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightRow implements Parcelable
{
    String flightimage;
    String flightName;
    String flightYear;
    int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FlightRow() {
    }

    public FlightRow(String image, String flightName, String flightYear) {
        this.flightimage = image;
        this.flightName = flightName;
        this.flightYear = flightYear;
    }

    protected FlightRow(Parcel in) {
        flightimage = in.readString();
        flightName = in.readString();
        flightYear = in.readString();
    }

    public static final Creator<FlightRow> CREATOR = new Creator<FlightRow>() {
        @Override
        public FlightRow createFromParcel(Parcel in) {
            return new FlightRow(in);
        }

        @Override
        public FlightRow[] newArray(int size) {
            return new FlightRow[size];
        }
    };

    public String getFlightImage() {
        return flightimage;
    }

    public void setFlightImage(String image) {
        this.flightimage = image;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightYear() {
        return flightYear;
    }

    public void setFlightYear(String flightYear) {
        this.flightYear = flightYear;
    }
    @Override
    public String toString() {
        return "FlightRow{" +
                "image='" + flightimage + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightYear='" + flightYear + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.flightimage);
            dest.writeString(this.flightName);
            dest.writeString(this.flightYear);
    }
}
