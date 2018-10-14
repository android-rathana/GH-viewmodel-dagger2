package com.example.ratha.app.di.module

import com.example.ratha.data.service.RepoService
import com.example.ratha.data.service.ServiceGenerator
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL="https://api.github.com"
    }

    @Singleton
    @Provides
    fun provideRetrofit():
            Retrofit = Retrofit.Builder()
                    .baseUrl(NetworkModule.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()


    @Singleton
    @Provides
    fun provideRepoService(retrofit: Retrofit): RepoService =
            retrofit.create(RepoService::class.java)
}