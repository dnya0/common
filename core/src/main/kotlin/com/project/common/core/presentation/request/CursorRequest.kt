package com.project.common.core.presentation.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern

data class CursorRequest(
    @field:Pattern(
        regexp = "^[A-Za-z0-9_-]+={0,2}$",
        message = "Invalid cursor format"
    )
    val cursor: String?,

    @field:Min(1)
    @field:Max(100)
    val size: Int = 20
)
