package com.example.ratha.app.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory : ViewModelProvider.Factory {

    var viewModels: Map<Class<out  ViewModel> , Provider<ViewModel>>

    @Inject
    constructor(viewModels: Map<Class<out  ViewModel> , @JvmSuppressWildcards Provider<ViewModel>>){
        this.viewModels=viewModels
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            return (viewModels.get(modelClass)!!.get()) as T
        }catch (e: Exception){
            throw  RuntimeException("Error create View model class for : ${modelClass.simpleName}",e)
        }
    }
}