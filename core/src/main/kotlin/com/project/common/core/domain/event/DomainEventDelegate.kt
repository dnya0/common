package com.project.common.core.domain.event

import com.project.common.core.domain.model.AggregateRoot
import kotlin.reflect.KClass

open class DomainEventDelegate : AggregateRoot {

    private val _events = mutableSetOf<DomainEvent>()

    override fun registerEvent(event: DomainEvent) {
        _events += event
    }

    override fun domainEvents(): List<DomainEvent> = _events.toList()

    override fun pullDomainEvents(): List<DomainEvent> = _events.toList().also { _events.clear() }

    override fun <T : DomainEvent> pullEventsOfType(clazz: KClass<T>): List<T> {
        val filtered = _events.filter { clazz.isInstance(it) }.map { it as T }
        _events.removeAll(filtered)
        return filtered
    }

}
