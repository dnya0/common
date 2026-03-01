package com.project.common.core.application.port.out

import com.project.common.core.domain.event.DomainEvent
import com.project.common.core.domain.model.AggregateRoot

interface DomainEventPublisher {

    fun <T : DomainEvent> publish(event: T)

    fun <T : DomainEvent> publishAll(events: List<T>) {
        events.forEach { publish(it) }
    }

    fun publishAndClear(aggregateRoot: AggregateRoot) {
        publishAll(aggregateRoot.pullDomainEvents())
    }

    fun publishAndClearAll(aggregateRoots: Collection<AggregateRoot>) {
        aggregateRoots.forEach { publishAndClear(it) }
    }
}
