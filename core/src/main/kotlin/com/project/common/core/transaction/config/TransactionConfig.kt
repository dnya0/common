package com.project.common.core.transaction.config

import com.project.common.core.transaction.helper.Tx
import com.project.common.core.transaction.runner.TransactionRunner
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.context.annotation.Bean
import org.springframework.transaction.TransactionManager

@AutoConfiguration
@AutoConfigureAfter(TransactionManager::class)
class TransactionConfig {

    @Bean("txInitBean")
    fun txInitialize(txRunner: TransactionRunner): InitializingBean =
        InitializingBean { Tx.initialize(txRunner) }

    @Bean
    fun transactionalRunner(): TransactionRunner = TransactionRunner()
}
