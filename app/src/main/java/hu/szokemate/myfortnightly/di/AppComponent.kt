package hu.szokemate.myfortnightly.di

import dagger.Component
import hu.szokemate.myfortnightly.di.modules.ApiModule
import hu.szokemate.myfortnightly.di.modules.NetworkModule

@Component(
    modules = [
        ApiModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent