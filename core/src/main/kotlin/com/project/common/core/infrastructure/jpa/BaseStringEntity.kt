package com.project.common.core.infrastructure.jpa

import com.project.common.core.domain.model.Domain
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import java.time.Instant


@MappedSuperclass
abstract class BaseStringEntity protected constructor(

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false, length = 100)
    private var id: String? = null,

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    var createdAt: Instant? = null,

    @LastModifiedDate
    @Column(name = "updated_at", updatable = true)
    var updatedAt: Instant? = null
) : Domain<String>(id), Persistable<String> {

    override val value: String?
        get() = id

    @Transient
    private var _isNew = true

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

    override fun getId(): String = id ?: throw IllegalStateException("Entity not persisted yet")

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false

        val thisClass = Hibernate.getClass(this)
        val otherClass = Hibernate.getClass(other)

        if (thisClass != otherClass) return false

        other as BaseStringEntity

        if (id == null || other.id == null) return false

        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: System.identityHashCode(this)

    override fun toString(): String = "${this::class.simpleName}(id=$id)"

}

