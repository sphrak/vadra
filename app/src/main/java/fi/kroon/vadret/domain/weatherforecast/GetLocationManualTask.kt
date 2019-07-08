package fi.kroon.vadret.domain.weatherforecast

import fi.kroon.vadret.data.exception.Failure
import fi.kroon.vadret.data.functional.Either
import fi.kroon.vadret.data.functional.flatMap
import fi.kroon.vadret.data.weatherforecast.local.WeatherForecastLocalKeyValueDataSource
import fi.kroon.vadret.data.weatherforecast.model.WeatherOut
import fi.kroon.vadret.util.LATITUDE_KEY
import fi.kroon.vadret.util.LOCALITY_KEY
import fi.kroon.vadret.util.LONGITUDE_KEY
import fi.kroon.vadret.util.extension.asRight
import fi.kroon.vadret.util.extension.toCoordinate
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles
import javax.inject.Inject

class GetLocationManualTask @Inject constructor(
    private val keyValueStore: WeatherForecastLocalKeyValueDataSource
) {

    operator fun invoke(): Single<Either<Failure, WeatherOut>> =
        Singles.zip(
            keyValueStore.getString(LOCALITY_KEY),
            keyValueStore.getString(LATITUDE_KEY),
            keyValueStore.getString(LONGITUDE_KEY)
        ).map { triple: Triple<Either<Failure, String>, Either<Failure, String>, Either<Failure, String>> ->
            val (localityEither: Either<Failure, String>, latitudeEither: Either<Failure, String>, longitudeEither: Either<Failure, String>) = triple
            localityEither.flatMap { locality ->
                latitudeEither.flatMap { latitude ->
                    longitudeEither.flatMap { longitude ->
                        WeatherOut(
                            localityName = locality,
                            latitude = latitude.toCoordinate(),
                            longitude = longitude.toCoordinate()
                        ).asRight()
                    }
                }
            }
        }
}