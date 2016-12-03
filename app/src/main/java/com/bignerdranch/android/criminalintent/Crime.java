package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Created by rana_ on 12/3/2016.
 */

public class Crime {

    private UUID mId;
    private String mTitle;

    public Crime(){
        //generate Unique ID
        mId = UUID.randomUUID();
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
}
