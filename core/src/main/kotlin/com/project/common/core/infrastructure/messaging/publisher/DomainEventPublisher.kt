package com.project.common.core.infrastructure.messaging.publisher

import com.project.common.core.domain.event.DomainEvent
import org.springframework.context.ApplicationEventPublisher

class DomainEventPublisher(
    private val publisher: ApplicationEventPublisher
) {

    fun <T : DomainEvent> publish(event: T) {
        publisher.publishEvent(event)
    }

    fun <T : DomainEvent> publishAll(events: List<T>) {
        events.forEach { publish(it) }
    }

}
