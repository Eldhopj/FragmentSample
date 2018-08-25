package ownmanager.in.fragmentruntimesample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class XmlFragment extends Fragment {

    private static final String TAG = "XmlFragment";
    public XmlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xml, container, false);
    }

    /**Receiving message from host Activity*/
    public void messageFromActivity(CharSequence newText){
        Log.d(TAG, "messageFromActivity: "+newText);
    }

}
