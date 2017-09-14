package com.example.paultruongnguyen.assignment5;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class PotHoleListActivity extends SingleFragmentActivity
        implements PotHoleListFragment.Callbacks {


    @Override
    protected Fragment createFragment() {
        return new PotHoleListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R. layout.activity_masterdetail;
    }

    @Override
    public void onPotHoleSelected(PotHole potHole) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = new Intent(this, PotHoleActivity.class);
            intent.putExtra("id", potHole.getId());
            intent.putExtra("date", potHole.getDate());
            intent.putExtra("description", potHole.getDescription());
            intent.putExtra("latitude", potHole.getLatitude());
            intent.putExtra("longitude", potHole.getLongitude());
            intent.putExtra("imagetype", potHole.getImageType());
            startActivity(intent);
        } else {
            Fragment newDetail = PotHoleFragment.newInstance(potHole);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }


    }
}
