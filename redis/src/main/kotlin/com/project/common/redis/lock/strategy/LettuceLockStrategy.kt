package com.project.common.redis.lock.strategy

import com.project.common.redis.lock.config.LockProperties
import com.project.common.redis.lock.exception.LockAcquisitionException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import java.util.concurrent.TimeUnit

class LettuceLockStrategy(
    private val redisTemplate: RedisTemplate<String, String>,
    private val lockProperties: LockProperties
) : LockStrategy {

    override fun <T> executeWithLock(
        lockKey: String,
        block: () -> T
    ): T {
        val value = "${Thread.currentThread().name}-${System.currentTimeMillis()}"
        val retryInterval = lockProperties.retryIntervalMillis

        repeat(lockProperties.maxRetries) {
            val acquired = redisTemplate.opsForValue()
                .setIfAbsent(lockKey, value, lockProperties.timeoutMillis, TimeUnit.MILLISECONDS)

            if (acquired == true) {
                try {
                    return block()
                } finally {
                    redisTemplate.execute(unlockScript, listOf(lockKey), value)
                }
            }

            Thread.sleep(retryInterval)
        }

        throw LockAcquisitionException(lockKey, lockProperties.timeoutMillis)
    }


    private val unlockScript = DefaultRedisScript<Long>(
        "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end",
        Long::class.java
    )

}
