package ownmanager.in.fragmentruntimesample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
                MainActivity.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,new SecondFragment(),null)
                        .commit();
            }
        });
        return view;
    }

}
