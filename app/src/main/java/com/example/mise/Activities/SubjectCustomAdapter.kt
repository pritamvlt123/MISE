package com.example.mise.Activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mise.Activities.PackageActivity.Companion.numbers
import com.example.mise.Activities.PackageActivity.Companion.packageId
import com.example.mise.Activities.PackageActivity.Companion.seletedItemsChecked
import com.example.mise.Activities.PackageActivity.Companion.subjectCount
import com.example.mise.R
import com.example.miseexam.utility.Subjects
import kotlinx.android.synthetic.main.item_subject.view.*


class SubjectCustomAdapter(private val context: PackageActivity, private val chaptersList: List<Subjects>) :
    RecyclerView.Adapter<SubjectCustomAdapter.ViewHolder>() {
        var checkset = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(
        LayoutInflater.from(
            context
        ).inflate(R.layout.item_subject, parent, false)
    )
    }
    override fun getItemCount(): Int {
    return chaptersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.chapterName?.text = chaptersList[position].SM_Subject_Name

        holder.check.setOnCheckedChangeListener { _, isChecked ->
            //Toast.makeText(context, subjectCount+ "======================"+seletedItemsChecked.count(), Toast.LENGTH_LONG).show()
            if (isChecked){

                seletedItemsChecked.add(chaptersList[position])
                // show toast , check box is checked
            }else{
                seletedItemsChecked.remove(chaptersList[position])
                // show toast , check box is not checked
            }

                numbers.add(chaptersList[position].SM_ID.toString())
            if (subjectCount.toInt() == seletedItemsChecked.count()){
              
            }


        }


   }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val chapterName = view.subText
        val check = view.myCheckBox

    }
}