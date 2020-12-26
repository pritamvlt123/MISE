package com.example.mise.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mise.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileBasicInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileBasicInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var classnam = ""
        val view: View = inflater.inflate(R.layout.fragment_profile_basic_info, container, false)
        val sharedPreferences: SharedPreferences = view.context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharednameValue = sharedPreferences.getString("name_key","")
        val sharedemailValue = sharedPreferences.getString("email_key","")
        val sharedmobileValue = sharedPreferences.getString("mobile_key","")
        val sharedgenderValue = sharedPreferences.getString("gender_key","")
        val shareddobValue = sharedPreferences.getString("dob_key","")
        val sharedrelValue = sharedPreferences.getString("rel_key","")
        val sharedclassValue = sharedPreferences.getString("class_key","")
        val sharedgurdValue = sharedPreferences.getString("guardian_key","")
        val nameVal = view.findViewById<TextView>(R.id.nameVal)
        val emailval = view.findViewById<TextView>(R.id.emailval)
        val mobileVal = view.findViewById<TextView>(R.id.mobileVal)
        val genderVal = view.findViewById<TextView>(R.id.genderVal)
        val dobval = view.findViewById<TextView>(R.id.dobval)
        val relVal = view.findViewById<TextView>(R.id.relVal)
        val classVal = view.findViewById<TextView>(R.id.classVal)
        val gurdianVal = view.findViewById<TextView>(R.id.gurdianVal)


        nameVal.text = sharednameValue
        emailval.text = sharedemailValue
        mobileVal.text = sharedmobileValue
        genderVal.text = sharedgenderValue
        dobval.text = shareddobValue
        relVal.text = sharedrelValue
        val clsvl = (sharedclassValue?.toInt())?.minus(1)
        if (clsvl == 0){
            classnam = "KG"
        }
        else if (clsvl == 1){
            classnam = "I"
        }
        else if (clsvl == 2){
            classnam = "II"
        }
        else if (clsvl == 3){
            classnam = "III"
        }
        else if (clsvl == 4){
            classnam = "IV"
        }
        else if (clsvl == 5){
            classnam = "V"
        }
        else if (clsvl == 6){
            classnam = "VI"
        }
        else if (clsvl == 7){
            classnam = "VII"
        }
        else if (clsvl == 8){
            classnam = "VIII"
        }
        else if (clsvl == 9){
            classnam = "IX"
        }
        else if (clsvl == 10){
            classnam = "X"
        }
        else if (clsvl == 11){
            classnam = "XI"
        }
        else if (clsvl == 12){
            classnam = "XII"
        }

        classVal.text = classnam//clsvl.toString()
        gurdianVal.text = sharedgurdValue
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileBasicInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileBasicInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}