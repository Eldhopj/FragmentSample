package ownmanager.in.fragmentruntimesample.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ownmanager.in.fragmentruntimesample.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleFragmentMultipleInstance#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleFragmentMultipleInstance extends Fragment {

    // Keys for Bundle
    private static final String KEY_1 = "param1";
    private static final String KEY_2 = "param2";


    private String mParam1;
    private String mParam2;


    public SingleFragmentMultipleInstance() {
        // Required empty public constructor
    }

    // Call this to get the new instances - >  SingleFragmentMultipleInstance.newInstance("1","2");

    //Creates new instance
    public static SingleFragmentMultipleInstance newInstance(String param1, String param2) {
        SingleFragmentMultipleInstance fragment = new SingleFragmentMultipleInstance();
        Bundle args = new Bundle();
        args.putString(KEY_1, param1);
        args.putString(KEY_2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(KEY_1);
            mParam2 = getArguments().getString(KEY_2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_fragment_multiple_instance, container, false);
    }

}
