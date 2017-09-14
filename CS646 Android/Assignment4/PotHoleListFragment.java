package com.example.paultruongnguyen.assignment5;

import android.app.Activity;
import android.content.Context;
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
    private ArrayList<PotHole> currentPotHoleList;
    private TextView mIDTextView;
    private TextView mDateTextView;
    private TextView mLatitude;
    private TextView mLongitude;
    private PotHole potHole;
    private int batchNumberCounter = 0;
    private String convertStart = "0";
    LinearLayoutManager mLayoutManager;
    private RequestQueue queue;
    private static PotHoleListFragment mInstance;
    private PotHoleAdapter mPotHoleAdapter;
    String TAG = "test";
    private Callbacks mCallbacks;


    /**
     * Required interface for hosting activities
     */
    public interface Callbacks {
        void onPotHoleSelected(PotHole potHole);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static synchronized PotHoleListFragment getInstance() {
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pothole_list, container, false);

        mPotHoleRecyclerView = (RecyclerView)
                view.findViewById(R.id.pothole_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mPotHoleRecyclerView.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(getActivity());
        currentPotHoleList = new ArrayList<>();
        MakeJsonArrayReq(currentPotHoleList,convertStart);
        mPotHoleAdapter = new PotHoleAdapter(currentPotHoleList);
        mPotHoleRecyclerView.setAdapter(mPotHoleAdapter);
        updateUIAdapter();

        mPotHoleRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    Log.i(TAG, "Flung to update!");
                    batchNumberCounter++;
                    String convert = Integer.toString(batchNumberCounter);
                    MakeJsonArrayReq(currentPotHoleList, convert);
                    mPotHoleAdapter.notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUIAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void updateUIAdapter() {
        mPotHoleRecyclerView.setAdapter(new PotHoleAdapter(currentPotHoleList));
    }

    private class PotHoleHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public PotHoleHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mIDTextView = (TextView) itemView.findViewById(R.id.list_item_pothole_id_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_pothole_date_text_view);
            mLatitude = (TextView) itemView.findViewById(R.id.list_item_pothole_latitude_text_view);
            mLongitude = (TextView) itemView.findViewById(R.id.list_item_pothole_longitude_text_view);
        }

        public void bindPotHole(PotHole currentPotHole) {
            mIDTextView.setText("Entry ID: " + currentPotHole.getId());
            mDateTextView.setText("Date Reported: " + currentPotHole.getDate());
            mLatitude.setText("Latitude: " + currentPotHole.getLatitude());
            mLongitude.setText("Longitude: " + currentPotHole.getLongitude());
        }


        @Override
        public void onClick(View v) {
            int itemPosition = mPotHoleRecyclerView.getChildPosition(v);
            PotHole potHole = currentPotHoleList.get(itemPosition);
            mCallbacks.onPotHoleSelected(potHole);
            if (queue != null) {
                queue.cancelAll(mPotHoleRecyclerView);
            }

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


    private void MakeJsonArrayReq(final ArrayList<PotHole> currentPotHoleAdded, String batchNumber) {
        final ConnectivityManager conMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {

        final String url = "http://bismarck.sdsu.edu/city/batch" +
                "?type=street&size=10&batch-number=" + batchNumber;
        JsonArrayRequest jreq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject whitneyJsonObject = response.getJSONObject(i);
                                potHole = new PotHole();

                                String id = whitneyJsonObject.getString("id");
                                String latitude = whitneyJsonObject.getString("latitude");
                                String longitude = whitneyJsonObject.getString("longitude");
                                String type = whitneyJsonObject.getString("type");
                                String description = whitneyJsonObject.getString("description");
                                String date = whitneyJsonObject.getString("created");
                                String imagetype = whitneyJsonObject.getString("imagetype");

                                potHole.setId(id);
                                potHole.setLatitude(latitude);
                                Log.i(TAG, latitude);
                                potHole.setLongitude(longitude);
                                potHole.setType(type);
                                potHole.setDescription(description);
                                potHole.setDate(date);
                                potHole.setImageType(imagetype);
                                currentPotHoleAdded.add(potHole);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mPotHoleAdapter = new PotHoleAdapter(currentPotHoleAdded);
                        updateUIAdapter();
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
