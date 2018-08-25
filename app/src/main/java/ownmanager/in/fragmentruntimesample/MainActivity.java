package ownmanager.in.fragmentruntimesample;

/**Aim : To create a RunTimeFragment with buttons inside fragment activities
 *      navigate through Fragments
 *      Communications
 *      Fragment using XML
 *
 * commit 2 : Adding navigation
 *          implement .addToBackStack() before commit
 * commit 3 : Fragment to Activity communication
 *          Communication between fragment and activity using interfaces
 *          code implemented in SecondFragment and MainActivity
 * Commit 4:Adding a Fragment using XML
 *          create a fragment with xml file as usual
 *          on MainActivity XML file add fragment (Drag and drop is better) check activity_main.xml
 * Commit 5: Communication between fragments and activity (Improved) and fragment to fragment
 *          We cant communicate Fragment to Fragment directly because there is no connection,First we have to send it to the host activity then to fragment
 *          Message send from SecondFragment receives in MainActivity and from there sends to XmlFragment
 * */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SecondFragment.FragmentMessageListener{ // Implement the interfaces

    private XmlFragment xmlFragment;

    public static FragmentManager fragmentManager;
    FrameLayout fragmentConatiner;
    TextView messageDisplayTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xmlFragment = new XmlFragment(); // Instance of second fragment

        fragmentConatiner = findViewById(R.id.fragment_container);
        messageDisplayTV = findViewById(R.id.displayTV);

        fragmentManager = getSupportFragmentManager(); // object of fragment manager

        /**if you want to add a fragment into the container onCreate*/
        if(fragmentConatiner != null){ // check whether there is container or not

            if(savedInstanceState != null){ // prevents overlapping of fragments on recreating activities eg: on Navigation
                return;
            }
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();// object of fragment transaction
            fragmentTransaction.add(R.id.fragment_container,new HomeFragment(),null);// Replacing container with homeFragment
            fragmentTransaction.commit();
        }
    }

    /**Receiving message from fragment*/
    @Override
    public void onMessageRead(CharSequence message) {
        messageDisplayTV.setText(message);// Setting up message into the MainActivity

        /*NOTE : We cant communicate Fragment to Fragment directly because there is no connection,
        * First we have to send it to the host activity then to fragment*/
        //NOTE : If you want to send message when the fragment is created "https://www.youtube.com/watch?v=HZYYjY2NSKk&list=PLrnPJCHvNZuBkhcesO6DfdCghl6ZejVPc"
        xmlFragment.messageFromActivity(message); /** Sending message to XML fragment*/
    }
}
