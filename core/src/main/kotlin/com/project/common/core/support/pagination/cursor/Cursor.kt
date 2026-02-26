package com.project.common.core.support.pagination.cursor

import java.time.Instant

@JvmInline
value class Cursor(val value: String)

data class CursorCondition(
    val createdAt: Instant?,
    val id: Long?
)
