package com.dl.lvak.insta.Adapters;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dl.lvak.insta.R;
import com.dl.lvak.insta.listeners.CustomItemClickListener;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by HemantSingh on 25/10/16.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private String folderName;
    private String[] mFileset;
    private final Context context;

    CustomItemClickListener listener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mItemView;
        public ImageView imgageView;

        public ViewHolder(View v) {
            super(v);
            mItemView = v;
            imgageView = (ImageView) v.findViewById(R.id.ivProfile);
        }


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GridAdapter(Context context, String folderName,String[] fileSet, CustomItemClickListener listener) {
        this.folderName = folderName;
        this.listener = listener;
        this.context = context;
        this.mFileset = fileSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.griditem, parent, false);
            // set the view's size, margins, paddings and layout parameters

            final GridAdapter.ViewHolder vh = new GridAdapter.ViewHolder(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, vh.getAdapterPosition());
                }
            });
            return vh;


    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

//        AltexImageDownloader.readFromDiskAsync(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
//                + "/" + mDataset.get(0).getUser().getUsername() + "/" + folder.list()[position]), new AltexImageDownloader.OnImageReadListener() {
//            @Override
//            public void onImageRead(Bitmap bitmap) {
//                holder.imgageView.setImageBitmap(bitmap);
//            }
//
//            @Override
//            public void onReadFailed() {
//
//            }
//        });

        if (position < mFileset.length) {
            if (mFileset[position].contains(".mp4")) {
                holder.imgageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
                                + "/" + folderName + "/" + mFileset[position],
                        MediaStore.Images.Thumbnails.MINI_KIND));

            } else {
                Picasso.with(context).load(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
                        + "/" + folderName + "/" + mFileset[position])).resize(320, 320).into(holder.imgageView);
            }

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mFileset.length;
    }


}



