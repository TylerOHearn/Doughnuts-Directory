package com.sample.app

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.sample.BuildConfig
import com.sample.R
import com.sample.data.entities.RelatedTopic

class MainActivity : AppCompatActivity() {
    private lateinit var slidingPanel: SlidingPaneLayout
    private val viewModel:SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        slidingPanel = findViewById(R.id.sliding_pane_layout)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                val fullCast: ArrayList<RelatedTopic> = intent?.getBundleExtra(SearchManager.APP_DATA)?.getParcelableArrayList(("listCast")) ?: ArrayList<RelatedTopic>()
                val results = getSearchResults(query, fullCast)
                results?.let {
                    viewModel.setCurrentCast(it)
                }
            }
        } else {
            viewModel.onGetCharacters(BuildConfig.URL)
        }
    }

    fun getSearchResults(query: String, fullCast: ArrayList<RelatedTopic>): ArrayList<RelatedTopic>? {
        //filter stuff here
        var filterList: ArrayList<RelatedTopic> = fullCast
        filterList = ArrayList(filterList.filter {
            it.text.contains(query, true)
        })
        return filterList
    }

    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            putParcelableArrayList("listCast", viewModel.getCast())
        }
        startSearch(null, false, appData, false)
        return true
    }

    public fun openPanel() {
        slidingPanel.open()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.toolbar_search){
            onSearchRequested()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        if (!slidingPanel.isSlideable) {
            super.onBackPressed()
        }
        else if (slidingPanel.isOpen) {
            slidingPanel.close()
        }
        else
            super.onBackPressed()
    }
}