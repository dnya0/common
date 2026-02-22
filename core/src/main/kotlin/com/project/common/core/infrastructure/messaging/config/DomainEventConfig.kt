package com.project.common.core.infrastructure.messaging.config

import com.project.common.core.infrastructure.messaging.publisher.DomainEventPublisher
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean

@AutoConfiguration
class DomainEventConfig {

    @Bean
    @ConditionalOnMissingBean(DomainEventPublisher::class)
    fun domainEventPublisher(applicationEventPublisher: ApplicationEventPublisher): DomainEventPublisher =
        DomainEventPublisher(applicationEventPublisher)

}
