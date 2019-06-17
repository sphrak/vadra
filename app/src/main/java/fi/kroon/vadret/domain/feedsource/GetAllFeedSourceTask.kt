package fi.kroon.vadret.domain.feedsource

import fi.kroon.vadret.data.exception.Failure
import fi.kroon.vadret.data.feedsource.local.FeedSourceDao
import fi.kroon.vadret.data.feedsource.model.FeedSourceEntity
import fi.kroon.vadret.data.functional.Either
import fi.kroon.vadret.util.extension.asRight
import io.reactivex.Single
import javax.inject.Inject

class GetAllFeedSourceTask @Inject constructor(
    private val dao: FeedSourceDao
) {
    operator fun invoke(): Single<Either<Failure, List<FeedSourceEntity>>> =
        dao.getAll()
            .map { feedSourceList: List<FeedSourceEntity> ->
                feedSourceList.asRight()
            }
}