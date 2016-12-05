package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/*
So we have created a new abstract parent class which will allow us to leverage fragments in a much
easier fashion. All we need to do is ensure that we create an instance of the right one, and it will
add it will create all of the appropriate methods to implment it properly. (i.e onCreate, add the
new fragment to the manager... etc, etc.. (Saves a lot of typing)
 */
public class CrimeActivity extends SingleFragmentActivity{

    @Override
   protected Fragment createFragment(){
        UUID crimeID = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeID);
    }

//    /*
//    This is how we create the intent Generator for our intent, when done explicitly.
//     */
//    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

        private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent intent =  new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeID);
        return intent;
    }



}
