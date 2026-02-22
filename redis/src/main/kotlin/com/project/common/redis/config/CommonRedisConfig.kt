package com.project.common.redis.config

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@AutoConfiguration
class CommonRedisConfig {

    @Bean
    @ConditionalOnMissingBean(LettuceConnectionFactory::class)
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean("defaultStringRedisTemplate")
    @ConditionalOnMissingBean(name = ["defaultStringRedisTemplate"])
    fun defaultStringRedisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, String> = RedisTemplate<String, String>().apply {
        connectionFactory = redisConnectionFactory
        keySerializer = StringRedisSerializer()
        valueSerializer = StringRedisSerializer()
        afterPropertiesSet()
    }

    @Bean("anyValueRedisTemplate")
    @ConditionalOnMissingBean(name = ["anyValueRedisTemplate"])
    fun anyValueRedisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, Any> = RedisTemplate<String, Any>().apply {
        connectionFactory = redisConnectionFactory

        keySerializer = StringRedisSerializer()
        hashKeySerializer = StringRedisSerializer()

        val serializer = GenericJackson2JsonRedisSerializer()
        valueSerializer = serializer
        hashValueSerializer = serializer

        afterPropertiesSet()
    }
}
