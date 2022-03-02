package hu.szokemate.myfortnightly.di.modules

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.szokemate.myfortnightly.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.OffsetDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor { chain ->
                val original = chain.request()
                val url = original.url().newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.NEWS_API_KEY)
                    .addQueryParameter("language", BuildConfig.NEWS_LANGUAGE)
                    .build()
                val request = original.newBuilder().url(url).build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMoshi() = Moshi.Builder()
        .add(object {

            @FromJson
            fun fromJson(string: String): OffsetDateTime = OffsetDateTime.parse(string)

            @ToJson
            fun toJson(value: OffsetDateTime): String = value.toString()
        })
        .build()
}