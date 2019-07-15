package com.example.callingapp.view

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.example.callingapp.util.Constants
import com.example.callingapp.R
import com.example.callingapp.util.Util
import com.example.callingapp.model.UserDetails


internal class UserListAdapter internal constructor(
    context: Context,
    private val resource: Int,
    var itemList: List<UserDetails>?
) : ArrayAdapter<UserListAdapter.ItemHolder>(context, resource) {

    private var selectedPosition: Int = 0
    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList!!.size else 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView

        val holder: ItemHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null)
            holder = ItemHolder()
            holder.icon = convertView.findViewById(R.id.icon)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ItemHolder
        }

        holder.icon!!.setImageBitmap(Util.getInstance().StringToBitMap(itemList?.get(position)?.userImage))

        holder.icon?.setOnClickListener {
            selectedPosition = position
            if (Util.getInstance().checkPermission(
                    context, arrayOf(Manifest.permission.CALL_PHONE),
                    Constants.PERMISSION_CALL)) {
                callSelectedNumber()
            }
        }

        return convertView
    }

    fun callSelectedNumber() {
        try {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + itemList?.get(selectedPosition)?.number.toString())
            startActivity(context, callIntent, null)
        } catch (activityException: ActivityNotFoundException) {
            Log.e("Calling a Phone Number", "Call failed", activityException)
        }
    }

    internal class ItemHolder {
        var icon: ImageView? = null
    }
}