/**
 * Name: Paul Truong Nguyen
 * Assignment 1
 * Due February 1, 2016
 * CS646 - Android Mobile Application Development
 *
 * The following is java code that tracks activity within a simple app, displaying
 * this information on the LogCat as well as to the user via TextView.
 */

package edu.sdsu.cs.cs646.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The following is the public class of LifecycleActivity, the app that will record
 * methods being used
 */
public class LifecycleActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mClearButton;
    private static final String TAG = "LifecycleActivity";

    /**
     * onCreate is a part of the controller; code to which calls the creation of
     * widgets and objects for displaying and managing information to and from the user
     * @param savedInstanceState - refers to a Bundle; the current information state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_lifecycle);
        mTextView = (TextView) this.findViewById(R.id.mTextViewID);
        mTextView.append("onCreate(Bundle) called" + "\n");
        mClearButton = (Button) this.findViewById(R.id.clearButton);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText("");
                Toast.makeText(LifecycleActivity.this, R.string.tvCleared,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Method call onRestart, when app is recalled by the user
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart called");
        mTextView.append("onRestart called" + "\n");
    }

    /**
     * Method call onStart, where app starts all necessary functions
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called");
        mTextView.append("onStart called" + "\n");
    }

    /**
     * Method call onPause, where all activities are paused when app is not active
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause called");
        mTextView.append("onPause called" + "\n");
    }

    /**
     * onSaveInstanceState is a method to which will store current conditions and
     * activities of the app
     * @param savedInstanceState - refers to a Bundle; the current information state
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState called");
        savedInstanceState.putString("getText", mTextView.getText().toString());
        mTextView.append("onSaveInstanceState called" + "\n");
    }

    /**
     * onRestoreInstanceState is a mothod to which will restore the app to the last
     * saved bundle state from the onSaveInstanceState method.
     * @param savedInstanceState - refers to a Bundle; the current information state
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState called");
        savedInstanceState.putString("getText", mTextView.getText().toString());
        mTextView.append("onRestoreInstanceState called" + "\n");

    }

    /**
     * Method call onResume, command the resume operation of app
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called");
        mTextView.append("onResume called" + "\n");
    }

}

