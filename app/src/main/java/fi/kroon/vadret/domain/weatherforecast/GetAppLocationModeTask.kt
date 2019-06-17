package fi.kroon.vadret.domain.weatherforecast

import fi.kroon.vadret.data.exception.Failure
import fi.kroon.vadret.data.functional.Either
import fi.kroon.vadret.data.weatherforecast.local.WeatherForecastLocalKeyValueDataSource
import fi.kroon.vadret.util.AUTOMATIC_LOCATION_MODE_KEY
import io.reactivex.Single
import javax.inject.Inject

class GetAppLocationModeTask @Inject constructor(
    private val weatherForecastLocalKeyValueDataSource: WeatherForecastLocalKeyValueDataSource
) {
    operator fun invoke(): Single<Either<Failure, Boolean>> =
        weatherForecastLocalKeyValueDataSource
            .getBoolean(AUTOMATIC_LOCATION_MODE_KEY)
}