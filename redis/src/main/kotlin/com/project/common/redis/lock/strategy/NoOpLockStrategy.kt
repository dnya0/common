package com.project.common.redis.lock.strategy

class NoOpLockStrategy : LockStrategy {

    override fun <T> executeWithLock(lockKey: String, block: () -> T): T = block()

}
