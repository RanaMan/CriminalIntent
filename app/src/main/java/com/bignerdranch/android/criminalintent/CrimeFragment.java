package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Rememeber, a fragmenet is a "replaceable activity" and allows for the separation of concerns...
 * I am a bit lots as to why we need and "activity" and a "Fragement"... why not just one activity and
 * then replace the Fragments??
 */

public class CrimeFragment extends Fragment {

    //This is for our bundle
    private static final String ARG_CRIME_ID = "crime_id";

    private Crime       mCrime;
    private EditText    mTitleField;
    private Button      mDateButton;
    private CheckBox    mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeID);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    /* This is the stardard "start method" */
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID)getArguments().getSerializable(ARG_CRIME_ID);

        /*
        The Crimelab is static so you can leverage that call. (The method is also static). We are
         going to use arguements in a bundle next...
         */

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);

    }


    /*
    It is important that you inflate your own view with a fragment because you need to be able
    to pass the view back to the controller which is actually hosting it for you. In a regular activity
    it is hosted right there which is why you can complete the inflation in the onCreate method.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //We pass in false here since the we will ad the view in the activity's code
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        //We need to use the V here to get the ID, as we inflated it on our own.
        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This is left blank
            }

            //Update the Crime's model to have the right title.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This is left blank
            }
        });

        //Set the date of the button to the crime's date, and disable it
        mDateButton = (Button)v.findViewById(R.id.crime_date);

        DateFormat df = new android.text.format.DateFormat();
        Log.d(TAG, "onCreateView: My date is [" + df.format("E, MM, dd,yyyy", mCrime.getDateOccoured())+']');

        mDateButton.setText(df.format("E, MM, dd,yyyy", mCrime.getDateOccoured()));
        mDateButton.setEnabled(false);


        //When the box is set, update the value within the crime object. (Of course we need to set
        //it when we load a crime, but I am sure we will get to that.
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(mSolvedCheckBox.isChecked());
            }
        });


        return v;
    }

}
