package fi.kroon.vadret.data.common.exception

import fi.kroon.vadret.data.exception.Failure

class LocalFileReaderFailure {
    class IOFailure : Failure.FeatureFailure()
}