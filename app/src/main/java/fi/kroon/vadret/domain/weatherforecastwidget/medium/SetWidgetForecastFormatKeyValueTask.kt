package fi.kroon.vadret.domain.weatherforecastwidget.medium

import fi.kroon.vadret.data.failure.Failure
import fi.kroon.vadret.data.weatherforecastwidget.local.WeatherForecastWidgetLocalKeyValueDataSource
import fi.kroon.vadret.util.FORECAST_FORMAT_WIDGET_KEY
import io.github.sphrak.either.Either
import io.reactivex.Single
import javax.inject.Inject

class SetWidgetForecastFormatKeyValueTask @Inject constructor(
    private val local: WeatherForecastWidgetLocalKeyValueDataSource
) {
    operator fun invoke(appWidgetId: Int, value: Int): Single<Either<Failure, Unit>> =
        local
            .putInt(FORECAST_FORMAT_WIDGET_KEY, appWidgetId, value)
}