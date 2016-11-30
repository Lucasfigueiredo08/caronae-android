package br.ufrj.caronae.frags;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufrj.caronae.App;
import br.ufrj.caronae.R;
import br.ufrj.caronae.SharedPref;
import br.ufrj.caronae.Util;
import br.ufrj.caronae.acts.MainAct;
import br.ufrj.caronae.adapters.MyActiveRidesAdapter;
import br.ufrj.caronae.comparators.RideOfferComparatorByDateAndTime;
import br.ufrj.caronae.gcm.FirebaseTopicsHandler;
import br.ufrj.caronae.models.ActiveRide;
import br.ufrj.caronae.models.modelsforjson.RideForJson;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyActiveRidesFrag extends Fragment {

    @Bind(R.id.myRidesList)
    RecyclerView myRidesList;
    @Bind(R.id.norides_tv)
    TextView norides_tv;
    @Bind(R.id.helpText_tv)
    TextView helpText_tv;
    @Bind(R.id.helpText2_tv)
    TextView helpText2_tv;

    private MyActiveRidesAdapter adapter;

    public MyActiveRidesFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my_active_rides, container, false);
        ButterKnife.bind(this, view);

        App.getBus().register(this);

        final ProgressDialog pd = ProgressDialog.show(getContext(), "", getActivity().getString(R.string.wait), true, true);
        App.getNetworkService().getMyActiveRides(new Callback<List<RideForJson>>() {
            @Override
            public void success(List<RideForJson> rideWithUsersList, Response response) {
                if (rideWithUsersList == null || rideWithUsersList.isEmpty()) {
                    pd.dismiss();

                    myRidesList.setAdapter(new MyActiveRidesAdapter(new ArrayList<RideForJson>(), (MainAct) getActivity()));
                    myRidesList.setHasFixedSize(true);
                    myRidesList.setLayoutManager(new LinearLayoutManager(getActivity()));

                    norides_tv.setVisibility(View.VISIBLE);
                    return;
                }

                ActiveRide.deleteAll(ActiveRide.class);
                //subscribe to ride id topic
                //TODO: if statement not required, firebase does not stores token, remove old gcm code
//                if (!SharedPref.getUserGcmToken().equals(SharedPref.MISSING_PREF)) {
//                    Log.i("getMyActiveRides", "i have gcm token");
                    for (RideForJson rideWithUsers : rideWithUsersList) {
                        int rideId = rideWithUsers.getId().intValue();
                        rideWithUsers.setDbId(rideId);
                        //TODO: remove old gcm code
//                        new CheckSubGcmTopic().execute(rideId + "");

                        FirebaseTopicsHandler.subscribeToTopic(rideId + "");

                        new ActiveRide(rideWithUsers.getDbId(),rideWithUsers.isGoing(), rideWithUsers.getDate()).save();
                    }
//                } else {
//                    Log.i("getMyActiveRides", "i DO NOT have gcm token");
//                }

                Collections.sort(rideWithUsersList, new RideOfferComparatorByDateAndTime());
                adapter = new MyActiveRidesAdapter(rideWithUsersList, (MainAct) getActivity());
                myRidesList.setAdapter(adapter);
                myRidesList.setHasFixedSize(true);
                myRidesList.setLayoutManager(new LinearLayoutManager(getActivity()));

                helpText_tv.setVisibility(View.VISIBLE);
                helpText2_tv.setVisibility(View.VISIBLE);
                pd.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                pd.dismiss();

                norides_tv.setVisibility(View.VISIBLE);
                Util.toast(R.string.frag_myactiverides_errorGetActiveRides);

                try {
                    Log.e("getMyActiveRides", error.getMessage());
                } catch (Exception e) {//sometimes RetrofitError is null
                    Log.e("getMyActiveRides", e.getMessage());
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        String rideId = SharedPref.getRemoveRideFromList();
        if (!rideId.equals(SharedPref.MISSING_PREF)) {
            removeRideFromList(rideId);
        }
    }

    public void removeRideFromList(String rideId) {
        adapter.remove(Integer.valueOf(rideId));
        SharedPref.removeRemoveRideFromList();
        Log.i("removeRideFromList,actv", "remove called");
    }
}
