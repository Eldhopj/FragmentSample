package ownmanager.`in`.fragmentruntimesample.fragments.nonImportant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_first.view.*
import ownmanager.`in`.fragmentruntimesample.R
import ownmanager.`in`.fragmentruntimesample.fragments.FragmentToActivityCommunication

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_first, container, false)

        // Moving from First Fragment to Second Fragment
        view.secondFragmentBtn.setOnClickListener(View.OnClickListener {
            activity!!.supportFragmentManager // replaces second fragment with main activity container
                .beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    FragmentToActivityCommunication(), null
                )
                .addToBackStack(null) // instead of destroy it will stop the fragment -> makes navigation between fragments possible
                .commit()
        })
        return view
    }
}