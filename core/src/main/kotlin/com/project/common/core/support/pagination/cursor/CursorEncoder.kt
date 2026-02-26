package com.project.common.core.support.pagination.cursor

interface CursorEncoder<T> {
    fun encode(item: T): Cursor
    fun decode(cursor: Cursor): CursorCondition
}
