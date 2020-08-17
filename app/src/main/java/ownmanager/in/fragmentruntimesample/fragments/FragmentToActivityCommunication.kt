package ownmanager.`in`.fragmentruntimesample.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*
import ownmanager.`in`.fragmentruntimesample.FragmentMessageListener
import ownmanager.`in`.fragmentruntimesample.R
import ownmanager.`in`.fragmentruntimesample.viewModel.SharedViewModel

/*** Implemented between FragmentToActivityCommunication ->  XmlFragment*/

class FragmentToActivityCommunication : Fragment() {
    private var toastMessage: String? = null
    private var messageListener // callback for the interface
            : FragmentMessageListener? = null
    private var viewModel: SharedViewModel? = null

    var i = 0

    /**
     * Checking whether interface is implemented
     * onAttach -> this is where the fragment attach to its host activity
     */
    override fun onAttach(context: Context) { // This method will call when the fragment is attached to the activity
        super.onAttach(context)
        messageListener =
            if (context is FragmentMessageListener) // checking whether our activity implements this interface
            {
                context
            } else {
                throw RuntimeException("$context Must implement FragmentMessageListener")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)
        getExtras()
        toastFromMainActivity(toastMessage)

        sendMessageUsingViewModel()

        view.openFragmentInsideFragment
            .setOnClickListener(View.OnClickListener {
                i++
                fragmentTransaction(SingleFragmentMultipleInstance.newInstance("", i))
            })


        return view
    }


    /**Sending message using view model */
    private fun sendMessageUsingViewModel() {
        view?.sendBtn?.setOnClickListener(View.OnClickListener {
            val message: CharSequence =
                editText.text //By using CharSequence we don't need to convert into string
            messageListener?.onMessageRead(message) //Sends this message using this interface method to our activity , and in activity we implement this method and fetch the message
            viewModel?.setText(editText.text)
        })
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(activity!!).get<SharedViewModel>(SharedViewModel::class.java)
    }

    override fun onDetach() { // this runs when we detach fragment from the activity
        super.onDetach()
        messageListener = null
    }

    fun toastFromMainActivity(message: String?) {
        message.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getExtras() {
        val args = arguments
        if (args != null) {
            toastMessage = args.getString("toastMessage", null)
        }
    }

    private fun fragmentTransaction(fragmentToSet: Fragment) {
        /**fragmentManager -> responsible for starting and completing the fragment transactions  */
        val transaction =
            childFragmentManager.beginTransaction() //to get FragmentTransaction object
        val fragment =
            childFragmentManager.findFragmentByTag(fragmentToSet.javaClass.name)
        if (fragment != null) {
            transaction.replace(
                R.id.fragmentContainer,
                fragment,
                fragment.javaClass.name
            ) // If fragment is already present replace whatever is in the fragment_container view with this fragment
        } else {
            transaction.replace(
                R.id.fragmentContainer,
                fragmentToSet,
                fragmentToSet.javaClass.name
            ) // Replace whatever is in the fragment_container view with this fragment
        }
        transaction.addToBackStack(null) // and add the transaction to the back stack so the user can navigate back (Optional)
        transaction.commit()
    }
}