package org.android.go.sopt.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.R
import org.android.go.sopt.data.Dog
import org.android.go.sopt.databinding.ItemDogBinding

class DogAdapter(context: Context) : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    private var dogList: List<Dog> = emptyList()

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

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.onBind(dogList[position])
    }

    fun setDogList(dogList: List<Dog>) {
        this.dogList = dogList.toList()
        notifyDataSetChanged()
    }
}

