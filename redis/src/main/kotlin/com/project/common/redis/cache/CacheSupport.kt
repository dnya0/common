package com.project.common.redis.cache

import org.springframework.data.redis.core.RedisTemplate
import java.time.Duration

/**
 * 주어진 [function]을 실행하고, 그 결과를 Redis에 캐싱합니다.
 *
 * 캐시에 값이 존재하면 캐시된 값을 반환하고,
 * 값이 없으면 함수를 실행한 뒤 결과를 Redis에 저장합니다.
 *
 * @param ttl 캐시 유지 시간 (초 단위)
 * @param key Redis 캐시 키를 구성하는 요소들
 * @param function 캐시 미스 시 실행할 로직
 *
 * @return 캐시된 값 또는 새로 계산된 값
 *
 * ### Key 생성 규칙:
 *
 * ```
 * key1:key2:key3
 * ```
 *
 * ### 동작 방식:
 * - key 파라미터들을 ":"로 연결하여 Redis Key 생성
 * - 캐시 조회 후 존재하면 즉시 반환
 * - 캐시 미스 시 function 실행
 * - 결과를 TTL과 함께 Redis에 저장
 *
 * ### 주의사항:
 * - TTL은 초(seconds) 단위로 해석됩니다
 * - Key 충돌 방지는 호출 측에서 보장해야 합니다
 *
 * ### 예시:
 *
 * ```
 * // 예시 1 - named argument 사용
 *  fun findById(userId: Long): UserRead = cache(
 *      ttl = 60000,
 *      key = arrayOf("UserRead", "userId:${userId}")
 *  ) { // 캐시 AOP
 *    val user = userRepository.findById(userId).orElseThrow { throw Exception("User Not Found :${userId}") }
 *    return@cache UserRead(user)
 *  }
 *
 * // 예시 2 - named argument 미사용
 * fun findById(userId: Long): UserRead = cache(60000, "UserRead", "userId:${userId}") { // 캐시 AOP
 *    val user = userRepository.findById(userId).orElseThrow { throw Exception("User Not Found :${userId}") }
 *    return@cache UserRead(user)
 *  }
 * ```
 */
fun <T : Any> cache(ttl: Long, vararg key: String, function: () -> T): T {
    val redisTemplate = CacheSupport.getRedisTemplate()
    val cacheKey = key.joinToString(":")

    val cached = redisTemplate.opsForValue().get(cacheKey)
    if (cached != null) {
        @Suppress("UNCHECKED_CAST")
        return cached as T
    }

    val result = function()

    redisTemplate.opsForValue().set(cacheKey, result, Duration.ofSeconds(ttl))

    return result
}

/**
 * 캐시 유틸리티에서 사용할 RedisTemplate을 보관하는 클래스
 *
 * Spring 설정을 통해 RedisTemplate을 주입받아
 * static context에 저장한 뒤 공통 캐시 로직에서 사용한다.
 *
 * 사용 예:
 * ```
 * @Bean
 * fun cacheHelper(redisTemplate: RedisTemplate<String, Any>) =
 *     CacheHelper(redisTemplate)
 * ```
 *
 * 주의:
 * - 애플리케이션 시작 시 반드시 초기화되어야 한다
 * - 초기화 이전에 cache 함수를 호출하면 예외가 발생할 수 있다
 */
class CacheSupport(
    private val _redisTemplate: RedisTemplate<String, Any>,
) {

    init {
        redisTemplate = _redisTemplate
    }

    companion object {

        /**
         * 캐시 유틸리티에서 공통으로 사용하는 RedisTemplate 인스턴스
         *
         * CacheSupport 생성자를 통해 한 번만 초기화된다.
         */
        private var redisTemplate: RedisTemplate<String, Any>? = null

        /**
         * RedisTemplate 인스턴스를 반환합니다.
         *
         * @throws IllegalStateException RedisTemplate이 초기화되지 않은 경우
         */
        internal fun getRedisTemplate(): RedisTemplate<String, Any> {
            return redisTemplate
                ?: throw IllegalStateException(
                    "CacheSupport is not initialized. " +
                        "Please ensure CacheSupport bean is created in your Spring configuration."
                )
        }
    }
}
