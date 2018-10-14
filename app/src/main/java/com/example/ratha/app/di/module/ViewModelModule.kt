package com.example.ratha.app.di.module

import android.arch.lifecycle.ViewModel
import com.example.ratha.app.di.bind.ViewModelKey
import com.example.ratha.viewmodel.DetailViewModel
import com.example.ratha.viewmodel.RepoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel::class)
    abstract fun bindRepoViewModel(repoViewModel: RepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel


}