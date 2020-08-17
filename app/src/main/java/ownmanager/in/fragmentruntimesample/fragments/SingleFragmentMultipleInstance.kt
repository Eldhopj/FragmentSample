package ownmanager.`in`.fragmentruntimesample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_single_fragment_multiple_instance.view.*
import ownmanager.`in`.fragmentruntimesample.R

/**
 * A simple [Fragment] subclass.
 * Use the [SingleFragmentMultipleInstance.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SingleFragmentMultipleInstance : Fragment() {
    private var param1: String? = null
    private var param2: Int? = null


    companion object {
        //Creates new instance
        fun newInstance(param1: String, param2: Int) =
            SingleFragmentMultipleInstance().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_single_fragment_multiple_instance,
            container, false
        )
        setValues(view)
        return view
    }

    private fun setValues(view: View) {
        view.instanceCount?.text = param2.toString()
    }
}