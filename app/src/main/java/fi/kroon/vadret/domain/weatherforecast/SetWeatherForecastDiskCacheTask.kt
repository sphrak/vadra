package fi.kroon.vadret.domain.weatherforecast

import fi.kroon.vadret.data.failure.Failure
import fi.kroon.vadret.data.weatherforecast.cache.WeatherForecastCacheDataSource
import fi.kroon.vadret.data.weatherforecast.model.Weather
import io.github.sphrak.either.Either
import io.reactivex.Single
import javax.inject.Inject
import timber.log.Timber

class SetWeatherForecastDiskCacheTask @Inject constructor(
    private val repo: WeatherForecastCacheDataSource
) {
    operator fun invoke(cacheKey: String, weather: Weather): Single<Either<Failure, Weather>> =
        repo
            .updateDiskCache(weather, cacheKey)
            .doOnError {
                Timber.e("$it")
            }
}