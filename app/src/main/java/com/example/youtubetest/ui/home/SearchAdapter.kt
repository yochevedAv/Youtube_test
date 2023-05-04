package com.example.youtubetest.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubetest.R

class SearchAdapter(
    val  mContext: Context,
    var list: List<Courts>,
    val clickListener: (Courts) -> Unit
)  : RecyclerView.Adapter<SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.search_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.courtName.text =  list[position].courtDescription
        holder.itemView.setOnClickListener {
            clickListener(list[position])
        }
    }


}

class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val courtName = view.findViewById<TextView>(R.id.courtName)!!

}