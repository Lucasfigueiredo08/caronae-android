package com.unifap.caronaeunifap.asyncs;

import android.content.Context;
import android.os.AsyncTask;

import com.facebook.login.LoginManager;

import java.util.List;

import com.unifap.caronaeunifap.App;
import com.unifap.caronaeunifap.data.SharedPref;
import com.unifap.caronaeunifap.firebase.FirebaseTopicsHandler;
import com.unifap.caronaeunifap.models.ActiveRideId;
import com.unifap.caronaeunifap.models.ChatAssets;
import com.unifap.caronaeunifap.models.Ride;
import com.unifap.caronaeunifap.models.RideRequestSent;

public class LogOut extends AsyncTask<Void, Void, Void> {

    Context context;

    public LogOut(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        LoginManager.getInstance().logOut();

        SharedPref.removeAllPrefButGcm();
        App.clearUserVar();

        List<ActiveRideId> activeRideIds = ActiveRideId.listAll(ActiveRideId.class);
        if (!activeRideIds.isEmpty()) {
            for (ActiveRideId ari : activeRideIds) {
                FirebaseTopicsHandler.unsubscribeFirebaseTopic(ari.getRideId());
            }
        }

        Ride.deleteAll(Ride.class);
        RideRequestSent.deleteAll(RideRequestSent.class);
        ActiveRideId.deleteAll(ActiveRideId.class);
        ChatAssets.deleteAll(ChatAssets.class);

        return null;
    }
}