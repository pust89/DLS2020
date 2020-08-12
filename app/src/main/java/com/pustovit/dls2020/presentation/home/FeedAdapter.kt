package com.pustovit.dls2020.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.dls2020.R
import com.pustovit.dls2020.databinding.FeedItemBinding
import com.pustovit.dls2020.domain.Feed

class ItemClickListener<T>(private val itemClickListener: (t: T) -> Unit) {

    fun onClick(t: T) {
        itemClickListener.invoke(t)
    }
}

class FeedAdapter(private val feedClickListener: ItemClickListener<Feed>) :
    ListAdapter<Feed, FeedAdapter.FeedItemVH>(
        MovieDiffUtilItemCallback
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemVH {
        return FeedItemVH.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: FeedItemVH, position: Int) {
        val feed = getItem(position)
        holder.bind(feed, feedClickListener)
    }

    class FeedItemVH(private val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            feed: Feed,
            feedClickListener: ItemClickListener<Feed>
        ) {
            binding.feed = feed
            binding.feedClickListener = feedClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FeedItemVH {
                val binding: FeedItemBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.feed_item,
                    parent,
                    false
                )
                return FeedItemVH(
                    binding
                )
            }
        }
    }

    companion object MovieDiffUtilItemCallback : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }
    }


}