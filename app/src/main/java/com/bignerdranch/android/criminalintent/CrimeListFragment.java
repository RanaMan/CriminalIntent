package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by rana_ on 12/4/2016.
 */

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    /*
    This is the onCreateView Method which is required for Fragments; same as before.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Have to invflate your your layout
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        //Assign the right field from the layout
        mCrimeRecyclerView = (RecyclerView)view.findViewById(R.id.crime_recycler_view);
        //You need a layout manager.. .not sure where the getActivity came from!
        //TODO: ask wher the getactivity method came from...
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }


    /*
    This is what is called when the view regains visibiliy.
     */
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    /*
    This is done to ensure that the model is bound to the UI properly. (You do this when you start
    and when you pop back to this fragment to ensure any changes have been applied.
     */
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null){
            //If the adapter is null, then it is brand new... just get the data...
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            //Otherwise, run the notificatier that data has/may have changed
            mAdapter.notifyDataSetChanged();
        }
    }

    /*
    This is the holder of the views which is going to be recycled (i.e. the implementation of what is
    actually in the recycler....
     */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //TODO: how is is clear that mCrime has the context of the item which we are looking for?
        private Crime mCrime;

        public TextView mTitleTextView;
        public TextView mDateTextView;
        public CheckBox mSolvedCheckBox;


        /*
        Basic stuff here... get the right ID's for the items which we would like to use...
         */
        public CrimeHolder (View itemView){
            super (itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_tite_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        @Override
        public void onClick(View v){
            //Not going to show a toast, going to invoke the intent
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }

        /*
        Utility class for the binding...
         */
        public void bindCrime(Crime crime){

            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDateOccoured().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }



    }

    /*
    This is the adapter... far more important
     */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        //This is the constructor that takes in the list of crimes from the parent.
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        /*
        Override "create shit" method which is called by the RecyclerView which is defined in the
        parent class. REQUIRED METHOD
         */
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType){

            /*
            You need to inflate things on your own... Notice how we inflate the view which we made
            specifically for the line item
             */

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);

            //Create the CrimeHolder and send it back with the right View
            return new CrimeHolder(view);
        }


        /*
        This is how the view is bound to the model. Who is going to call this with the right instance
        of CrimeHolder? REQUIRED METHOD
        TODO: I don't understand who actually calls this method with the right version of CrimeHolder
         */
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position ){
            Crime crime = mCrimes.get(position);

            //We put this back in the holder since the actual elements are there...
            holder.bindCrime(crime);
        }

        /*
        Ccount! Good stuff.
         */
        @Override
        public int getItemCount(){
            return mCrimes.size();
        }
    }

}
