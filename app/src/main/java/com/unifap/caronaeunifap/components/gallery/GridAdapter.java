package com.unifap.caronaeunifap.components.gallery;

/*
 * Created by Guillaume on 17/11/2016.
 */

import android.content.Context;
import android.view.ViewGroup;

import java.io.File;
import java.lang.ref.WeakReference;

import com.unifap.caronaeunifap.commons.adapters.RecyclerViewAdapterBase;
import com.unifap.caronaeunifap.commons.adapters.ViewWrapper;

class GridAdapter extends RecyclerViewAdapterBase<File, MediaItemView> implements MediaItemViewListener {

    private final Context context;

    GridAdapter(Context context) {
        this.context = context;
    }

    private WeakReference<GridAdapterListener> mWrListener;

    void setListener(GridAdapterListener listener) {
        this.mWrListener = new WeakReference<>(listener);
    }

    @Override
    protected MediaItemView onCreateItemView(ViewGroup parent, int viewType) {
        return new MediaItemView(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<MediaItemView> viewHolder, final int position) {
        MediaItemView itemView = viewHolder.getView();
        itemView.setListener(this);
        itemView.bind(mItems.get(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClickItem(File file) {
        mWrListener.get().onClickMediaItem(file);
    }
}