package com.project.common.core.infrastructure.annotation

import org.springframework.stereotype.Repository

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Repository
annotation class QueryRepository
