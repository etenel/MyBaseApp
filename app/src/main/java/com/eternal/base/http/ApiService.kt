package com.eternal.base.http

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/lock/houseinfo")
    suspend fun getLockDatas(@Field("user_id") userId: String, @Field("role_type") int: Int): Any

}