package edu.sdsu.cs.cs646.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ListActivity extends AppCompatActivity implements Communicator {
    private Button mBackButton;
    private String mSelectedText;
    private Intent toPassBack = getIntent();
    private String none = "None Selected";
    Intent getToPassBack = new Intent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment);


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        CountriesFragment countriesFragment = new CountriesFragment();
        SportsFragment sportsFragment = new SportsFragment();
       // fragmentTransaction.add(R.id.list_activity_countries, sportsFragment);
        fragmentTransaction.add(R.id.list_activity_sports, countriesFragment);
        fragmentTransaction.commit();

        mBackButton = (Button)findViewById(R.id.list_back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void respond(String data) {
        mSelectedText = data.toString();
        toPassBack.putExtra("editTextValue", mSelectedText);
        setResult(RESULT_OK, toPassBack);
    }
}