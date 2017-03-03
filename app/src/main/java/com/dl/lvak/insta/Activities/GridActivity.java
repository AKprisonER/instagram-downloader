package com.dl.lvak.insta.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dl.lvak.insta.Adapters.GridAdapter;
import com.dl.lvak.insta.Models.ImageData;
import com.dl.lvak.insta.Models.User;
import com.dl.lvak.insta.MyApplication;
import com.dl.lvak.insta.Networking.AltexImageDownloader;
import com.dl.lvak.insta.Networking.VolleySingleton;
import com.dl.lvak.insta.R;
import com.dl.lvak.insta.listeners.CustomItemClickListener;
import com.dl.lvak.insta.listeners.EndlessRecyclerViewScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class GridActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;
    public CustomItemClickListener listener;
    private Realm realm;
    String userId;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        mRecyclerView = (RecyclerView) findViewById(R.id.grid_recycler_view);
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);

        final Activity activity = this;


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mLayoutManager = new GridLayoutManager(this,3);
//            mLayoutManager = new StaggeredGridLayoutManager(2,Configuration.ORIENTATION_PORTRAIT);

        }
        else{
            mLayoutManager = new GridLayoutManager(this,5);
//            mLayoutManager = new StaggeredGridLayoutManager(3,Configuration.ORIENTATION_LANDSCAPE);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
         userId = getIntent().getStringExtra("id");
//        results = realm.where(User.class).equalTo("id", userId).findAll().first().getItems();


        setTitle(getIntent().getStringExtra("username"));
//       final File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
//        + "/" + results.first().getUser().getUsername() +"/");
        final String[] files = getIntent().getStringArrayExtra("files");
       final String[] captions = getIntent().getStringArrayExtra("captions");
        listener = new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent o = new Intent(GridActivity.this, ImageTabsActivity.class);

                o.putExtra("files",files);

                o.putExtra("displayName",getIntent().getStringExtra("displayName"));
                o.putExtra("username",getIntent().getStringExtra("username"));
                o.putExtra("id", getIntent().getStringExtra("id"));
                o.putExtra("index", position);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    setSharedElementEnterTransition(new DetailsTransition());
//                    kittenDetails.setEnterTransition(new Fade());
//                    setExitTransition(new Fade());
//                    kittenDetails.setSharedElementReturnTransition(new DetailsTransition());
//                }

//                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(GridActivity.this, v.findViewById(R.id.ivProfile), getString(R.string.transition_name_circle));
//                startActivity(o, transitionActivityOptions.toBundle());
                startActivity(o);
            }

            @Override
            public void onDeleteClick(int position) {

            }
        };
        mAdapter = new GridAdapter(this,getIntent().getStringExtra("username"),files,listener);
        mRecyclerView.setAdapter(mAdapter);

         AltexImageDownloader.readFromDiskAsync(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()
                 + "/" + getIntent().getStringExtra("username") +"/" + files[new  Random().nextInt(files.length - 1)]), new AltexImageDownloader.OnImageReadListener() {
             @Override
             public void onImageRead(Bitmap bitmap) {
                 Drawable d = new BitmapDrawable(getResources(), bitmap);
                 d.setAlpha(130);
                 mRecyclerView.setBackground(d);
             }

             @Override
             public void onReadFailed() {

             }
         });
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                jsonRequestVolley("https://www.instagram.com/" + getIntent().getStringExtra("username") + "/media/?&max_id=" + getIntent().getStringExtra("lastId"));

            }
        };
        mRecyclerView.addOnScrollListener(scrollListener);
        jsonRequestVolley("https://www.instagram.com/" + getIntent().getStringExtra("username") + "/media/" );
//        mRecyclerView.setBackground(new GradientDrawable(activity.getResources().getConfiguration().orientation,));
    }
    public void jsonRequestVolley(final String volleyUrl) {
        //dialog.show();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest

                (Request.Method.GET, volleyUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("items");
                           SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(GridActivity.this);
                            Boolean hdBool = sharedPref.getBoolean("fullhdkey", true);
                            Boolean downloadVideo = sharedPref.getBoolean("videokey", false);

                           int count = array.length();
                            if (count > 0 ) {
                                Gson gson = new GsonBuilder().create();
                                String json = response.toString();
                                final User u = gson.fromJson(json, User.class);
                                realm.beginTransaction();
                                u.setId(u.getItems().first().getUser().getId());
                                realm.commitTransaction();

//                                AltexImageDownloader.writeToDisk(MainActivity.this, u.getItems().first().getUser().getProfilePicture().replace("s150x150","s1080x1080"), volleyUrl.split("/")[3].trim() + "/");
                                for (ImageData img:
                                        u.getItems() ) {
                                    if (!checkIfExists(img.getId())) {

                                        if (img.getType().contains("video")){
                                            if( downloadVideo){

                                                realm.beginTransaction();
                                                realm.where(User.class).equalTo("id", userId).findAll().first().addItem(img);

                                                realm.commitTransaction();
                                                AltexImageDownloader.writeToDisk(MyApplication.getAppContext(), img.getAlt_media_url(), volleyUrl.split("/")[3].trim() + "/");
                                            }

                                        } else {
                                            realm.beginTransaction();
                                            realm.where(User.class).equalTo("id", userId).findAll().first().addItem(img);

                                            realm.commitTransaction();
                                            if (hdBool) {
                                                AltexImageDownloader.writeToDisk(MyApplication.getAppContext(), img.getImages().getStandard_resolution().getUrl().replace("s640x640", "s1080x1080"), volleyUrl.split("/")[3].trim() + "/");
                                            }
                                            else{
                                                AltexImageDownloader.writeToDisk(MyApplication.getAppContext(), img.getImages().getStandard_resolution().getUrl(), volleyUrl.split("/")[3].trim() + "/");

                                            } }
                                    }

                                }


//                                    mAdapter.notifyItemRangeChanged();
//
//                                mAdapter.notifyDataSetChanged();
////                                mAdapter.notifyItemRangeInserted(results.size() - u.getItems().size(),results.size());

                            }
                        } catch (JSONException e) {
                            Log.e("TAG", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub


                    }
                });

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance().getRequestQueue().add(jsObjRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    public  boolean checkIfExists(String id){

        RealmQuery<ImageData> query = realm.where(ImageData.class)
                .equalTo("id", id);

        return query.count() != 0;
    }
}
