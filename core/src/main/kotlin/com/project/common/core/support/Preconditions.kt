package com.project.common.core.support

import com.project.common.core.domain.exception.DomainException

/**
 * ### How to use
 * Throws the given exception if `condition` is true.
 *
 * ```
 * throwIf(condition) {
 *     IllegalStateException("오류 발생")
 * }
 * ```
 */
inline fun throwIf(boolean: Boolean, exceptionSupplier: () -> DomainException) {
    if (boolean) throw exceptionSupplier()
}

/**
 * ### How to use
 * Throws the given exception if `condition` is true.
 *
 * ```
 * throwIf(isTrue, { IllegalStateException() }) {
 *     return@throwIf this
 * }
 * ```
 */
inline fun <T> throwIf(boolean: Boolean, exceptionSupplier: () -> DomainException, block: () -> T): T {
    throwIf(boolean, exceptionSupplier)
    return block()
}

/**
 * ### How to use
 * Throws the given exception if `condition` is false.
 *
 * ```
 * throwIf(condition) {
 *     IllegalStateException("오류 발생")
 * }
 * ```
 */
inline fun throwIfNot(boolean: Boolean, exceptionSupplier: () -> DomainException) {
    throwIf(!boolean, exceptionSupplier)
}

/**
 * ### How to use
 *
 * ```
 * throwIfElse(isDuplicate, { DuplicateNameException(name) }) {
 *     println("저장 로직 실행")
 * }
 * ```
 */
inline fun throwIfElse(boolean: Boolean, exceptionSupplier: () -> DomainException, block: () -> Unit) {
    throwIf(boolean, exceptionSupplier)
    block()
}
