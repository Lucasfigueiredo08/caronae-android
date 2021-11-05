package com.unifap.caronaeunifap.models.modelsforjson;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.unifap.caronaeunifap.models.RideHistory;

public class RideHistoryForJson{
    @SerializedName("rides")
    private List<RideHistory> rides;
    @SerializedName("taken_rides_count")
    private int ridesHistoryTakenCount;
    @SerializedName("offered_rides_count")
    private int ridesHistoryOfferedCount;

    public List<RideHistory> getRides() {
        return rides;
    }

    public int getRidesHistoryTakenCount()
    {
        return ridesHistoryTakenCount;
    }

    public int getRidesHistoryOfferedCount()
    {
        return ridesHistoryOfferedCount;
    }

}
