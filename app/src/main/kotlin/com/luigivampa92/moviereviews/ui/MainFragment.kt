package com.luigivampa92.moviereviews.ui

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.luigivampa92.moviereviews.R
import com.luigivampa92.moviereviews.domain.MainPresenter
import com.luigivampa92.moviereviews.domain.Review
import com.luigivampa92.moviereviews.mvp.MainView
import com.luigivampa92.moviereviews.mvp.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(), MainView {

    private lateinit var recyclerViewAdapter: MovieReviewRecycleViewAdapter
    private lateinit var recyclerViewLayoutManager: LinearLayoutManager
    private var notOnceRequested = true
    private var notOnceResponded = true
    private var processingFetch = false
    private var countPast = 0
    private var countVisible = 0
    private var countTotal = 0

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "MainPresenter")
    lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_main, container, false)

        if (notOnceResponded)
            Handler().post({ progress_bar_loading.visibility = View.VISIBLE })

        recyclerViewAdapter = MovieReviewRecycleViewAdapter(this@MainFragment.context)
        recyclerViewLayoutManager = LinearLayoutManager(view.context)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setAdapter(recyclerViewAdapter)
        recyclerView.setLayoutManager(recyclerViewLayoutManager)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    if (dy > 0) {
                        countVisible = recyclerViewLayoutManager.childCount
                        countTotal = recyclerViewLayoutManager.itemCount
                        countPast = recyclerViewLayoutManager.findFirstVisibleItemPosition()
                        if (!processingFetch) {
                            if (countVisible + countPast >= countTotal) {
                                processingFetch = true
                                progress_bar_fetching.setVisibility(View.VISIBLE)
                                presenter.getReviews(getCurrentQuery(), false)
                            }
                        }
                    }
                }
            }
        )

        val swipeRefreshLayout = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        swipeRefreshLayout.setColorSchemeResources(R.color.color_primary)
        swipeRefreshLayout.setOnRefreshListener { presenter.getReviews(getCurrentQuery(), true) }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (notOnceRequested) {
            notOnceRequested = false
            presenter.getReviews(getCurrentQuery(), true)
        }
    }

    @StateStrategyType(value = AddToEndSingleStrategy::class, tag = "cmd_display")
    override fun displayReviews(reviews: List<Review>) {
        recyclerViewAdapter.setNewItems(reviews)
    }

    @StateStrategyType(value = SkipStrategy::class, tag = "cmd_toast")
    override fun toastMessage(message: String) {
        super.toastMessage(message)
    }

    @StateStrategyType(value = AddToEndStrategy::class, tag = "cmd_complete")
    override fun completeLoading() {
        notOnceResponded = false
        progress_bar_loading.visibility = View.GONE
        swipe_refresh_layout.isRefreshing = false
        if (processingFetch && progress_bar_fetching.visibility == View.VISIBLE) {
            processingFetch = false
            progress_bar_fetching.visibility = View.GONE
        }
    }

    fun searchReviewsWithQuery(query: String) {
        presenter.getReviews(query, false)
    }

    private fun getCurrentQuery(): String {
        return activity.search_view.query.toString()
    }
}
