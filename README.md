# Bonus
- 允许接口有多个频控策略
- 实现核心配置类，允许用户通过配置文件自定义默认频控时间范围、频控时间单位、单位频控时间范围内最大访问次数
- 可通过配置文件的参数指定替换限流算法
# 项目结构
    ├─ratelimit-starter
    │  │  pom.xml
    │  │
    │  ├─src
    │  │  ├─main
    │  │  │  ├─java
    │  │  │  │  └─com
    │  │  │  │      └─ratelimit
    │  │  │  │          ├─algorithm
    │  │  │  │          │      AnotherRateLimitAlgorithm.java
    │  │  │  │          │      DefaultRateLimitAlgorithm.java //默认实现算法
    │  │  │  │          │      RateLimitAlgorithm.java
    │  │  │  │          │
    │  │  │  │          ├─annotation
    │  │  │  │          │      RateLimit.java
    │  │  │  │          │      RateLimits.java
    │  │  │  │          │
    │  │  │  │          ├─aspect
    │  │  │  │          │      RateLimitAspect.java //切面类
    │  │  │  │          │
    │  │  │  │          ├─config
    │  │  │  │          │      RateLimitAutoConfiguration.java
    │  │  │  │          │      RateLimitProperties.java //核心配置类
    │  │  │  │          │      RedisConfig.java
    │  │  │  │          │
    │  │  │  │          ├─exception
    │  │  │  │          │      GlobalExceptionHandler.java
    │  │  │  │          │      RateLimitException.java
    │  │  │  │          │
    │  │  │  │          ├─pojo
    │  │  │  │          │      Result.java
    │  │  │  │          │
    │  │  │  │          └─service
    │  │  │  │                  RateLimitService.java
    │  │  │  │
    │  │  │  └─resources
    │  │  │      │  application.yml
    │  │  │      │
    │  │  │      └─META-INF
    │  │  │              spring.factories
    │  │  │
    │  │  └─test
    │  │      └─java
    │  
    └─ratelimit-test //测试模块
        │  pom.xml
        │
        ├─src
        │  ├─main
        │  │  ├─java
        │  │  │  └─com
        │  │  │      └─ratelimit
        │  │  │          │  RatelimitTestApplication.java
        │  │  │          │
        │  │  │          └─controller
        │  │  │                  TestController.java
        │  │  │
        │  │  └─resources
        |  |     └── application.yml //自定义频控属性
        │  └─test
        │     └─java
    
