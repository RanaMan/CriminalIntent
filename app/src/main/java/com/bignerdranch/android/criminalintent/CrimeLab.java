package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rana_ on 12/4/2016.
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;

    //Why a list? you need it for the view to repeat properly
    private List<Crime> mCrimes;

    /*
    Standard Singleton creation
     */
    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /*
    This is the private constructor for our singleton; don't worry about the context yet... it will
    be used in chapter 14.
     */
    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();

        //Add in a list of default crimes
        for(int i=0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime # ["+i+"]");
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime:mCrimes){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }

}
