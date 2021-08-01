package com.mobileexercise.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mobileexercise.R
import com.mobileexercise.model.AccountDetails

class CustomAdapter (val accountList: ArrayList<AccountDetails>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val accountDetails = accountList[position]
        holder.tvName.text = accountDetails.name
        holder.tvPrice.text = accountDetails.balance
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return accountList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: AppCompatTextView = itemView.findViewById(R.id.tvName)
        var tvPrice: AppCompatTextView = itemView.findViewById(R.id.tvPrice)
    }
}