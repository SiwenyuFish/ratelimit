package com.ratelimit.algorithm;

import java.util.concurrent.TimeUnit;

public interface RateLimitAlgorithm {
    boolean isAllowed(String key, int period, TimeUnit unit, int maxCount);
}

