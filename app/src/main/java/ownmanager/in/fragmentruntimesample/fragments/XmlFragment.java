package ownmanager.in.fragmentruntimesample.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ownmanager.in.fragmentruntimesample.R;
import ownmanager.in.fragmentruntimesample.viewModel.SharedViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class XmlFragment extends Fragment {

    private static final String TAG = "XmlFragment";
    private SharedViewModel viewModel;
    public XmlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xml, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);


        /**Receiving message from shared view model*/
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                Log.d(TAG, "messageFromViewModel: " + charSequence);
            }
        });
    }

    /**Receiving message from host Activity*/
    public void messageFromActivity(CharSequence newText){
        Log.d(TAG, "messageFromActivity: "+newText);
    }

}
