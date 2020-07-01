package com.avatarfirst.avatagen.apis

import io.reactivex.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("users/amoskorir/followers")
    fun getUsers(): Single<List<GithubUserApiResponseItem>>
}