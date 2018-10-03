package com.example.ratha.data.entity

import com.google.gson.annotations.SerializedName

data class Repo (
        val id: Long,
        val name: String,
        val description: String,
        @SerializedName("watchers_count")val watchers: Int,
        @SerializedName("stargazers_count")val forks : Int,
        val owner : User)