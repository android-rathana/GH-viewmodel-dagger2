package com.example.ratha.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.ratha.data.entity.Repo
import com.example.ratha.data.service.RepoService
import com.example.ratha.data.service.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoViewModel: ViewModel {
    val  repos =MutableLiveData<MutableList<Repo>>()
    val loading= MutableLiveData<Boolean>()
    val repoLoadError=MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    var repoService: RepoService

    init {
        //repoService=ServiceGenerator.createService(RepoService::class.java)

    }

    @Inject constructor(repoService: RepoService){
        this.repoService=repoService
        loadRepos()
    }

    fun loadRepos(){
        loading.value=true
        compositeDisposable.add(repoService.getRepositories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<MutableList<Repo>>(){
                    override fun onComplete() {
                        loading.value=false
                        repoLoadError.value=false
                    }

                    override fun onError(e: Throwable) {
                        loading.value=false
                        repoLoadError.value=true
                    }

                    override fun onNext(t: MutableList<Repo>) {
                        repos.value=t
                        loading.value=false
                        repoLoadError.value=false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}