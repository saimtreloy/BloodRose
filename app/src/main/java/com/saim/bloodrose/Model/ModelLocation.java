package com.saim.bloodrose.Model;

/**
 * Created by Android on 7/30/2017.
 */

public class ModelLocation {
    public int locationID;
    public String locationName;

    public ModelLocation(int locationID, String locationName) {
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public int getLocationID() {
        return locationID;
    }

    public String getLocationName() {
        return locationName;
    }
}
