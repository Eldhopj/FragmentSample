package ownmanager.`in`.fragmentruntimesample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ownmanager.`in`.fragmentruntimesample.databinding.FragmentSingleFragmentMultipleInstanceBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SingleFragmentMultipleInstance.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SingleFragmentMultipleInstance : Fragment() {

    private var _binding: FragmentSingleFragmentMultipleInstanceBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentSingleFragmentMultipleInstanceBinding
            .inflate(inflater, container, false)
        bindValues()
        return binding.root
    }

    private fun bindValues() {
        with(binding) {
            instanceCount.text = param2.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}