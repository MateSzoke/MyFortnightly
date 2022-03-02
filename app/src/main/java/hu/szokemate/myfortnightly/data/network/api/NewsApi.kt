package hu.szokemate.myfortnightly.data.network.api

import hu.szokemate.myfortnightly.data.network.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("v2/top-headlines/")
    suspend fun getTopNews(): Response<ArticlesResponse>
}