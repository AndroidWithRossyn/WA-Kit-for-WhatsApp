package com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.data.model

import androidx.annotation.Keep

@Keep
data class DeletedMessage(
    val id: String ="0",
    val message: String = "",
    val isDeleted: Boolean = false,
    val time: Long,
)
