package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/*
So we have created a new abstract parent class which will allow us to leverage fragments in a much
easier fashion. All we need to do is ensure that we create an instance of the right one, and it will
add it will create all of the appropriate methods to implment it properly. (i.e onCreate, add the
new fragment to the manager... etc, etc.. (Saves a lot of typing)
 */
public class CrimeActivity extends SingleFragmentActivity{

    @Override
   protected Fragment createFragment(){
        return new CrimeFragment();
    }





}
