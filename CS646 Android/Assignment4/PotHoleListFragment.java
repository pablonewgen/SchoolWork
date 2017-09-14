package com.example.paultruongnguyen.assignment4;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PotHoleListFragment extends Fragment {

    private RecyclerView mPotHoleRecyclerView;
    private List<PotHole> items;
    private Button mMap;
    private Button mNewEntry;
    private TextView mIDTextView;
    private TextView mDateTextView;
    private TextView mLatitude;
    private TextView mLongitude;
    private PotHole item;
    private int batchNumberCounter;
    LinearLayoutManager mLayoutManager;
    private RequestQueue queue;
    private static PotHoleListFragment mInstance;
    private PotHoleAdapter mPotHoleAdapter;
    String TAG = "Volley Response: ";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        items = new ArrayList<>();
        batchNumberCounter = 0;
        MakeJsonArrayReq(items, "0");
        queue = Volley.newRequestQueue(getActivity());
    }

    public static synchronized PotHoleListFragment getInstance() {
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pothole_list, container, false);
        mPotHoleAdapter = new PotHoleAdapter(items);

        mMap = (Button) view.findViewById(R.id.map_button);
        mNewEntry = (Button) view.findViewById(R.id.new_entry_button);
        mNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), PotholeMakerActivity.class);
                startActivity(myIntent);
            }
        });

        mPotHoleRecyclerView = (RecyclerView) view
                .findViewById(R.id.pothole_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mPotHoleRecyclerView.setLayoutManager(mLayoutManager);

        setupAdapter();

        mPotHoleRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    Log.i(TAG, "Flung to update!");
                    batchNumberCounter += 1;
                    String convert = Integer.toString(batchNumberCounter);
                    Toast.makeText(getContext(),"Updating... " + convert,
                            Toast.LENGTH_LONG).show();
                    MakeJsonArrayReq(items, convert);
                    mPotHoleAdapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    private void setupAdapter() {
            mPotHoleRecyclerView.setAdapter(new PotHoleAdapter(items));
    }

    private class PotHoleHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        String tempID;
        String temptDate;
        String tempDescription;
        String tempLatitude;
        String tempLongitude;
        String tempImageType;

        public PotHoleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mIDTextView = (TextView) itemView.findViewById(R.id.list_item_pothole_id_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_pothole_date_text_view);
            mLatitude = (TextView) itemView.findViewById(R.id.list_item_pothole_latitude_text_view);
            mLongitude = (TextView) itemView.findViewById(R.id.list_item_pothole_longitude_text_view);
        }

        public void bindPotHole(PotHole PotHole) {
            tempID = PotHole.getId();
            temptDate = PotHole.getDate();
            tempDescription = PotHole.getDescription();
            tempLatitude = PotHole.getLatitude();
            tempLongitude = PotHole.getLongitude();
            tempImageType = PotHole.getImageType();
            mIDTextView.setText("Entry ID: " + PotHole.getId());
            mDateTextView.setText("Date Reported: " + PotHole.getDate());
            mLatitude.setText("Latitude: " + PotHole.getLatitude());
            mLongitude.setText("Longitude: " + PotHole.getLongitude());
        }


        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getActivity(), PotHoleActivity.class);
            intent.putExtra("id", tempID);
            intent.putExtra("date", temptDate);
            intent.putExtra("description", tempDescription);
            intent.putExtra("latitude", tempLatitude);
            intent.putExtra("longitude", tempLongitude);
            intent.putExtra("imagetype", tempImageType);

            if (queue != null) {
                queue.cancelAll(mPotHoleRecyclerView);
            }
            startActivity(intent);
        }
    }

    protected class PotHoleAdapter extends RecyclerView.Adapter<PotHoleHolder> {

        private List<PotHole> mPotHoles;

        public PotHoleAdapter(List<PotHole> PotHoles) {
            mPotHoles = PotHoles;
        }

        @Override
        public PotHoleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_pothole, parent, false);
            return new PotHoleHolder(view);
        }

        @Override
        public void onBindViewHolder(PotHoleHolder holder, int position) {
            PotHole potHole = mPotHoles.get(position);
            holder.bindPotHole(potHole);
        }

        @Override
        public int getItemCount() {
            return mPotHoles.size();
        }
    }

    private void MakeJsonArrayReq(final List<PotHole> list, String batchNumber) {
        final ConnectivityManager conMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {

        final String url = "http://bismarck.sdsu.edu/city/batch" +
                "?type=street&size=10&batch-number=" + batchNumber;
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    //List<PotHole> jReqList = new ArrayList<>();
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject whitneyJsonObject = response.getJSONObject(i);
                                item = new PotHole();

                                String id = whitneyJsonObject.getString("id");
                                String latitude = whitneyJsonObject.getString("latitude");
                                String longitude = whitneyJsonObject.getString("longitude");
                                String type = whitneyJsonObject.getString("type");
                                String description = whitneyJsonObject.getString("description");
                                String date = whitneyJsonObject.getString("created");
                                String imagetype = whitneyJsonObject.getString("imagetype");

                                item.setId(id);
                                item.setLatitude(latitude);
                                item.setLongitude(longitude);
                                item.setType(type);
                                item.setDescription(description);
                                item.setDate(date);
                                item.setImageType(imagetype);
                                list.add(item);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mPotHoleAdapter.notifyDataSetChanged();
                        setupAdapter();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    Log.d(TAG, "Error Response Code: " + error.networkResponse.statusCode
                            +". Issue: " + error.getMessage());
                }
            }
        });
            VolleyQueue.instance(getContext()).add(jreq);
        } else {
            Toast.makeText(getContext(),"No Internet Connection detected. Please connect to an " +
                            "internet source and restart this application.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
