package com.project.common.core.support.pagination.cursor.impl

import com.project.common.core.support.pagination.cursor.Cursor
import com.project.common.core.support.pagination.cursor.CursorCondition
import com.project.common.core.support.pagination.cursor.CursorEncoder
import java.time.Instant
import java.util.Base64

/**
 * CursorEncoder 기본 구현
 */
class CreatedAtIdCursorEncoder<T>(
    private val createdAtExtractor: (T) -> Instant,
    private val idExtractor: (T) -> Long
) : CursorEncoder<T> {

    override fun encode(item: T): Cursor {
        val raw = "${createdAtExtractor(item)}|${idExtractor(item)}"
        val encoded = Base64.getUrlEncoder().encodeToString(raw.toByteArray())
        return Cursor(encoded)
    }

    override fun decode(cursor: Cursor): CursorCondition = runCatching {
        val decoded = String(Base64.getUrlDecoder().decode(cursor.value))
        val (createdAt, id) = decoded.split("|")

        CursorCondition(
            createdAt = Instant.parse(createdAt),
            id = id.toLong()
        )
    }.getOrElse { e ->
        throw IllegalArgumentException("Invalid cursor format", e)
    }

}
