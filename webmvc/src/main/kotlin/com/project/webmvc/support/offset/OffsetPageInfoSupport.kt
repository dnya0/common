package com.project.webmvc.support.offset

import com.project.common.core.presentation.response.OffsetPageInfo
import com.project.common.core.presentation.response.toOffsetPageInfo
import com.project.webmvc.presentation.config.PageableProperties
import org.springframework.data.domain.Page

class OffsetPageInfoSupport(
    private val props: PageableProperties
) {
    fun toPageInfo(page: Page<*>): OffsetPageInfo =
        page.toOffsetPageInfo(props.oneIndexed)
}
