package com.example.paultruongnguyen.assignment4;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Paul on 3/25/2016.
 */
public class PotholeMakerActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_TAKE_PHOTO = 1;
    public static final String PREFS = "Saving picture counter";
    private TextView mUserId;
    private TextView mLatitudeText;
    private TextView mLongitudeText;
    private TextView mDateText;
    private EditText mDescription;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private boolean pictureTakenChecker = false;
    private Button mSubmitButton;
    private String mDescriptionPost;
    private String mUserPost;
    private String mImageType;
    private LocationManager mLocationManager;
    private double mLatitude;
    private double mLongitude;
    private String latitudeConvert;
    private String longitudeConvert;
    String encodedBase64 = null;
    String TAG = "test";
    private final PotHole potHole = new PotHole();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_camera_and_title);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        int permissionCheck = ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000, 1, this);
        }

        mUserId = (TextView) findViewById(R.id.pothole_id_new_post);
        mUserId.setText("User ID: " + potHole.getMyUserId());
        mLatitudeText = (TextView) findViewById(R.id.pothole_latitude_new_post);
        mLatitudeText.setText("Latitude: " + mLatitude);
        mLongitudeText = (TextView) findViewById(R.id.pothole_longitude_new_post);
        mLongitudeText.setText("Longitude: " + mLongitude);
        mDateText = (TextView) findViewById(R.id.pothole_date_post);
        mDateText.setText("Date Reported: " + getCurrentTimeStamp());

        mDescription = (EditText) findViewById(R.id.pothole_new_description);
        mPhotoButton = (ImageButton) findViewById(R.id.pothole_camera);
        mPhotoView = (ImageView) findViewById(R.id.pothole_photo);
        mPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(imageFile()), "image/");
                startActivity(intent);
            }
        });
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent(v);
            }
        });

        mSubmitButton = (Button)findViewById(R.id.pothole_submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pictureTakenChecker) {
                    final ConnectivityManager conMgr = (ConnectivityManager)
                            getSystemService(Context.CONNECTIVITY_SERVICE);
                    final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                    if (activeNetwork != null && activeNetwork.isConnected()) {
                        final String URL = "http://bismarck.sdsu.edu/city/report";

                        mDescriptionPost = mDescription.getText().toString();
                        mUserPost = potHole.getMyUserId();
                        mImageType = potHole.getImageType();

                        JSONObject data = new JSONObject();
                        try {
                            data.put("type", "street");
                            data.put("latitude", mLatitude);
                            data.put("longitude", mLongitude);
                            data.put("description", mDescriptionPost);
                            data.put("user", mUserPost);
                            if (pictureTakenChecker) {
                                data.put("imagetype", mImageType);
                                data.put("image", encodedBase64);
                            } else {
                                data.put("imagetype", "none");
                            }
                        } catch (JSONException error) {
                            Log.e("rew", "JSONerror", error);
                            return;
                        }

                        JsonObjectRequest req = new JsonObjectRequest(
                                Request.Method.POST, URL, data, new
                                Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            VolleyLog.v("Response:%n %s", response.toString(4));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error.networkResponse != null) {
                                    Log.d(TAG, "Error Response Code: "
                                            + error.networkResponse.statusCode
                                            + ". Issue: " + error.getMessage());
                                    error.printStackTrace();
                                }
                            }
                        });
                        VolleyQueue.instance(v.getContext()).add(req);

                        finish();
                        Toast.makeText(v.getContext(), "Pothole has been submitted.",
                                Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(v.getContext(), "No Internet Connection detected",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "No Photo taken. Please take a photo of the" +
                                    " pothole as evidence.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private File imageFile() {
        PotHole potHole = new PotHole();
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if ( externalFilesDir == null ) return null;
        return new File(externalFilesDir, potHole.setPhotoFilename());
    }

    public void dispatchTakePictureIntent(View button) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = imageFile();
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            File photoFile = imageFile();
            if (photoFile != null) {
                pictureTakenChecker = true;
                mPhotoView.setImageURI(Uri.fromFile(imageFile()));
                encodedBase64 = encodedToBase64(imageFile());
                potHole.setImageType("png");
                mLatitudeText.setText("Latitude: " + latitudeConvert);
                mLongitudeText.setText("Longitude: " + longitudeConvert);
                mDateText.setText("Date Reported: " + getCurrentTimeStamp());
            }
            else {
                pictureTakenChecker = false;
                potHole.setImageType("none");
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        mLatitude =  location.getLatitude();
        mLongitude = location.getLongitude();
        latitudeConvert = Double.toString(mLatitude);
        longitudeConvert = Double.toString(mLongitude);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps disabled!!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "GPS enabled!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    protected static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private String encodedToBase64 (File file) {
        String encodedString = null;
        int bytesRead = (int)file.length();
        byte[] bytes = new byte[bytesRead];
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            InputStream fileInputStreamReader = new FileInputStream(file);
            while((bytesRead = fileInputStreamReader.read(bytes)) != -1) {
                output.write(bytes, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = output.toByteArray();
        encodedString = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return encodedString;
    }


}
