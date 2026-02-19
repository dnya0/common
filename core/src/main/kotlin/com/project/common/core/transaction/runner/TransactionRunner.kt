package com.project.common.core.transaction.runner

import com.project.common.core.transaction.annotation.ReadOnlyTransactional
import org.springframework.transaction.annotation.Transactional

open class TransactionRunner {

    @Transactional
    open fun <T> run(block: () -> T): T = block()

    @ReadOnlyTransactional
    open fun <T> runReadOnly(block: () -> T): T = block()
}
