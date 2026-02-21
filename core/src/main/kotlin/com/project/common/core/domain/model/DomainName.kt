package com.project.common.core.domain.model

import com.project.common.utils.support.UUIDUtil

interface DomainName {

    fun value(): String

    fun createId(): String = UUIDUtil.createId(this.value().lowercase())

}
