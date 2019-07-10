package com.example.callingapp.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridView
import com.example.callingapp.GridViewAdapter
import com.example.callingapp.R
import com.example.callingapp.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var adapter: GridViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.init(this)
        initGridView()
        mainActivityViewModel.getUserList()?.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })

    }

    private fun initGridView() {
        val gridView = findViewById<GridView>(R.id.gridview)

        adapter = GridViewAdapter(this, R.layout.list_item, mainActivityViewModel.getUserList()?.value)
        gridView.adapter = adapter
    }
}
