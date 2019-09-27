package fi.kroon.vadret.data.location.exception

import fi.kroon.vadret.data.failure.Failure

class LocationFailure {
    object LocationProviderDisabled : Failure.FeatureFailure()
    object LocationNotAvailable : Failure.FeatureFailure()
    object NoLocationPermissions : Failure.FeatureFailure()
}