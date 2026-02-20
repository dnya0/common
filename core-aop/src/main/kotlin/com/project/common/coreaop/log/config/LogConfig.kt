package com.project.common.coreaop.log.config

import com.project.common.coreaop.log.aspect.LogAspect
import com.project.common.coreaop.log.filter.LoggingFilter
import com.project.common.coreaop.log.filter.MDCLoggingFilter
import com.project.common.coreaop.log.strategy.ExternalApiLogExecutionStrategy
import com.project.common.coreaop.log.strategy.LogExecutionStrategy
import com.project.common.coreaop.log.support.DefaultLogSanitizer
import com.project.common.coreaop.log.support.LogSanitizer
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy

@AutoConfiguration
@EnableAspectJAutoProxy
class LogConfig {

    @Bean
    @ConditionalOnMissingBean
    fun loggingFilter(): LoggingFilter = MDCLoggingFilter()

    @Bean
    @ConditionalOnMissingBean
    fun logAspect(strategy: LogExecutionStrategy): LogAspect = LogAspect(strategy)

    @Bean
    @ConditionalOnMissingBean
    fun logExecutionStrategy(sanitizer: LogSanitizer): LogExecutionStrategy =
        ExternalApiLogExecutionStrategy(sanitizer)

    @Bean
    @ConditionalOnMissingBean
    fun logSanitizer(): LogSanitizer = DefaultLogSanitizer()
}
