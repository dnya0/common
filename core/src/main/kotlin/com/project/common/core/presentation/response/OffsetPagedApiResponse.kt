package com.project.common.core.presentation.response

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * Offset-based Response
 */
data class OffsetPagedApiResponse<T>(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    override val code: String = HttpStatus.OK.name,
    override val message: String = "success",
    val data: List<T>,
    val offsetPageInfo: OffsetPageInfo
) : ApiResponse<T>

data class OffsetPageInfo(
    val currentPage: Int,
    val size: Int,
    val totalPages: Int,
    val totalElements: Long
)

fun Page<*>.toOffsetPageInfo(oneIndexed: Boolean = true): OffsetPageInfo = OffsetPageInfo(
    currentPage = number + if (oneIndexed) 1 else 0,
    size = size,
    totalPages = totalPages,
    totalElements = totalElements
)
