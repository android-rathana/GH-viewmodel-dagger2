package com.example.ratha.app.di.component

import com.example.ratha.app.di.module.NetworkModule
import com.example.ratha.app.di.module.ViewModelModule
import com.example.ratha.ui.detail.DetailFragment
import com.example.ratha.ui.repo.RepoListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(NetworkModule::class, ViewModelModule::class)
)
interface ApplicationComponent {
    fun inject(repoListFragment: RepoListFragment)
    fun inject(fragment: DetailFragment)


}
