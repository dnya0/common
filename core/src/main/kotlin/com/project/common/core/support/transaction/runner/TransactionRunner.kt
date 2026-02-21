package com.project.common.core.support.transaction.runner

import com.project.common.core.support.transaction.annotation.ReadOnlyTransactional
import org.springframework.transaction.annotation.Transactional

open class TransactionRunner {

    @Transactional
    open fun <T> run(block: () -> T): T = block()

    @ReadOnlyTransactional
    open fun <T> runReadOnly(block: () -> T): T = block()
}
