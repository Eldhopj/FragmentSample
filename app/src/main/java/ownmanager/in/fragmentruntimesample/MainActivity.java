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
 *          code implemented in FragmentToActivityCommunication and MainActivity
 * Commit 4:Adding a Fragment using XML
 *          create a fragment with xml file as usual
 *          on MainActivity XML file add fragment (Drag and drop is better) check activity_main.xml
 * Commit 5: Communication between fragments and activity (Improved) and fragment to fragment
 *          We cant communicate Fragment to Fragment directly because there is no connection,First we have to send it to the host activity then to fragment
 *          Message send from FragmentToActivityCommunication receives in MainActivity and from there sends to XmlFragment
 *Commit 6: getChildFragmentManager : for placing and managing Fragments inside Fragment (check : FragmentToActivityCommunication)
 * */

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import ownmanager.in.fragmentruntimesample.fragments.HomeFragment;
import ownmanager.in.fragmentruntimesample.fragments.XmlFragment;

public class MainActivity extends AppCompatActivity implements FragmentMessageListener { // Implement the interfaces

    private XmlFragment xmlFragment;
    private FrameLayout fragmentContainer;
    private TextView messageDisplayTV;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xmlFragment = new XmlFragment(); // Instance of second fragment

        fragmentContainer = findViewById(R.id.fragment_container);
        messageDisplayTV = findViewById(R.id.displayTV);

        /**fragmentManager -> responsible for starting and completing the fragment transactions */
        fragmentManager = getSupportFragmentManager();

        /**if you want to add a fragment into the container onCreate*/
        if(fragmentContainer != null){ // check whether there is container or not
            if(savedInstanceState != null){ // prevents recreating of fragments on state changes.
                return;
            }
             fragmentManager.beginTransaction()// to begin the fragment transaction
                     .add(R.id.fragment_container,new HomeFragment(),null)// adds the fragment into the container
                    // .replace(R.id.fragment_container,new HomeFragment(),null)// replace the fragment into the container
                     .commit();
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

    /**For passing value back to previous activity*
     * <a href https://stackoverflow.com/a/14292451/9585974 />
     */

}
