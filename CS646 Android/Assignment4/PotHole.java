package com.example.paultruongnguyen.assignment5;

/**
 * Created by Paul on 2/12/2016.
 */
public class PotHole {

    private static final String myUserId = "2821";
    private String mId;
    private String mLatitude;
    private String mLongitude;
    private String mDescription;
    private String mType;
    private String mDate;
    private String mImageType;

    public PotHole() {
        //Generate unique Identifier
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getImageType() {
        return mImageType;
    }

    public void setImageType(String mImageType) {
        this.mImageType = mImageType;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId() + "." + getImageType();
    }

    public String setPhotoFilename() {
        return "IMG_" + myUserId + ".png";
    }

    public String getMyUserId() {
        return myUserId;
    }
}
