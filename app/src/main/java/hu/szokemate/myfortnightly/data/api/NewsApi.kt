package hu.szokemate.myfortnightly.data.api

import hu.szokemate.myfortnightly.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines/")
    fun getTopNews(): Response<ArticlesResponse>

}