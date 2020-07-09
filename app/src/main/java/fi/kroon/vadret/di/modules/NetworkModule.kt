package fi.kroon.vadret.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import fi.kroon.vadret.BuildConfig
import fi.kroon.vadret.di.scope.CoreApplicationScope
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module(
    includes = [
        ContextModule::class
    ]
)
object NetworkModule {

    private const val DEFAULT_CONNECTION_TIMEOUT = 10000L
    private const val READ_CONNECTION_TIMEOUT = 10000L
    private const val WRITE_CONNECTION_TIMEOUT = 10000L
    private const val DEFAULT_CACHE_SIZE: Long = 33_554_432L
    private const val CACHE_DIRECTORY = "okhttp_cache"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    private annotation class InternalApi

    private val getLogLevel: HttpLoggingInterceptor.Level =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @CoreApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .apply { level = getLogLevel }

    @Provides
    @InternalApi
    @CoreApplicationScope
    fun provideCache(
        context: Context
    ): Cache = Cache(
        directory = File(
            context.applicationContext.cacheDir,
            CACHE_DIRECTORY
        ),
        maxSize = DEFAULT_CACHE_SIZE
    )

    @Provides
    @CoreApplicationScope
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @InternalApi
        cache: Cache
    ): OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(READ_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(WRITE_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
        .cache(cache)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}