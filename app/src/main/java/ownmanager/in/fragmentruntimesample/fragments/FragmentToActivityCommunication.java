package ownmanager.in.fragmentruntimesample.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import ownmanager.in.fragmentruntimesample.FragmentMessageListener;
import ownmanager.in.fragmentruntimesample.R;


    public class FragmentToActivityCommunication extends Fragment {
    private FragmentMessageListener messageListener; // callback for the interface
    private EditText editText;
    private Button sendBtn, openFragmentInsideFragment;
    private String toastMessage;

    public FragmentToActivityCommunication() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        editText = view.findViewById(R.id.editText);
        sendBtn = view.findViewById(R.id.sendBtn);
        openFragmentInsideFragment = view.findViewById(R.id.openFragmentInsideFragment);

        getExtras();

        if (toastMessage != null) {
            toastFromMainActivity(toastMessage);
        }

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence message = editText.getText(); //By using CharSequence we don't need to convert into string
                messageListener.onMessageRead(message); //Sends this message using this interface method to our activity , and in activity we implement this method and fetch the message
            }
        });

        openFragmentInsideFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SingleFragmentMultipleInstance();
                fragmentTransaction(fragment);
            }
        });


        return view;
    }

    /**
     * Checking whether interface is implemented
     * onAttach -> this is where the fragment attach to its host activity
     */
    @Override
    public void onAttach(Context context) { // This method will call when the fragment is attached to the activity
        super.onAttach(context);

        if (context instanceof FragmentMessageListener) // checking whether our activity implements this interface
        {
            messageListener = (FragmentMessageListener) context;
        }
        else{
            throw new RuntimeException(context.toString() + " Must implement FragmentMessageListener");
        }
    }

    @Override
    public void onDetach() { // this runs when we detach fragment from the activity
        super.onDetach();
        messageListener = null;
    }

    public void toastFromMainActivity(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void getExtras () {
        Bundle args = getArguments();
        if (args != null) {
            toastMessage = args.getString("toastMessage", null);
        }
    }

    private void fragmentTransaction(Fragment fragment) {
        /**getChildFragmentManager : for placing and managing Fragments inside Fragment.*/
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_fragment_container,fragment,null)
                .addToBackStack(null) // instead of destroy it will stop the fragment -> makes navigation between fragments possible
                .commit();
    }

}
