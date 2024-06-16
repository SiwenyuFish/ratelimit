package com.ratelimit.service;


import com.ratelimit.algorithm.RateLimitAlgorithm;
import com.ratelimit.config.RateLimitProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RateLimitService {

    @Autowired
    private RateLimitAlgorithm rateLimitAlgorithm;
    @Autowired
    private RateLimitProperties rateLimitProperties;


    public boolean isAllowed(String key, Integer period, TimeUnit unit, Integer maxCount) {
        int effectivePeriod = period != null ? period : rateLimitProperties.getDefaultPeriod();
        TimeUnit effectiveUnit = unit != null ? unit : rateLimitProperties.getDefaultUnit();
        int effectiveMaxCount = maxCount != null ? maxCount : rateLimitProperties.getDefaultMaxCount();

        return rateLimitAlgorithm.isAllowed(key, effectivePeriod, effectiveUnit, effectiveMaxCount);
    }
}
