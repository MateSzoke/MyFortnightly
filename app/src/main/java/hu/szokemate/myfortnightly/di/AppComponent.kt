package hu.szokemate.myfortnightly.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import hu.szokemate.myfortnightly.FortnightlyApplication
import hu.szokemate.myfortnightly.di.modules.ApiModule
import hu.szokemate.myfortnightly.di.modules.NetworkModule
import hu.szokemate.myfortnightly.di.modules.ViewModelModule

@Component(
    modules = [
        ApiModule::class,
        NetworkModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(application: FortnightlyApplication)
}