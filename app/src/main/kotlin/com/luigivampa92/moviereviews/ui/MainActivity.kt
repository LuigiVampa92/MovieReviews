package com.luigivampa92.moviereviews.ui

import android.os.Bundle
import android.support.v7.widget.SearchView
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.mvp.base.NestedFragmentActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : NestedFragmentActivity<MainFragment>(MainFragment()), SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        search_view.setOnQueryTextListener(this)
    }

    private fun initToolbar() {
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrBlank()) nestedFragment.searchReviewsWithQuery(query!!)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
