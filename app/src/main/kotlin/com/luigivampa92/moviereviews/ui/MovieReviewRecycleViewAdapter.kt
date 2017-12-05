package com.luigivampa92.moviereviews.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.domain.Review

class MovieReviewRecycleViewAdapter constructor (val context: Context) : RecyclerView.Adapter<MovieReviewViewHolder>() {

    var items: List<Review> = mutableListOf<Review>()

    fun setNewItems(newItems: List<Review>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_movie_review, parent, false)
        return MovieReviewViewHolder(view as MovieReviewView)
    }

    override fun onBindViewHolder(holder: MovieReviewViewHolder?, position: Int) {
        holder!!.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}