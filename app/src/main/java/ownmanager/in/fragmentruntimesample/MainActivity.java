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
 *Commit 6: Important : getChildFragmentManager : for placing and managing Fragments inside Fragment (check : FragmentToActivityCommunication)
 *Commit 7: Communication from activity to fragment (Check : FragmentToActivityCommunication  and MainActivity)
 *              1. Already opened
 *              2. Not opened
 *
 *Commit 8: Sharing data between fragments using sharedViewModel
 *         Implemented between FragmentToActivityCommunication ->  XmlFragment
 * */

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

import ownmanager.in.fragmentruntimesample.fragments.FragmentToActivityCommunication;
import ownmanager.in.fragmentruntimesample.fragments.HomeFragment;
import ownmanager.in.fragmentruntimesample.fragments.XmlFragment;

public class MainActivity extends AppCompatActivity implements FragmentMessageListener { // Implement the interfaces

    private XmlFragment xmlFragment;
    private FrameLayout fragmentContainer;
    private TextView messageDisplayTV;
    private FragmentToActivityCommunication fragmentToActivityCommunication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xmlFragment = new XmlFragment(); // Instance of second fragment

        fragmentContainer = findViewById(R.id.fragment_container);
        messageDisplayTV = findViewById(R.id.displayTV);

        /**if you want to add a fragment into the container onCreate*/
        if(fragmentContainer != null){ // check whether there is container or not
            if(savedInstanceState != null){ // prevents recreating of fragments on state changes.
                return;
            }
            fragmentTransaction(new HomeFragment());
        }
    }

    /**Communication
     * 1.if fragment is open, the the instance and pass the value
     * 2.If fragment is not open, we cannot communicate so we have to navigate into the fragment first, and pass the values through setArguments
     * */
    public void communicatingToFragment(View view) {
        if (getCommunicationFragment() != null) { // checking whether the instance is available
            getCommunicationFragment().toastFromMainActivity("hello communication successful");
        } else {
            FragmentToActivityCommunication communicationFragment = new FragmentToActivityCommunication();
            Bundle args = new Bundle();
            args.putString("toastMessage","hello communication successful");
            communicationFragment.setArguments(args);

            fragmentTransaction(communicationFragment);
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


/** Works only when the fragment is open */
    public FragmentToActivityCommunication getCommunicationFragment() {
        FragmentToActivityCommunication communicationFragment = null;
        getSupportFragmentManager();
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof FragmentToActivityCommunication) {
                communicationFragment = (FragmentToActivityCommunication) fragment;
                break;
            }
        }
        return communicationFragment;
    }


    /**Fragment transition*/
    private void fragmentTransaction(Fragment fragmentToSet) {
        /**fragmentManager -> responsible for starting and completing the fragment transactions */
        FragmentManager manager = getSupportFragmentManager(); //to get FragmentManager object
        FragmentTransaction transaction = manager.beginTransaction(); //to get FragmentTransaction object
        Fragment fragment = manager.findFragmentByTag(fragmentToSet.getClass().getName());
        if (fragment != null) {
            transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName()); // If fragment is already present replace whatever is in the fragment_container view with this fragment
        } else {
            transaction.replace(R.id.fragment_container, fragmentToSet, fragmentToSet.getClass().getName()); // Replace whatever is in the fragment_container view with this fragment
        }
        transaction.addToBackStack(null); // and add the transaction to the back stack so the user can navigate back (Optional)
        transaction.commit();
//        if (!isFinishing()) {
//            transaction.commitAllowingStateLoss();
//            manager.executePendingTransactions();
//        }
    }

    /**
     * For refreshing fragment
     */
    private void refreshCommunicationFragment() {
        Fragment frg = getCommunicationFragment();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (frg != null) {
            if (frg.isAdded()) {
                ft.detach(frg);
                ft.attach(frg);
                ft.commitAllowingStateLoss();
            }
        }
        fragmentToActivityCommunication = getCommunicationFragment();
    }
}
