package org.android.go.sopt.presentation.main.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.android.go.sopt.data.remote.model.ResponseHomeUserDto
import org.android.go.sopt.data.remote.service.HomeUserService
import org.android.go.sopt.databinding.ItemPagerBinding

class PagerAdapter() :
    ListAdapter<ResponseHomeUserDto.UserData, PagerAdapter.PagerViewHolder>(PagerDiffCallBack()) {

    class PagerViewHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: ResponseHomeUserDto.UserData) {
            with(binding){
                Glide.with(root)
                    .load(userData.avatar)
                    .into(ivAvatar)
                tvPagerEmail.text = "email : ${userData.email}"
                tvPagerFirstName.text = "First Name : ${userData.first_name}"
                tvPagerLastName.text = "Last Name : ${userData.last_name}"
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
}

class PagerDiffCallBack : DiffUtil.ItemCallback<ResponseHomeUserDto.UserData>() {
    override fun areItemsTheSame(
        oldItem: ResponseHomeUserDto.UserData,
        newItem: ResponseHomeUserDto.UserData
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ResponseHomeUserDto.UserData,
        newItem: ResponseHomeUserDto.UserData
    ): Boolean {
        return oldItem == newItem
    }
}