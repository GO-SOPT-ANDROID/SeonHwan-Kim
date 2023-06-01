package org.android.go.sopt.presentation.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.databinding.ItemSearchBinding
import org.android.go.sopt.domain.entity.KakaoSearch
import org.android.go.sopt.util.DiffCallback

class SearchAdapter :
    ListAdapter<KakaoSearch, SearchAdapter.SearchViewHolder>(diffUtil) {

    class SearchViewHolder(private var binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(videoData: KakaoSearch) {
            with(binding) {
                ivSearchItemThumbnail.load(videoData.thumbnail)

                tvSearchItemTitle.text = videoData.title
                tvSearchItemAuthor.text = videoData.author
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<KakaoSearch>(
            onContentsTheSame = { old, new -> old.title == new.title },
            onItemsTheSame = { old, new -> old == new },
        )
    }
}
