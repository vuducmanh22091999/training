package com.example.smalldemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smalldemo.R
import com.example.smalldemo.data.ImageModel
import kotlinx.android.synthetic.main.item_image.view.*

class ShowImageAdapter(var listImageModel : List<ImageModel>) : RecyclerView.Adapter<ShowImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listImageModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataViewHolder(listImageModel[position])
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindDataViewHolder(imageModel: ImageModel) {
            Glide.with(itemView.context)
                .load(imageModel.urlImage)
                .into(itemView.itemImage)

        }
    }
}