package com.example.studyplannerapp.network

import com.example.studyplannerapp.model.QuoteResponse
import retrofit2.http.GET

interface QuoteApiService {

    @GET("random")
    suspend fun getRandomQuote(): List<QuoteResponse>
}
