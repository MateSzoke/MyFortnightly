package hu.szokemate.myfortnightly.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkSource(
    val id: String?,
    val name: String?
)