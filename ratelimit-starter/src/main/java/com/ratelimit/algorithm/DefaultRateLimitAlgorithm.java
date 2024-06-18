package com.ratelimit.algorithm;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DefaultRateLimitAlgorithm implements RateLimitAlgorithm{

    private static final Logger log = LoggerFactory.getLogger(DefaultRateLimitAlgorithm.class);
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    @Override
    public boolean isAllowed(String key, int period, TimeUnit unit, int maxCount) {
        long currentTimeMillis = System.currentTimeMillis();
        long expireMillis = unit.toMillis(period);
        String redisKey = key + ":" + (currentTimeMillis / expireMillis);

        // Increment the counter and set expiry if it's a new key
        Long count = redisTemplate.opsForValue().increment(redisKey, 1);

        if (count != null && count == 1) {
            redisTemplate.expire(redisKey, expireMillis, TimeUnit.MILLISECONDS);
        }

        return count != null && count <= maxCount;
    }

}
