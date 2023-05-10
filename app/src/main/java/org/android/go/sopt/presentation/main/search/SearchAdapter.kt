package org.android.go.sopt.presentation.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.remote.model.ResponseKakaoSearchDto
import org.android.go.sopt.databinding.ItemSearchBinding

class SearchAdapter(context: Context) :
    ListAdapter<ResponseKakaoSearchDto.Document, SearchAdapter.SearchViewHolder>(SearchDiffCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    class SearchViewHolder(private var binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(videoData: ResponseKakaoSearchDto.Document) {
            with(binding) {
                Glide.with(root)
                    .load(videoData.thumbnail)
                    .into(ivSearchItemThumbnail)

                tvSearchItemTitle.text = videoData.title
                tvSearchItemAuthor.text = videoData.author
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<ResponseKakaoSearchDto.Document>() {
    override fun areItemsTheSame(
        oldItem: ResponseKakaoSearchDto.Document,
        newItem: ResponseKakaoSearchDto.Document
    ): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: ResponseKakaoSearchDto.Document,
        newItem: ResponseKakaoSearchDto.Document
    ): Boolean {
        return oldItem == newItem
    }
}