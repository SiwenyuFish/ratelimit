package com.ratelimit.algorithm;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * 和默认限流算法唯一区别是count计数从2开始
 * 仅用于测试通过配置文件修改算法
 */
@Component
@AllArgsConstructor
public class AnotherRateLimitAlgorithm implements RateLimitAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(DefaultRateLimitAlgorithm.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean isAllowed(String key, int period, TimeUnit unit, int maxCount) {
        String redisKey = "ratelimit:" + key;

        Integer count = null;
        try {
            count = (Integer) redisTemplate.opsForValue().get(redisKey);
        } catch (Exception e) {
            log.info("....");
        }

        if (count == null) {
            redisTemplate.opsForValue().set(redisKey, 2, period, unit);
            return true;
        }

        if (count < maxCount) {
            redisTemplate.opsForValue().increment(redisKey);
            return true;
        }

        return false;
    }
}
