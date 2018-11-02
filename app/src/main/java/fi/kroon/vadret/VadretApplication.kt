package fi.kroon.vadret

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import fi.kroon.vadret.di.component.DaggerVadretApplicationComponent
import fi.kroon.vadret.di.component.VadretApplicationComponent
import fi.kroon.vadret.di.modules.ApplicationModule
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import com.crashlytics.android.core.CrashlyticsCore

class VadretApplication : Application() {

    companion object {
        operator fun get(context: Context): VadretApplication {
            return context.applicationContext as VadretApplication
        }
    }

    val cmp: VadretApplicationComponent by lazy {
        DaggerVadretApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        cmp.inject(this)
        plantTimber()
        initThreeTenAbp()
        initLeakCanary()
        initCrashlytics()
    }

    private fun initCrashlytics() {
        val crashlyticsKit = Crashlytics.Builder()
            .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
            .build()
        Fabric.with(this, crashlyticsKit)
    }

    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Timber.d("LeakCanary is in analyzer process")
            return
        }
        LeakCanary.install(this)
    }

    private fun initThreeTenAbp() {
        AndroidThreeTen.init(this)
    }
}