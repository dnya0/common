package com.project.common.core.presentation.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("common.pageable")
data class PageableProperties(
    var oneIndexed: Boolean = true,
    var maxPageSize: Int = 200
)
