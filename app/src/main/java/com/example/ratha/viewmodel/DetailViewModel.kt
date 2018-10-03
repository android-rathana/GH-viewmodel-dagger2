package com.example.ratha.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.ratha.data.entity.Repo

class DetailViewModel : ViewModel() {
    val repoLive= MutableLiveData<Repo>()

    fun setRepo(repo: Repo){
        repoLive.value=repo
        Log.e("repo","$repo")
    }
}