package com.project.common.core.support.log

import com.project.common.utils.log.logger
import java.time.Duration
import java.time.LocalDateTime

private val log = logger()

/**
 * Trailing Lambdas를 사용한 로그 함수
 */
fun <T> loggingStopWatch(function: () -> T): T {
    val startAt = LocalDateTime.now()
    log.info("Start At : $startAt")

    val result = function.invoke()

    val endAt = LocalDateTime.now()

    log.info("End At : $endAt")
    log.info("Logic Duration : ${Duration.between(startAt, endAt).toMillis()}ms")

    return result
}

/**
 * 로깅 컨텍스트(Map)를 생성한 뒤, 호출자에게 전달하여
 * 비즈니스 로직 수행 중 필요한 값을 자유롭게 기록할 수 있도록 하는 함수.
 *
 * - 함수 실행 전 `startAt` 값을 자동으로 저장
 * - 호출자는 전달받은 MutableMap에 원하는 로그 데이터를 추가 가능
 * - 로직 실행 완료 후 Map 전체를 로그로 출력
 *
 * 사용 예:
 * ```
 * loggingWithArgument { logData ->
 *     logData["userId"] = user.id
 *     logData["action"] = "create-order"
 *
 *     service.doSomething()
 * }
 * ```
 *
 * @param function 로깅 데이터를 기록할 수 있는 컨텍스트(Map)를 인자로 받는 함수
 * @return function 실행 결과
 */
fun <T> loggingWithArgument(function: (MutableMap<String, Any>) -> T): T {
    val logData = mutableMapOf<String, Any>()
    logData["startAt"] = LocalDateTime.now()

    val result = function.invoke(logData) // logData 변수를 넘겨줌

    log.info(logData.entries.toString())
    return result
}
