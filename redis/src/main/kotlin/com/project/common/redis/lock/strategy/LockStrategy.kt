package com.project.common.redis.lock.strategy

@FunctionalInterface
interface LockStrategy {

    fun <T> executeWithLock(lockKey: String, block: () -> T): T

}
