package ownmanager.in.fragmentruntimesample;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {

    private Button firstFragment;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false); // create a view class object

        firstFragment = view.findViewById(R.id.firstFragmentBtn);

        // Moving from Home Fragment to First Fragment
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container,new FirstFragment(),null)
                        .commit();
            }
        });

        return view;
    }


}
