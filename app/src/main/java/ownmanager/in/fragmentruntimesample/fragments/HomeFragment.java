package ownmanager.in.fragmentruntimesample.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import ownmanager.in.fragmentruntimesample.R;


public class HomeFragment extends Fragment {

    private Button firstFragment;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the xml layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false); // create a view class object

        firstFragment = view.findViewById(R.id.firstFragmentBtn);

        // Moving from Home Fragment to First Fragment
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager() //Getting getSupportFragmentManager from Activity since its replacing the container inside the MainActivity XML
                        .beginTransaction()
                        .replace(R.id.fragment_container,new FirstFragment(),null)
                        .addToBackStack(null) // instead of destroy it will stop the fragment -> makes navigation between fragments possible
                        .commit();
            }
        });

        return view;
    }
}
