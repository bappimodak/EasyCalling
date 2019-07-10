package com.example.callingapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.callingapp.model.UserDetails


internal class GridViewAdapter internal constructor(
    context: Context,
    private val resource: Int,
    private val itemList: List<UserDetails>?
) : ArrayAdapter<GridViewAdapter.ItemHolder>(context, resource) {

    override fun getCount(): Int {
        return if (this.itemList != null) this.itemList.size else 0
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

        holder.icon!!.setImageDrawable(itemList?.get(position)?.drawable)

        holder.icon?.setOnClickListener(View.OnClickListener {
            try {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + itemList?.get(position)?.number.toString())
                startActivity(context, callIntent, null)
            } catch (activityException: ActivityNotFoundException) {
                Log.e("Calling a Phone Number", "Call failed", activityException)
            }

        })

        return convertView
    }

    internal class ItemHolder {
        var icon: ImageView? = null
    }

}