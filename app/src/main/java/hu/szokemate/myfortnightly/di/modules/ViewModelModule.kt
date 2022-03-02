package hu.szokemate.myfortnightly.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import hu.szokemate.myfortnightly.ui.articles.ArticlesViewModel
import kotlin.reflect.KClass

@InstallIn(SingletonComponent::class)
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel
}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)