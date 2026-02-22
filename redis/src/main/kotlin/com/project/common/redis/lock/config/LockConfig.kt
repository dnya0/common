package com.project.common.redis.lock.config

import com.project.common.redis.lock.strategy.LettuceLockStrategy
import com.project.common.redis.lock.strategy.LockStrategy
import com.project.common.redis.lock.strategy.NoOpLockStrategy
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.core.RedisTemplate

@AutoConfiguration
@EnableConfigurationProperties(LockProperties::class)
@ConditionalOnBean(name = ["defaultStringRedisTemplate"])
class LockConfig {

    @Bean
    @ConditionalOnMissingBean(LockStrategy::class)
    fun lockService(
        @Qualifier("defaultStringRedisTemplate")
        redisTemplate: RedisTemplate<String, String>,
        lockProperties: LockProperties
    ): LockStrategy {
        return if (lockProperties.enabled) {
            LettuceLockStrategy(redisTemplate, lockProperties)
        } else {
            NoOpLockStrategy()
        }
    }
}
