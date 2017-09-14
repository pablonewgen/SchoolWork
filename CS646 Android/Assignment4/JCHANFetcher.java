package com.example.paultruongnguyen.assignment4;

/**
 * Created by paultruongnguyen on 3/20/16.
 */

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Environment;
        import android.util.Base64;
        import android.util.Log;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedInputStream;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.ArrayList;
        import java.util.List;

public class JCHANFetcher {
    PotHole item;

    private static final String TAG = "JCHANFetcher";

    private static final String API_KEY = "REPLACE_ME_WITH_A_REAL_KEY";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<PotHole> fetchItems() {

        List<PotHole> items = new ArrayList<>();

        try {
            String url = Uri.parse("http://bismarck.sdsu.edu/city/batch")
                    .buildUpon()
                    .appendQueryParameter("type", "street")
                    .appendQueryParameter("size", "15")
                    .appendQueryParameter("batch-number", "0")
                    //.appendQueryParameter("end-id", "15")
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONArray jsonBody = new JSONArray(jsonString);
            parseItems(items, jsonBody);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<PotHole> items, JSONArray jsonBody)
            throws IOException, JSONException {

        for (int i = 0; i < jsonBody.length(); i++) {
            JSONObject whitneyJsonObject = jsonBody.getJSONObject(i);

            item = new PotHole();

            /*Base64 decoder = new Base64();
            byte[] imgBytes = decoder.decode(value);
            FileOutputStream osf = new FileOutputStream(new File("yourImage.png"););
            osf.write(imgBytes);
            osf.flush(); */

            String id = whitneyJsonObject.getString("id");
            String latitude = whitneyJsonObject.getString("latitude");
            String longitude = whitneyJsonObject.getString("longitude");
            String type = whitneyJsonObject.getString("type");
            String description = whitneyJsonObject.getString("description");
            String date = whitneyJsonObject.getString("created");
            String imagetype = whitneyJsonObject.getString("imagetype");

            //String image = whitneyJsonObject.getString("image");
            //Log.i("IMAGE", image);

            item.setId(id);
            item.setLatitude(latitude);
            item.setLongitude(longitude);
            item.setType(type);
            item.setDescription(description);
            item.setDate(date);
            item.setImageType(imagetype);
            items.add(item);
        }
    }

    public Bitmap download_Image(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Hub", "Error getting the image from server : " + e.getMessage().toString());
        }
        return bm;
    }

    /*private File imageFile() {
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if ( externalFilesDir == null ) return null;
        return new File(externalFilesDir, item.getPhotoFilename());
    }*/




}

