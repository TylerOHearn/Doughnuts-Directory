package com.sample.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.R
import com.sample.data.entities.RelatedTopic
import com.sample.formatting.StringUtil

class RecyclerViewAdapter (private var cast: ArrayList<RelatedTopic>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        cast[position].let {
            holder.name.text = StringUtil.formatTitle(it.text)
            //Picasso.get().load(R.drawable.sprinkles_medium_pink).centerCrop().fit().into(holder.background)

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(cast[position])
                }
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(character: RelatedTopic)
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val name: TextView = itemview.findViewById(R.id.character_name)
        val background: ImageView = itemview.findViewById(R.id.card_view_image_view)
    }
}