package com.example.mise.Activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mise.Activities.PackageActivity.Companion.packageId
import com.example.mise.Activities.PackageActivity.Companion.seletedItemsChecked
import com.example.mise.Activities.PackageActivity.Companion.subjectCount
import com.example.mise.Activities.PackageActivity.Companion.totalPackageAmt
import com.example.mise.R
import com.example.miseexam.utility.PackageDetails
import kotlinx.android.synthetic.main.item_mindorks_chapters.view.*


class CustomAdapter(private val context: PackageActivity, private val chaptersList: List<PackageDetails>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mindorks_chapters, parent, false))
    }
    override fun getItemCount(): Int {
    return chaptersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chapterName?.text = chaptersList[position].packageName
        holder.cost?.text = "\u20B9"+chaptersList[position].packagePrice
        holder.itemView.setOnClickListener {
            context.lay11.visibility = View.VISIBLE
            subjectCount = chaptersList[position].PM_No_Of_Subject.toString()
            packageId = chaptersList[position].packageID.toString()
            totalPackageAmt = chaptersList[position].packagePrice.toString()
            seletedItemsChecked.clear()
            context.setUpRecyclerView4()
       // Toast.makeText(context, subjectCount, Toast.LENGTH_LONG).show()
    }
   }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val chapterName = view.tvChapterName
        val cost = view.cost

    }
}