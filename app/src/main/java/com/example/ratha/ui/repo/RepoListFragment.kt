package com.example.ratha.ui.repo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ratha.app.GHApplication
import com.example.ratha.app.di.module.ViewModelFactory
import com.example.ratha.data.entity.Repo
import com.example.ratha.ghviewmodel.R
import com.example.ratha.ui.adapter.RepoAdapter
import com.example.ratha.ui.callback.ItemClickCallback
import com.example.ratha.ui.detail.DetailFragment
import com.example.ratha.viewmodel.DetailViewModel
import com.example.ratha.viewmodel.RepoViewModel
import kotlinx.android.synthetic.main.repo_fragment.*
import javax.inject.Inject

class RepoListFragment : Fragment() ,ItemClickCallback<Repo>{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewmodel: RepoViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        GHApplication.getComponent(context!!).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.repo_fragment,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(this,viewModelFactory).get(RepoViewModel::class.java)

        recycler_view?.addItemDecoration(DividerItemDecoration(activity,DividerItemDecoration.VERTICAL))
        val adapter=RepoAdapter(viewmodel,this,this)
        recycler_view?.layoutManager=LinearLayoutManager(activity)
        recycler_view?.adapter=adapter
        observeViewModel()
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

    override fun onObjectClickListener(t: Repo) {
       val detailViewModel=ViewModelProviders.of(activity!!,viewModelFactory).get(DetailViewModel::class.java)
        //Log.e("repo","$t")
        detailViewModel.setRepo(t)
        activity?.supportFragmentManager?.beginTransaction()!!
                .replace(R.id.container, DetailFragment())
                .addToBackStack(null)
                .commit()
    }
}

