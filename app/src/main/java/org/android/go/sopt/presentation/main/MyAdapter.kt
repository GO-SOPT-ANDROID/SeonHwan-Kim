package org.android.go.sopt.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubRepoBinding

class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    // 더미 데이터 생성
    private val itemList: List<Repo> =
        listOf(Repo("repo1", "author1"), Repo("repo2", "author2"), Repo("repo3", "author3"))

    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemGithubRepoBinding = ItemGithubRepoBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 각각의 아이템을 연결
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }
}

class MyViewHolder(private val binding: ItemGithubRepoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Repo) {
        binding.tvItemGithubRepo.text = item.name
        binding.tvItemGithubAuthor.text = item.author
    }
}

data class Repo(
    val name: String,
    val author: String
)