package com.project.common.core.presentation.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer

@AutoConfiguration
@EnableConfigurationProperties(PageableProperties::class)
class PageableConfig(
    private val props: PageableProperties
) {

    @Bean
    @ConditionalOnMissingBean
    fun pageableCustomizer(): PageableHandlerMethodArgumentResolverCustomizer =
        PageableHandlerMethodArgumentResolverCustomizer { resolver ->
            resolver.setOneIndexedParameters(props.oneIndexed)
            resolver.setMaxPageSize(props.maxPageSize)
        }
}
