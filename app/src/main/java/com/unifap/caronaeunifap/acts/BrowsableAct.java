package com.unifap.caronaeunifap.acts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.unifap.caronaeunifap.App;

public class BrowsableAct extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();

        if(App.isUserLoggedIn())
        {
            List<String> params = uri.getPathSegments();
            final String rideId;

            if (params.size() == 1)
            {
                rideId = params.get(0);
            }
            else
            {
                rideId = params.get(1);
            }

            Intent activeRide = new Intent(this, RideDetailAct.class);
            activeRide.putExtra("startLink", true);
            activeRide.putExtra("rideLinkId", rideId);
            startActivity(activeRide);
        }
        else
        {
            List<String> id = uri.getQueryParameters("id");
            List<String> token = uri.getQueryParameters("token");
            Intent loginAct = new Intent(this, LoginAct.class);
            loginAct.putExtra("startLink", true);
            loginAct.putExtra("id", id.get(0));
            loginAct.putExtra("token", token.get(0));
            startActivity(loginAct);
        }
    }
}
