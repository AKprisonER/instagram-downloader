package com.dl.lvak.insta.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.dl.lvak.insta.Models.User;
import com.dl.lvak.insta.R;
import com.dl.lvak.insta.Networking.VolleySingleton;
import com.dl.lvak.insta.listeners.CustomItemClickListener;

import java.util.List;

/**
 * Created by HemantSingh on 21/10/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private List<User> mDataset;
    private CustomItemClickListener listener;
    public Boolean editMode = false;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder  {
        // each data item is just a string in this case
        public View mItemView;
        public  TextView nameTV, followerTV;
        public ImageButton editButton;
        public NetworkImageView proPicIB;

        public ViewHolder(View v) {
            super(v);
            mItemView = v;
            nameTV = (TextView) v.findViewById(R.id.textViewName);
            proPicIB = (NetworkImageView) v.findViewById(R.id.imageButton2);
            followerTV = (TextView) v.findViewById(R.id.textViewFollowers);
            editButton = (ImageButton) v.findViewById(R.id.imageButton);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<User> myDataset, CustomItemClickListener listener, Boolean editMode) {
        mDataset = myDataset;
        this.listener = listener;
        this.editMode = editMode;
    }

    @Override
    public int getItemViewType(int position) {

        return editMode ? 1 : 0;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        final ViewHolder vh = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vh.getAdapterPosition());
            }
        });
        if (viewType == 0 )
        vh.editButton.setVisibility(View.GONE);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameTV.setText(mDataset.get(position).getItems().first().getUser().getFull_name());
        Log.wtf( "onBindViewHolder: ", mDataset.get(position).getItems().first().getUser().getProfilePicture().replace("s150x150","s640x640"));
         holder.proPicIB.setImageUrl(mDataset.get(position).getItems().first().getUser().getProfilePicture().replace("s150x150","s640x640"), VolleySingleton.getInstance().getImageLoader());
        holder.followerTV.setText(mDataset.get(position).getItems().first().getUser().getUsername());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onDeleteClick(position);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}



