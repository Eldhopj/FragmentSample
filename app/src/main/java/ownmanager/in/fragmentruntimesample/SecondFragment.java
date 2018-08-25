package ownmanager.in.fragmentruntimesample;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SecondFragment extends Fragment {
    private FragmentMessageListener messageListener; // callback for the interface
    private EditText editText;
    private Button sendBtn;

    /** interface to communicate messages to activity*/
    public interface FragmentMessageListener {
        //NOTE : We can use String instead of CharSequence, but we don't need to convert CharSequence into string
        void onMessageRead(CharSequence message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        editText = view.findViewById(R.id.editText);
        sendBtn = view.findViewById(R.id.sendBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence message = editText.getText(); //By using CharSequence we don't need to convert into string
                messageListener.onMessageRead(message); //Sends this message using this interface method to our activity , and in activity we implement this method and fetch the message
            }
        });

        return view;
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Telling where to send this message
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

}
