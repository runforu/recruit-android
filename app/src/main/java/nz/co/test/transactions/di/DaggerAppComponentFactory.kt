package nz.co.test.transactions.di

import android.app.Activity
import androidx.core.app.AppComponentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerAppComponentFactory : AppComponentFactory() {
    companion object {
        val mComponent = DaggerAppComponent.create()
    }
    init {
        mComponent.inject(this)
    }

    @Inject
    lateinit var mMap: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>
}