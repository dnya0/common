package com.project.common.core.infrastructure.config

import com.project.common.core.presentation.exception.GlobalExceptionHandler
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync

@AutoConfiguration
@EnableAsync
class CoreConfig {

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler::class)
    fun globalExceptionHandler(): GlobalExceptionHandler = GlobalExceptionHandler()
}
