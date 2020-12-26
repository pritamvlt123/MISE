package com.example.mise.Activities

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mise.R


class CustomExpandableAdapter(
    context: Context, listDataHeader: List<String>,
    listChildData: HashMap<String, List<String>>
) : BaseExpandableListAdapter() {
    private val _context: Context
    private val _listDataHeader // header titles
            : List<String>

    // child data in format of header title, child title
    private val _listDataChild: HashMap<String, List<String>>
    override fun getChild(groupPosition: Int, childPosititon: Int): Any {
        return _listDataChild[_listDataHeader[groupPosition]]!![childPosititon]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        val childText = getChild(groupPosition, childPosition) as String
        if (convertView == null) {
            val layoutInflater =
                _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.about_list_item, null)
            /*val infalInflater = _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.about_list_item, null)*/
        }
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.listView)
        expandedListTextView.text = childText
        /*val txtListChild = convertView
            !!.findViewById(R.id.listView) as TextView
        txtListChild.text = childText*/
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return _listDataChild[_listDataHeader[groupPosition]]!!
            .size
    }

    override fun getGroup(groupPosition: Int): Any {
        return _listDataHeader[groupPosition]
    }

    override fun getGroupCount(): Int {
        return _listDataHeader.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        val headerTitle = getGroup(groupPosition) as String
        if (convertView == null) {
            val layoutInflater =
                _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(com.example.mise.R.layout.about_parent_list_item, null)
            /*val infalInflater = _context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.about_parent_list_item, null)*/
        }
        if (isExpanded){
            ConstantValues.categoryFlag = groupPosition;
            //Log.e("FLAG ", String.valueOf(galleryFlag));
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.title_head)
        val indicator = convertView!!.findViewById<ImageView>(R.id.image_indicator)
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = headerTitle
        /*val lblListHeader = convertView
            !!.findViewById(R.id.lblListHeader) as TextView
        lblListHeader.setTypeface(null, Typeface.BOLD)
        lblListHeader.text = headerTitle*/
        if(isExpanded) {
            indicator.setImageResource(com.example.mise.R.drawable.ic_arrow_up);
        } else {
            indicator.setImageResource(com.example.mise.R.drawable.ic_arrow_down);
        }
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    init {
        _context = context
        _listDataHeader = listDataHeader
        _listDataChild = listChildData
    }
}