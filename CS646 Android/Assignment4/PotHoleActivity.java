package com.example.paultruongnguyen.assignment4;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PotHoleActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PotHoleFragment();
    }

}
