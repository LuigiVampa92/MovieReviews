package com.luigivampa92.moviereviews.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.domain.Review
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_movie_review_content.view.*

class MovieReviewView constructor (context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    : RelativeLayout(context, attrs, defStyleAttr) {

    constructor (context: Context) : this(context, null)
    constructor (context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_movie_review_content, this, true)
    }

    fun setItem(review: Review) {
        Picasso.with(context).load(review.mediaSrc).into(movie_review_image)
        movie_review_title.text = review.displayTitle
        movie_review_date.text = review.updatedDate
        movie_review_text.text = review.headline
        movie_review_description.text = review.summaryShort
        setTags(review)

        button_show_more.setOnClickListener {
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(review.linkUrl))
            context.startActivity(viewIntent)
        }
    }

    private fun setTags(review: Review) {
        val showTagCriticsPick = review.criticsPick != null && review.criticsPick!! > 0
        val showTagRating = review.rating != null && review.rating!!.isNotEmpty()

        tags_container.visibility = if (showTagCriticsPick or showTagRating) View.VISIBLE else View.GONE
        movie_review_tag_critpick.visibility = if (showTagCriticsPick) View.VISIBLE else View.GONE
        movie_review_tag_rating.visibility = if (showTagRating) View.VISIBLE else View.GONE
        if (showTagRating) movie_review_tag_rating.text = "  ${review.rating}  "
    }
}