package fi.kroon.vadret.domain.weatherforecastwidget.shared

import fi.kroon.vadret.data.exception.Failure
import fi.kroon.vadret.data.weatherforecastwidget.local.WeatherForecastWidgetLocalKeyValueDataSource
import fi.kroon.vadret.util.INITIALISED_STATUS_WIDGET_KEY
import io.github.sphrak.either.Either
import io.reactivex.Single
import javax.inject.Inject

class GetWidgetInitialisedKeyValueTask @Inject constructor(
    private val local: WeatherForecastWidgetLocalKeyValueDataSource
) {
    operator fun invoke(appWidgetId: Int): Single<Either<Failure, Boolean>> =
        local.getBoolean(INITIALISED_STATUS_WIDGET_KEY, appWidgetId)
}