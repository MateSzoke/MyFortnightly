package hu.szokemate.myfortnightly.data.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSource(
    val id: String?,
    val name: String?
)