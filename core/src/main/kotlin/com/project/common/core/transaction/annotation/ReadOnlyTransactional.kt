package com.project.common.core.transaction.annotation

import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
annotation class ReadOnlyTransactional()
