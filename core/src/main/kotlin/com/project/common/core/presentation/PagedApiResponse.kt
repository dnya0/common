package com.project.common.core.presentation

import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class PagedApiResponse<T>(
    override val timestamp: LocalDateTime = LocalDateTime.now(),
    override val code: String = HttpStatus.OK.name,
    override val message: String = "success",
    val data: List<T>,
    val pageInfo: PageInfo
) : ApiResponse<T>

data class PageInfo(
    val currentPage: Int,
    val size: Int,
    val totalPages: Int,
    val totalElements: Long
)

fun Page<*>.toPageInfo(): PageInfo = PageInfo(
    currentPage = number,
    size = size,
    totalPages = totalPages,
    totalElements = totalElements
)
