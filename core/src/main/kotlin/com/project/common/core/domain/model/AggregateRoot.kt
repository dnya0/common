package com.project.common.core.domain.model

import com.project.common.core.domain.event.DomainEvent
import kotlin.reflect.KClass

/**
 * 아래와 같이 위임받아 사용
 *
 * ```
 * class Order :
 *     BaseEntity(...),
 *     AggregateRoot by DomainEventDelegate()
 * ```
 */
interface AggregateRoot {

    /**
     * 도메인 이벤트 등록
     *
     * @param event 등록할 도메인 이벤트
     */
    fun registerEvent(event: DomainEvent)

    /**
     * 현재 AggregateRoot가 가지고 있는 모든 도메인 이벤트 조회
     *
     * @return 등록된 이벤트들의 리스트 (복사본)
     */
    fun domainEvents(): List<DomainEvent>

    /**
     * 현재 AggregateRoot가 가지고 있는 모든 도메인 이벤트 조회
     */
    fun pullDomainEvents(): List<DomainEvent>

    /**
     * 특정 타입(T)의 도메인 이벤트만 조회 후 내부 리스트에서 제거
     *
     * @param clazz 조회할 도메인 이벤트 타입
     * @return 해당 타입 이벤트들의 리스트
     */
    fun <T : DomainEvent> pullEventsOfType(clazz: KClass<T>): List<T>

}
