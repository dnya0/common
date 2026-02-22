package com.project.common.redis.cache.config

import com.project.common.redis.cache.CacheSupport
import org.springframework.beans.factory.annotation.Qualifier
import com.project.common.redis.config.CommonRedisConfig
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.core.RedisTemplate

@AutoConfiguration(after = [CommonRedisConfig::class])
@ConditionalOnBean(name = ["anyValueRedisTemplate"])
class CacheConfig {

    @Bean
    @ConditionalOnMissingBean(CacheSupport::class)
    fun cacheHelper(
        @Qualifier("anyValueRedisTemplate")
        redisTemplate: RedisTemplate<String, Any>
    ): CacheSupport = CacheSupport(redisTemplate)

}
