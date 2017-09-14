package com.example.paultruongnguyen.assignment4;

import android.support.v4.app.Fragment;

public class PotHoleListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PotHoleListFragment();
    }
}
