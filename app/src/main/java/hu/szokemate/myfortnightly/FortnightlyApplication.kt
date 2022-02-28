package hu.szokemate.myfortnightly

import android.app.Application
import hu.szokemate.myfortnightly.di.AppComponent
import hu.szokemate.myfortnightly.di.DaggerAppComponent

class FortnightlyApplication : Application() {

    lateinit var injector: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupInjector()
    }

    private fun setupInjector() {
        injector = DaggerAppComponent.factory().create(applicationContext)
        injector.inject(this)
    }
}