package com.project.jpa.infrastructure.persistence.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@AutoConfiguration
@ConditionalOnClass(EntityManagerFactory::class)
@ConditionalOnBean(EntityManagerFactory::class)
@EnableJpaAuditing
class CommonJpaConfig
