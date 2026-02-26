package com.project.common.core.support.pagination.offset

import com.project.common.core.presentation.config.PageableProperties
import com.project.common.core.presentation.response.OffsetPageInfo
import com.project.common.core.presentation.response.toOffsetPageInfo
import org.springframework.data.domain.Page

class OffsetPageInfoSupport(
    private val props: PageableProperties
) {
    fun toPageInfo(page: Page<*>): OffsetPageInfo =
        page.toOffsetPageInfo(props.oneIndexed)
}
