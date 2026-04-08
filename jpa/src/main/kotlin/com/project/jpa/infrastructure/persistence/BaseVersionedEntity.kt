package com.project.jpa.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version

@MappedSuperclass
abstract class BaseVersionedEntity protected constructor(

    @Version
    @Column(name = "version", updatable = false, nullable = false)
    var version: Long? = null
) : BaseEntity()
