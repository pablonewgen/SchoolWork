package com.example.paultruongnguyen.assignment5;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class PotHoleFragment extends Fragment {

    private static final String ARG_REPORT_ID = "id";
    private static final String ARG_DATE = "date";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG__LONGITUDE = "longitude";
    private static final String ARG__IMAGETYPE = "imagetype";

    private TextView mID;
    private TextView mDescription;
    private Button mDateButton;
    private TextView mLatitude;
    private TextView mLongitude;
    private TextView mImagetype;
    private String report;
    private String description;
    private String emptyResponse = "No information given";
    private String date;
    private String latitude;
    private String longitude;
    private String imagetype;
    private ImageView mPhotoView;

    public static PotHoleFragment newInstance(PotHole potHole) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_REPORT_ID, potHole.getId());
        args.putSerializable(ARG_DATE, potHole.getDate());
        args.putSerializable(ARG_DESCRIPTION, potHole.getDescription());
        args.putSerializable(ARG_LATITUDE, potHole.getLatitude());
        args.putSerializable(ARG__LONGITUDE, potHole.getLongitude());
        args.putSerializable(ARG__IMAGETYPE, potHole.getImageType());

        PotHoleFragment fragment = new PotHoleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity().findViewById(R.id.detail_fragment_container) == null) {
            Bundle potHoleData = getActivity().getIntent().getExtras();

            report = potHoleData.getString("id");
            date = potHoleData.getString("date");
            description = potHoleData.getString("description");
            latitude = potHoleData.getString("latitude");
            longitude = potHoleData.getString("longitude");
            imagetype = potHoleData.getString("imagetype");
        } else {

            report = getArguments().getString("id");
            date = getArguments().getString("date");
            description = getArguments().getString("description");
            latitude = getArguments().getString("latitude");
            longitude = getArguments().getString("longitude");
            imagetype = getArguments().getString("imagetype");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pothole, container, false);

        mID = (TextView) v.findViewById(R.id.pothole_report_id);
        mID.setText(report);

        mDescription = (TextView) v.findViewById(R.id.pothole_description);
        if (description.equals("")) {
            description = emptyResponse;
        }
        mDescription.setText(description);

        mDateButton = (Button) v.findViewById(R.id.pothole_date);
        mDateButton.setText(date);
        mDateButton.setEnabled(false);

        mLatitude = (TextView) v.findViewById(R.id.pothole_latitude);
        mLatitude.setText(latitude);

        mLongitude = (TextView) v.findViewById(R.id.pothole_longitude);
        mLongitude.setText(longitude);

        mImagetype = (TextView) v.findViewById(R.id.pothole_imagetype);
        if (imagetype.equals("")) {
            imagetype = emptyResponse;
        }
        mImagetype.setText(imagetype);

        mPhotoView = (ImageView)v.findViewById(R.id.pothole_imageView_detail);
        JChanImageRequest();

        return v;
    }

    private void JChanImageRequest() {
        final ConnectivityManager conMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            final String url = "http://bismarck.sdsu.edu/city/image?id=" + report;
            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            mPhotoView.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            mPhotoView.setImageResource(R.drawable.x_no_picture);
                        }
                    });
            VolleyQueue.instance(getContext()).add(request);
        } else {
            Toast.makeText(getContext(), "No Internet Connection detected. Please connect to an " +
                            "internet source and restart this application.",
                    Toast.LENGTH_LONG).show();
        }
    }
}

