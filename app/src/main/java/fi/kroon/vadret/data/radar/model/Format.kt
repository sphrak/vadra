package fi.kroon.vadret.data.radar.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Format(

    @Json(name = "key")
    val key: String,

    @Json(name = "updated")
    val updated: String,

    @Json(name = "link")
    val link: String
) : Serializable