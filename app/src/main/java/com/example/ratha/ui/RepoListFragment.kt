package com.example.ratha.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ratha.ghviewmodel.R
import com.example.ratha.ui.adapter.RepoAdapter
import com.example.ratha.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.repo_fragment.*

class RepoListFragment : Fragment() {

    private lateinit var viewmodel: RepoViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.repo_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        recycler_view?.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        val adapter=RepoAdapter(viewmodel,this)
        recycler_view?.layoutManager=LinearLayoutManager(activity)
        recycler_view?.adapter=adapter
        observeViewModel();
    }

    fun  observeViewModel(){
        viewmodel.repos.observe(this, Observer{repos->
            Log.e("observe","$repos")
            if(repos!=null){
                recycler_view?.visibility=View.VISIBLE
            }
        })
        viewmodel.loading.observe(this, Observer{isError->
            Log.e("observe","$isError")
           if(isError!!) {
               progressbar?.visibility=View.VISIBLE
           }else
               progressbar?.visibility=View.GONE
        })

        viewmodel.repoLoadError.observe(this, Observer {textError->
            Log.e("observe","$textError")
            if(textError!!){
                error?.visibility=View.VISIBLE
                error?.text=getString(R.string.text_error)
            }else{
                error?.visibility=View.GONE
            }

        })
    }
}

