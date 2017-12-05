package com.luigivampa92.moviereviews.ui

import android.support.v7.widget.RecyclerView
import com.luigivampa92.moviereviews.domain.Review

class MovieReviewViewHolder constructor (private val view: MovieReviewView) : RecyclerView.ViewHolder(view) {

    fun bind(review: Review) {
        view.setItem(review)
    }
}