package ownmanager.`in`.fragmentruntimesample.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ownmanager.`in`.fragmentruntimesample.R
import ownmanager.`in`.fragmentruntimesample.viewModel.SharedViewModel

class XmlFragment : Fragment() {
    private var viewModel: SharedViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xml, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let {
            ViewModelProvider(it).get<SharedViewModel>(SharedViewModel::class.java)
        }
        /**Receiving message from shared view model */
        viewModel?.getText()?.observe(
            viewLifecycleOwner,
            Observer<CharSequence?> { charSequence ->
                Log.d(TAG, "messageFromViewModel: $charSequence")
            })
    }

    /**Receiving message from host Activity */
    fun messageFromActivity(newText: CharSequence) {
        Log.d(TAG, "messageFromActivity: $newText")
    }

    companion object {
        private const val TAG = "XmlFragment"
    }
}