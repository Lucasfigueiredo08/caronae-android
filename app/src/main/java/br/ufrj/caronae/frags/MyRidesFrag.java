package br.ufrj.caronae.frags;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrj.caronae.App;
import br.ufrj.caronae.R;
import br.ufrj.caronae.Util;
import br.ufrj.caronae.acts.MainAct;
import br.ufrj.caronae.adapters.MyRidesAdapter;
import br.ufrj.caronae.models.Ride;
import br.ufrj.caronae.models.modelsforjson.RideIdForJson;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyRidesFrag extends Fragment {

    @Bind(R.id.myRidesList)
    RecyclerView myRidesList;
    @Bind(R.id.norides_tv)
    TextView norides_tv;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.button2)
    Button button2;

    ArrayList<Ride> rides;

    public MyRidesFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_rides, container, false);
        ButterKnife.bind(this, view);

        rides = (ArrayList<Ride>) Ride.listAll(Ride.class);

        if (!rides.isEmpty()) {
            myRidesList.setAdapter(new MyRidesAdapter(rides, (MainAct) getActivity()));
            myRidesList.setHasFixedSize(true);
            myRidesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            norides_tv.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @OnClick(R.id.fab)
    public void fab() {
        ((MainAct) getActivity()).showRideOfferFrag();
    }

    @OnClick(R.id.button2)
    public void button2() {
        if (rides == null || rides.isEmpty())
            return;

        for (final Ride ride : rides) {
            App.getNetworkService().deleteRide(""+ride.getDbId(), new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Log.i("deleteRide", "ride " + ride.getDbId() + " deleted");
                    ride.delete();
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("deleteRide", error.getMessage());
                }
            });
        }

        Util.toast("Caronas deletadas");
        rides.clear();
        myRidesList.getAdapter().notifyDataSetChanged();
    }
}
