package com.project.common.core.infrastructure.messaging.listener

import com.project.common.core.domain.event.DomainEvent

interface DomainEventListener<T : DomainEvent> {
    fun onDomainEvent(event: T)
}
