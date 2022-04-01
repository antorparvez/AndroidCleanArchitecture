package com.antorparvez.androidcleanarchitecture.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antorparvez.androidcleanarchitecture.R
import com.antorparvez.androidcleanarchitecture.domain.model.Meal
import com.bumptech.glide.Glide

class SearchAdapter(
    private val list: List<Meal>,
    val onDocDetailsItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImageIV)
        val name: TextView = itemView.findViewById(R.id.mealName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.vh_img_item,
                    parent,
                    false
                )
        )}

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide
            .with(holder.itemView.context)
            .load(list[position].image)
            .centerCrop()
            .into(holder.image)

        holder.name.text=list[position].name

        holder.image.setOnClickListener {
            onDocDetailsItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}