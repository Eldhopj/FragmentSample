package ownmanager.in.fragmentruntimesample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import ownmanager.in.fragmentruntimesample.R;


public class FirstFragment extends Fragment {
    private Button secondFragment;
    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        secondFragment = view.findViewById(R.id.secondFragmentBtn);

        // Moving from First Fragment to Second Fragment
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager() // replaces second fragment with main activity container
                        .beginTransaction()
                        .replace(R.id.fragment_container,new FragmentToActivityCommunication(),null)
                        .addToBackStack(null) // instead of destroy it will stop the fragment -> makes navigation between fragments possible
                        .commit();
            }
        });
        return view;
    }

}
