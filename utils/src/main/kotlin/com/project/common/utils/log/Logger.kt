package com.project.common.utils.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
fun <T : Any> T.loggerDelegate(): LoggerDelegate<T> {
    val actualClass = (if (this::class.isCompanion) {
        this::class.java.enclosingClass ?: this::class.java
    } else {
        this::class.java
    }) as Class<T>

    return LoggerDelegate(actualClass)
}

fun logger(): Logger = LoggerFactory.getLogger(object {}.javaClass.enclosingClass)

class LoggerDelegate<in T : Any>(private val clazz: Class<T>) {
    private val logger by lazy { LoggerFactory.getLogger(clazz) }

    operator fun getValue(thisRef: T, property: KProperty<*>): Logger = logger
}

class FileLoggerDelegate(private val clazz: Class<*>) {
    private val logger by lazy { LoggerFactory.getLogger(clazz) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Logger = logger
}


