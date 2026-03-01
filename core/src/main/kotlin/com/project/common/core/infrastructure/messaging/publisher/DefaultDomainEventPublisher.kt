package com.project.common.core.infrastructure.messaging.publisher

import com.project.common.core.application.port.out.DomainEventPublisher
import com.project.common.core.domain.event.DomainEvent
import org.springframework.context.ApplicationEventPublisher

class DefaultDomainEventPublisher(
    private val publisher: ApplicationEventPublisher
) : DomainEventPublisher {

    override fun <T : DomainEvent> publish(event: T) {
        publisher.publishEvent(event)
    }

}
