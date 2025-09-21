package com.translator.data.remote

import com.translator.data.models.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET("/api/public/v1/words/search")
    suspend fun searchForWord(
        @Query("search") word: String,
        @Query("pageSize") limit: Int = 1
    ): List<ResponseDTO>
}