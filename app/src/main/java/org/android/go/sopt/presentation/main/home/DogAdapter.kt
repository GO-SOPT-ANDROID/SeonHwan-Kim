package org.android.go.sopt.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.local.Dog
import org.android.go.sopt.databinding.ItemDogBinding

class DogAdapter(context: Context) : ListAdapter<Dog, DogAdapter.DogViewHolder>(DogDiffCallback()) {
    private val inflater by lazy { LayoutInflater.from(context) }

    class DogViewHolder(private val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(dog: Dog) {
            binding.ivDogImage.setImageDrawable(binding.root.context.getDrawable(dog.image))
            binding.tvDogName.text = dog.name
            binding.tvDogSize.text = dog.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogBinding.inflate(inflater, parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class DogDiffCallback : DiffUtil.ItemCallback<Dog>() {
    override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
        return oldItem == newItem
    }
}