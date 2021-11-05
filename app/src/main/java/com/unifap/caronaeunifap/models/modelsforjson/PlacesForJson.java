package com.unifap.caronaeunifap.models.modelsforjson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.unifap.caronaeunifap.models.Campi;
import com.unifap.caronaeunifap.models.Institution;
import com.unifap.caronaeunifap.models.Zone;

public class PlacesForJson
{
    @SerializedName("zones")
    private List<Zone> zones;
    @SerializedName("campi")
    private List<Campi> campi;
    @SerializedName("institution")
    private Institution institutions;

    public List<Zone> getZones() {
        return zones;
    }

    public List<Campi> getCampi() {
        return campi;
    }

    public Institution getInstitutions() {
        return institutions;
    }
}
