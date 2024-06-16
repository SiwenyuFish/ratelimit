package com.ratelimit.controller;


import com.ratelimit.annotation.RateLimit;
import com.ratelimit.annotation.RateLimits;
import com.ratelimit.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {

    @RateLimits({
            @RateLimit(key = "test1", period = 5, unit = TimeUnit.SECONDS, maxCount = 3),
            @RateLimit(key = "test2", period = 30, unit = TimeUnit.SECONDS, maxCount = 10)
    })
    @GetMapping("/rateLimit")
    public Result rateLimit() {
        return Result.success("RateLimit test1");
    }


    @RateLimit(key = "test3", period = 10, unit = TimeUnit.SECONDS, maxCount = 5)
    @GetMapping("/rateLimitTest")
    public Result rateLimitTest() {
        return Result.success("RateLimit test2");
    }
}
