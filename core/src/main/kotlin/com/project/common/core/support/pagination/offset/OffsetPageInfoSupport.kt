package com.project.common.core.support.pagination.offset

import com.project.common.core.presentation.response.OffsetPageInfo
import com.project.common.core.presentation.config.PageableProperties
import com.project.common.core.presentation.response.toPageInfo
import org.springframework.data.domain.Page

class OffsetPageInfoSupport(
    private val props: PageableProperties
) {
    fun toPageInfo(page: Page<*>): OffsetPageInfo =
        page.toPageInfo(props.oneIndexed)
}
