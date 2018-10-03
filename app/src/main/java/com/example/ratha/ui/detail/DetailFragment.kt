package com.example.ratha.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ratha.ghviewmodel.R
import com.example.ratha.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_fragment_layout.*

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?=
            LayoutInflater.from(activity).inflate(R.layout.detail_fragment_layout,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailViewModel=ViewModelProviders.of(activity!!).get(DetailViewModel::class.java)
        detailViewModel.repoLive.observe(this, Observer {repo->
            //Log.e("repo","$repo")
            repoName?.text=repo?.name
            repoDesc?.text=repo?.description
            forksCount?.text="${repo?.forks}"
            starCount?.text="${repo?.watchers}"
        })
    }
}