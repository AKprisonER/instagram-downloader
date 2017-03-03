package com.dl.lvak.insta.listeners;

import android.view.View;

/**
 * Created by anuraggupta on 31/01/16.
 */
public interface CustomItemClickListener {
    void onItemClick(View v, int position);
    void onDeleteClick(int position);
}