package com.project.common.core.infrastructure.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@AutoConfiguration
@EnableAsync
@EnableJpaAuditing
class CoreConfig {
}
