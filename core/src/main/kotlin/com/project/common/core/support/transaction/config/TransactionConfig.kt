package com.project.common.core.support.transaction.config

import com.project.common.core.support.transaction.helper.Tx
import com.project.common.core.support.transaction.runner.TransactionRunner
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.transaction.TransactionManager

@AutoConfiguration
@AutoConfigureAfter(TransactionManager::class)
class TransactionConfig {

    @Bean("txInitBean")
    @ConditionalOnMissingBean
    fun txInitialize(txRunner: TransactionRunner): InitializingBean =
        InitializingBean { Tx.initialize(txRunner) }

    @Bean
    @ConditionalOnMissingBean
    fun transactionalRunner(): TransactionRunner = TransactionRunner()
}
