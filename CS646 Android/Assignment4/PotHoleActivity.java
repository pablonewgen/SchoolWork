package com.example.paultruongnguyen.assignment5;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class PotHoleActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PotHoleFragment();
    }

}
