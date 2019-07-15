package com.example.callingapp.view

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.callingapp.util.Constants
import com.example.callingapp.R
import com.example.callingapp.util.Util
import com.example.callingapp.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var adapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        mainActivityViewModel.init(this)

        mainActivityViewModel.getUserList()?.observe(this, Observer {
            adapter?.notifyDataSetChanged()
        })

        initGridView()

        fabAddNumber.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initGridView() {
        val gridView = findViewById<GridView>(R.id.gridview)

        adapter = UserListAdapter(
            this,
            R.layout.list_item,
            mainActivityViewModel.getUserLists()
            //mainActivityViewModel.getUserList()?.value
        )
        gridView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter?.itemList = mainActivityViewModel.getUserLists()
        adapter?.notifyDataSetChanged()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            Constants.PERMISSION_CALL -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    adapter?.callSelectedNumber()
                } else {
                    Util.getInstance().showToast(this, "Please grant Camera permission from Settings to take pictures.")
                }
            }
        }
    }
}
