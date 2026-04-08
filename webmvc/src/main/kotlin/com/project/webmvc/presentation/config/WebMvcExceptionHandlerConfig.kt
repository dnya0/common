package com.project.webmvc.presentation.config

import com.project.webmvc.presentation.exception.GlobalExceptionHandler
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync

@AutoConfiguration
@EnableAsync
class WebMvcExceptionHandlerConfig {

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler::class)
    fun globalExceptionHandler(): GlobalExceptionHandler = GlobalExceptionHandler()

}
