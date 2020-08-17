package ownmanager.`in`.fragmentruntimesample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ownmanager.`in`.fragmentruntimesample.fragments.FragmentToActivityCommunication
import ownmanager.`in`.fragmentruntimesample.fragments.XmlFragment
import ownmanager.`in`.fragmentruntimesample.fragments.nonImportant.HomeFragment

/**Aim : To create a RunTimeFragment with buttons inside fragment activities
 * commit 3 : Fragment to Activity communication
 * Communication between fragment and activity using interfaces
 * code implemented in FragmentToActivityCommunication and MainActivity
 * Commit 4:Adding a Fragment using XML
 * create a fragment with xml file as usual
 * on MainActivity XML file add fragment (Drag and drop is better) check activity_main.xml
 * Commit 5: Communication between fragments and activity (Improved) and fragment to fragment
 * We cant communicate Fragment to Fragment directly because there is no connection,First we have to send it to the host activity then to fragment
 * Message send from FragmentToActivityCommunication receives in MainActivity and from there sends to XmlFragment
 * Commit 6: Important : getChildFragmentManager : for placing and managing Fragments inside Fragment (check : FragmentToActivityCommunication)
 *
 * Communication from activity to fragment (Check : FragmentToActivityCommunication  and MainActivity)
 * 1. Already opened
 * 2. Not opened
 *
 * Sharing data between fragments using sharedViewModel
 * Implemented between FragmentToActivityCommunication ->  XmlFragment
 */
class MainActivity : AppCompatActivity(), FragmentMessageListener { // Implement the interfaces
    private lateinit var xmlFragment: XmlFragment
    private var fragmentToActivityCommunication: FragmentToActivityCommunication? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xmlFragment = XmlFragment() // Instance of xml fragment
        /**if you want to add a fragment into the container onCreate */
        if (fragmentContainer != null) { // check whether there is container or not
            if (savedInstanceState != null) { // prevents recreating of fragments on state changes.
                return
            }
            fragmentTransaction(HomeFragment())
        }
    }

    /**Communication
     * 1.if fragment is open, the the instance and pass the value
     * 2.If fragment is not open, we cannot communicate so we have to navigate into the fragment first, and pass the values through setArguments
     */
    fun communicatingToFragment(view: View?) {
        if (communicationFragment != null) { // checking whether the instance is available
            communicationFragment!!.toastFromMainActivity("hello communication successful")
        } else {
            createCommunicationFragment()
        }
    }

    /**
     *Creating an fragment and passing the value
     * */
    private fun createCommunicationFragment() {
        val communicationFragment =
            FragmentToActivityCommunication()
        val args = Bundle().apply {
            putString("toastMessage", "hello communication successful")
        }
        communicationFragment.arguments = args
        fragmentTransaction(communicationFragment)
    }


    /**Receiving message from fragment */
    override fun onMessageRead(message: CharSequence?) {
        messageDisplayTV.text = message // Setting up message into the MainActivity

        /*NOTE : We cant communicate Fragment to Fragment directly because there is no connection,
        * First we have to send it to the host activity then to fragment*/

        // UPDATE : Using SharedView model we can communicate between fragments
        if (message != null) {
            xmlFragment.messageFromActivity(message)
            /** Sending message to XML fragment */
        }

    }


    /**
     * Getting already existing fragments from Fragment Manager*/
    private val communicationFragment: FragmentToActivityCommunication?
        get() {
            var communicationFragment: FragmentToActivityCommunication? = null
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                if (fragment is FragmentToActivityCommunication) {
                    communicationFragment = fragment
                    break
                }
            }
            return communicationFragment
        }

    /**Fragment transition */
    private fun fragmentTransaction(fragmentToSet: Fragment) {
        /**fragmentManager -> responsible for starting and completing the fragment transactions  */
        val transaction =
            supportFragmentManager.beginTransaction() //to get FragmentTransaction object
        val fragment =
            supportFragmentManager.findFragmentByTag(fragmentToSet.javaClass.name)
        with(transaction) {
            if (fragment != null) {
                replace(
                    R.id.fragmentContainer,
                    fragment,
                    fragment.javaClass.name
                ) // If fragment is already present replace whatever is in the fragment_container view with this fragment
            } else {
                replace(
                    R.id.fragmentContainer,
                    fragmentToSet,
                    fragmentToSet.javaClass.name
                ) // Replace whatever is in the fragment_container view with this fragment
            }
            addToBackStack(null) // and add the transaction to the back stack so the user can navigate back (Optional)
            commit()
        }
    }

    /**
     * For refreshing fragment
     */
    private fun refreshCommunicationFragment() {
        val frg: Fragment? = communicationFragment
        val ft =
            supportFragmentManager.beginTransaction()
        if (frg != null) {
            if (frg.isAdded) {
                with(ft) {
                    detach(frg)
                    attach(frg)
                    commitAllowingStateLoss()
                }
            }
        }
        fragmentToActivityCommunication = communicationFragment
    }
}