package com.unifap.caronaeunifap.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import com.unifap.caronaeunifap.R;
import com.unifap.caronaeunifap.frags.AllRidesListFrag;
import com.unifap.caronaeunifap.interfaces.Updatable;
import com.unifap.caronaeunifap.models.modelsforjson.RideForJson;

public class AllRidesFragmentPagerAdapter extends FragmentPagerAdapter implements Updatable {
    final public static int PAGE_GOING = 0;
    final private static int PAGE_NOT_GOING = 1;
    private final AllRidesListFrag notGoingFrag;
    private final AllRidesListFrag goingFrag;
    private final Context context;

    public AllRidesFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<RideForJson> goingRides, ArrayList<RideForJson> notGoingRides) {
        super(fm);
        this.context = context;

        Bundle goingBundle = new Bundle();
        goingBundle.putParcelableArrayList("rides", goingRides);
        goingBundle.putInt("ID", PAGE_GOING);
        goingFrag = new AllRidesListFrag();
        goingFrag.setArguments(goingBundle);

        Bundle notGoingBundle = new Bundle();
        notGoingBundle.putParcelableArrayList("rides", notGoingRides);
        notGoingBundle.putInt("ID", PAGE_NOT_GOING);
        notGoingFrag = new AllRidesListFrag();
        notGoingFrag.setArguments(notGoingBundle);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return goingFrag;
        }

        return notGoingFrag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Resources resources = context.getResources();
        if (position == 0) {
            return resources.getString(R.string.arriving_ufrj);
        }

        return resources.getString(R.string.leaving_ufrj);
    }

    @Override
    public void needsUpdating() {
        goingFrag.needsUpdating();
        notGoingFrag.needsUpdating();
    }
}