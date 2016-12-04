package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by rana_ on 12/3/2016.
 */

public class CrimeFragment extends Fragment {

    private Crime       mCrime;
    private EditText    mTitleField;
    private Button      mDateButton;
    private CheckBox    mSolvedCheckBox;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }


    /*
    It is important that you inflate your own view with a fragment because you need to be able
    to pass the view back to the controller which is actually hosting it for you. In a regular activity
    it is hosted right there which is why you can complete the inflation in the onCreate method.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup conatiner, Bundle savedInstanceState){
        //We pass in false here since the we will ad the view in the activity's code
        View v = inflater.inflate(R.layout.fragment_crime, conatiner, false);

        //We need to use the V here to get the ID, as we inflated it on our own.
        mTitleField = (EditText)v.findViewById(R.id.crime_title);

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
        mDateButton.setText(mCrime.getDateOccoured().toString());
        mDateButton.setEnabled(false);


        //When the box is set, update the value within the crime object. (Of course we need to set
        //it when we load a crime, but I am sure we will get to that.
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(mSolvedCheckBox.isChecked());
            }
        });


        return v;
    }

}
