package com.luigivampa92.moviereviews.mvp.base

import android.os.Bundle
import com.luigivampa92.moviereviews.R

abstract class NestedFragmentActivity<F : BaseFragment> constructor (protected var nestedFragment: F) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        placeFragment(savedInstanceState, nestedFragment.javaClass.newInstance())
    }

    private fun placeFragment(savedInstanceState: Bundle?, fragment: F) {
        if (savedInstanceState == null) {
            nestedFragment = fragment
            nestedFragment.retainInstance = true
            supportFragmentManager.beginTransaction()
                    .replace(R.id.nested_fragment_container, nestedFragment)
                    .commit();
        }
        else {
            nestedFragment = supportFragmentManager.findFragmentById(R.id.nested_fragment_container) as F
        }
    }
}