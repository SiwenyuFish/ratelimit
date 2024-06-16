package com.ratelimit.aspect;


import com.ratelimit.annotation.RateLimit;
import com.ratelimit.annotation.RateLimits;
import com.ratelimit.exception.RateLimitException;
import com.ratelimit.service.RateLimitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Aspect
@Component
public class RateLimitAspect {

    @Autowired
    private RateLimitService rateLimitService;

    @Around("@annotation(rateLimits)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimits rateLimits) throws Throwable {
        for (RateLimit rateLimit : rateLimits.value()) {
            String key = rateLimit.key();
            Integer period = rateLimit.period() > 0 ? rateLimit.period() : null;
            TimeUnit unit = rateLimit.unit() != TimeUnit.SECONDS ? rateLimit.unit() : null;
            Integer maxCount = rateLimit.maxCount() > 0 ? rateLimit.maxCount() : null;

            boolean allowed = rateLimitService.isAllowed(key, period, unit, maxCount);

            if (!allowed) {
                throw new RateLimitException("请求过多，请稍后再试");
            }
        }

        return joinPoint.proceed();
    }

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = rateLimit.key();
        Integer period = rateLimit.period() > 0 ? rateLimit.period() : null;
        TimeUnit unit = rateLimit.unit() != TimeUnit.SECONDS ? rateLimit.unit() : null;
        Integer maxCount = rateLimit.maxCount() > 0 ? rateLimit.maxCount() : null;

        boolean allowed = rateLimitService.isAllowed(key, period, unit, maxCount);

        if (!allowed) {
            throw new RateLimitException("请求过多，请稍后再试");
        }

        return joinPoint.proceed();
    }
}