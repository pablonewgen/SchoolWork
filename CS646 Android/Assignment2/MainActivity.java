package edu.sdsu.cs.cs646.assignment2;

import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements Communicator {

    private Button mSelect;
    private EditText mSelectedText;
    private Spinner mSpinner;
    private String passedEditText;
    private static final int REQUEST_CODE_TIME = 0;
    private static final int REQUEST_CODE_KEYBOARD = 0;
    private static final int REQUEST_CODE_LIST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CountriesFragment countriesFragment = new CountriesFragment();
        fragmentTransaction.add(R.id.list_countries_container, countriesFragment);

        fragmentTransaction.commit();

        mSelect = (Button)findViewById(R.id.select_button);
        mSelectedText = (EditText)findViewById(R.id.edit_text);
        mSpinner = (Spinner)findViewById(R.id.activity_spinner);
        mSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("EditText", mSelectedText.getText().toString());
                if (mSpinner.getSelectedItem().toString().equals("Time Setter")) {
                    Intent timeIntent = new Intent(MainActivity.this, TimeActivity.class);
                    startActivityForResult(timeIntent, REQUEST_CODE_TIME);
                }else if (mSpinner.getSelectedItem().toString().equals("Keyboard Tester")) {
                    Intent keyboardIntent = new Intent(MainActivity.this, KeyboardActivity.class);
                    passedEditText = mSelectedText.getText().toString();
                    keyboardIntent.putExtra("topTextField",passedEditText);
                    startActivityForResult(keyboardIntent, REQUEST_CODE_KEYBOARD);
                }else if (mSpinner.getSelectedItem().toString().equals("Country Sport List")) {
                    Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
                    startActivityForResult(listIntent, REQUEST_CODE_LIST);
                }

            }
        });



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_TIME) {
            return;
        }
        switch (resultCode) {
            case RESULT_OK:
                String dataString = data.getStringExtra("editTextValue");
                mSelectedText.setText(dataString);
                break;
            case RESULT_CANCELED:
                break;
        }
    }

    @Override
    public void respond(String data) {
        mSelectedText.setText(data);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
