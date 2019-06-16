package fi.kroon.vadret.di.modules

import android.content.Context
import androidx.collection.LruCache
import dagger.Module
import dagger.Provides
import fi.kroon.vadret.data.weatherforecast.model.Weather
import fi.kroon.vadret.di.scope.CoreApplicationScope
import fi.kroon.vadret.data.alert.model.Alert
import fi.kroon.vadret.data.radar.model.Radar
import fi.kroon.vadret.utils.DISK_CACHE_SIZE
import fi.kroon.vadret.utils.MEMORY_CACHE_SIZE
import okhttp3.internal.cache.DiskLruCache
import okhttp3.internal.io.FileSystem

@Module
object CacheModule {

    @Provides
    @JvmStatic
    @CoreApplicationScope
    fun provideDiskLruCache(context: Context): DiskLruCache =
        DiskLruCache.create(
            FileSystem.SYSTEM,
            context.cacheDir,
            1,
            1,
            DISK_CACHE_SIZE
        )

    @Provides
    @JvmStatic
    @CoreApplicationScope
    fun provideWeatherLruCache(): LruCache<String, Weather> = LruCache(MEMORY_CACHE_SIZE)

    @Provides
    @JvmStatic
    @CoreApplicationScope
    fun provideAlertLruCache(): LruCache<Long, Alert> = LruCache(MEMORY_CACHE_SIZE)

    @Provides
    @JvmStatic
    @CoreApplicationScope
    fun provideRadarLruCache(): LruCache<Long, Radar> = LruCache(MEMORY_CACHE_SIZE)
}