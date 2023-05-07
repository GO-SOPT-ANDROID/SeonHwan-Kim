package org.android.go.sopt.presentation.main.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding

class PagerAdapter(_itemList: List<Int> = listOf()) :
    RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {
    private var itemList: List<Int> = _itemList

    class PagerViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(src: Int) {
            binding.ivPager.setImageResource(src)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun setItemList(itemList: List<Int>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}

//class PagerDiffCallBack : DiffUtil.ItemCallback<List<Int>>() {
//    override fun areItemsTheSame(oldItem: List<Int>, newItem: List<Int>): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: List<Int>, newItem: List<Int>): Boolean {
//        return oldItem == newItem
//    }
//}