package com.project.common.utils.support

import java.util.UUID

object UUIDUtil {

    fun createId(domain: String): String = domain.plus("_").plus(getRandomUUID())

    fun getRandomUUID(): String =
        UUID.randomUUID().toString().replace("-", "")

}
