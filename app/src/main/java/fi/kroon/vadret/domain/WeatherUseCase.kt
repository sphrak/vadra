package fi.kroon.vadret.domain

import fi.kroon.vadret.data.Request
import fi.kroon.vadret.data.exception.Either
import fi.kroon.vadret.data.exception.Failure
import fi.kroon.vadret.data.weather.WeatherRepository
import fi.kroon.vadret.data.weather.model.Weather
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    fun get(request: Request, forceCacheInvalidation: Boolean = false): Single<Either<Failure, Weather>> {
        return Single.just(request)
            .flatMap { _ ->
                weatherRepository.get(request, forceCacheInvalidation)
            }.doOnEvent { t1, t2 ->
                Timber.d("T1: $t1, T2: $t2")
            }.doOnError {
                Timber.d("$it")
            }
    }
}