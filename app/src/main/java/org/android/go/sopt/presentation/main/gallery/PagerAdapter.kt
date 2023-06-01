package org.android.go.sopt.presentation.main.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.databinding.ItemPagerBinding
import org.android.go.sopt.domain.entity.HomeUser
import org.android.go.sopt.util.DiffCallback

class PagerAdapter() : ListAdapter<HomeUser, PagerAdapter.PagerViewHolder>(diffUtil) {

    class PagerViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: HomeUser) {
            with(binding) {
                data = userData
                ivAvatar.load(userData.avatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffUtil = DiffCallback<HomeUser>(
            onContentsTheSame = { old, new -> old.email == new.email },
            onItemsTheSame = { old, new -> old == new },
        )
    }
}
