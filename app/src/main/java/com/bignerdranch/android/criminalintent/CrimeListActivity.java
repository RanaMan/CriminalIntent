package com.bignerdranch.android.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by rana_ on 12/4/2016.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
