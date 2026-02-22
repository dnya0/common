# Common Library 🚀

![jitpack](https://img.shields.io/badge/jitpack-v0.1.4--k1.9-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-brightgreen)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.25-7F52FF)
![JVM](https://img.shields.io/badge/jvm-17-blue)

Spring Boot 애플리케이션 개발을 위한 공통 라이브러리입니다.
반복적인 보일러플레이트 코드를 줄이고, 일관된 아키텍처 패턴을 제공합니다.

## ✨ 주요 기능

### 🗄️ Core 모듈
- **BaseEntity 클래스**: JPA 엔티티용 공통 베이스 클래스 (ID, 생성일시, 수정일시)
- **도메인 이벤트 시스템**: AggregateRoot와 DomainEvent를 통한 이벤트 기반 아키텍처
- **Domain 추상화**: 도메인 모델의 기본 구조 제공

### 🔴 Redis 모듈  
- **캐시 유틸리티**: Redis 기반 캐싱 지원
- **분산 락**: Redis를 활용한 분산 환경 동기화
- **세션 관리**: Redis 기반 세션 스토리지

### 📊 AOP 모듈
- **로깅 AOP**: 메서드 실행 시간, 파라미터, 결과 자동 로깅
- **트랜잭션 관리**: 커스텀 트랜잭션 처리
- **예외 처리**: 전역 예외 핸들링

### 🛠️ Utils 모듈
- **API Response**: 표준화된 REST API 응답 형식
- **페이징 지원**: 페이지네이션을 위한 유틸리티
- **예외 클래스**: 공통 예외 처리 클래스들

## 📦 설치 방법

`build.gradle.kts`에 다음을 추가하세요:

```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // 또는 필요한 모듈만 선택적으로 사용
    implementation("com.github.dnya0.common:core:${version}")      // JPA 엔티티, 도메인 이벤트
    implementation("com.github.dnya0.common:redis:${version}")     // Redis 캐시, 분산 락
    implementation("com.github.dnya0.common:core-aop:${version}")  // AOP 로깅
    implementation("com.github.dnya0.common:utils:${version}")     // 유틸리티 함수
}
```

## 🎯 사용 예시

### BaseEntity 사용
```kotlin
@Entity
class User : BaseEntity() {
    var name: String = ""
    var email: String = ""
}
```

### 도메인 이벤트 사용
```kotlin
class Order : 
    BaseEntity(),
    AggregateRoot by DomainEventDelegate() {
    
    fun completeOrder() {
        // 비즈니스 로직
        registerEvent(OrderCompletedEvent(this.id!!))
    }
}
```

### AOP 로깅 사용
```kotlin
@Service
class UserService {
    
    @Loggable
    fun createUser(request: CreateUserRequest): User {
        // 메서드 실행 시간과 파라미터가 자동으로 로깅됩니다
        return userRepository.save(User(...))
    }
}
```

## 🏗️ 아키텍처

```
common/
├── core/           # 핵심 도메인 모델 및 이벤트
├── core-aop/       # AOP 기반 횡단 관심사
├── redis/          # Redis 연동 유틸리티  
├── utils/          # 공통 유틸리티
└── buildSrc/       # Gradle 빌드 로직
```

## 🛠️ 개발 환경

### 요구사항
- **JDK**: 17 이상
- **Kotlin**: 2.2.0
- **Spring Boot**: 3.x
- **Gradle**: 9.0.0

### 빌드 및 테스트
```bash
# 프로젝트 빌드
./gradlew build

# 테스트 실행
./gradlew check

# 로컬 Maven 저장소에 배포
./gradlew publishToMavenLocal

# 빌드 결과물 정리
./gradlew clean
```

## 📄 라이선스

MIT License

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 문의

문제나 제안사항이 있으시면 [Issues](https://github.com/dnya0/common/issues)를 통해 알려주세요.
