package hu.szokemate.myfortnightly.di.modules

import dagger.Module
import dagger.Provides
import hu.szokemate.myfortnightly.data.api.NewsApi
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create()
}