package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class CrimeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        //Since we are using the support fragment library, we call getSUPPORTFragmentManager.. tricky
        FragmentManager fm = getSupportFragmentManager();

        //We know that our fragment is going to be put in this container
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //So if the container is null, we know we need to make it
        if (fragment == null){
            //make the fragment
            fragment = new CrimeFragment();
            //add it to the container.... like a boss!
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }





}
