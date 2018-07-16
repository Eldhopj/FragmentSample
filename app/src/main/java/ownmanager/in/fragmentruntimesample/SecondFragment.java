package ownmanager.in.fragmentruntimesample;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SecondFragment extends Fragment {
    OnMessageReadListner messageReadListner; // callback for the interface
    private EditText editText;
    private Button sendBtn;

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
                String message = editText.getText().toString();
                messageReadListner.onMessageRead(message);
            }
        });

        return view;
    }

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * check whether if the interface implemented in the parent activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        // check whether the interface is implemented in the activity or not
        try {
            messageReadListner = (OnMessageReadListner) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "Must override on message read");
        }
    }

    // interface to communicate messages
    public interface OnMessageReadListner {
        void onMessageRead(String message);
    }
}
