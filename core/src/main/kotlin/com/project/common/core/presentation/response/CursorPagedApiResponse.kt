package com.project.common.core.presentation.response

import org.springframework.data.domain.Slice
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Cursor-based Response
 */
data class CursorApiResponse<T>(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    override val code: String = HttpStatus.OK.name,
    override val message: String = "success",
    val data: List<T>,
    val pageInfo: CursorPageInfo
) : ApiResponse<T>

data class CursorPageInfo(
    val size: Int,
    val hasNext: Boolean,
    val nextCursor: String? // 없으면 null
)

fun <T : Any> Slice<T>.toCursorPageInfo(
    nextCursorExtractor: (T) -> String?
): CursorPageInfo {
    val nextCursor = if (hasNext()) {
        content.lastOrNull()?.let(nextCursorExtractor)
    } else null

    return CursorPageInfo(
        size = size,
        hasNext = hasNext(),
        nextCursor = nextCursor
    )
}

fun <T : Any> Slice<T>.toCursorResponse(
    nextCursorExtractor: (T) -> String?
): CursorApiResponse<T> = CursorApiResponse(
    data = content,
    pageInfo = toCursorPageInfo(nextCursorExtractor)
)
