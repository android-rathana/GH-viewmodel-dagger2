package com.example.ratha.data.service

import com.example.ratha.data.entity.Repo
import io.reactivex.Observable
import retrofit2.http.GET

interface RepoService {

    @GET("/orgs/Google/repos")
    fun getRepositories() : Observable<MutableList<Repo>>

}