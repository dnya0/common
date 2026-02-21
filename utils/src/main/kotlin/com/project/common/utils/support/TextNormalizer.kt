package com.project.common.utils.support

fun normalize(input: String): String =
    input.trim().replace(Regex("\\s+"), " ").lowercase()
