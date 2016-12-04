package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by rana_ on 12/3/2016.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDateOccoured;
    private boolean mSolved;


    public Crime(){
        //generate Unique ID
        mId = UUID.randomUUID();
        mDateOccoured = new Date();
    }


    /* Auto generated Classes */
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDateOccoured() {
        return mDateOccoured;
    }

    public void setDateOccoured(Date dateOccoured) {
        mDateOccoured = dateOccoured;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
