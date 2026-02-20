package com.project.common.coreaop.log.support

interface LogSanitizer {

    fun sanitize(value: Any?): String

}
