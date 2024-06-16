package com.ratelimit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {
    String key() default ""; // 标识前缀key
    int period(); // 频控时间范围
    TimeUnit unit() default TimeUnit.SECONDS; // 频控时间单位
    int maxCount(); // 单位频控时间范围内最大访问次数
}