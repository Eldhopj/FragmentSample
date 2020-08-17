package ownmanager.`in`.fragmentruntimesample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import ownmanager.`in`.fragmentruntimesample.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the xml layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_home, container, false) // create a view class object

        // Moving from Home Fragment to First Fragment
        view.firstFragmentBtn.setOnClickListener(View.OnClickListener {

            if (activity == null) return@OnClickListener
            activity!!.supportFragmentManager //Getting getSupportFragmentManager from Activity since its replacing the container inside the MainActivity XML
                .beginTransaction()
                .replace(R.id.fragmentContainer, FirstFragment(), null)
                .addToBackStack(null) // instead of destroy it will stop the fragment -> makes navigation between fragments possible
                .commit()
        })
        return view
    }
}