package com.example.ratha.ui.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ratha.data.entity.Repo
import com.example.ratha.ghviewmodel.R
import com.example.ratha.viewmodel.RepoViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repo_item_layout.*

class RepoAdapter(viewModel: RepoViewModel , lifecycleOwner: LifecycleOwner) :
        RecyclerView.Adapter<RepoAdapter.ViewHolder>() {
    val repos= mutableListOf<Repo>()
    init {
        viewModel.repos.observe(lifecycleOwner, Observer {data->
            repos.clear()
            if(data!=null){
                repos.addAll(data)
                notifyDataSetChanged()
            }
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
         ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.repo_item_layout,p0,false))

    override fun getItemCount(): Int = repos.size

    override fun getItemId(position: Int): Long = repos.get(position).id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo=repos[position]
        holder.apply {
            repoTitle?.text=repo.name
            repoDescription?.text=repo.description
            repoFork?.text="${repo.forks}"
            repoStar?.text="${repo.watchers}"
        }
    }

    inner class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView),LayoutContainer{

        val repoTitle=title
        val repoDescription =description
        val repoFork=text_fork
        val repoStar = text_star
    }
}