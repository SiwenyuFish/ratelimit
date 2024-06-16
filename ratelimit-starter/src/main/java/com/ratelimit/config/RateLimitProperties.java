package com.ratelimit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Component
@ConfigurationProperties(prefix = "ratelimit")
@Data
public class RateLimitProperties {
    private int defaultPeriod = 60;
    private TimeUnit defaultUnit = TimeUnit.SECONDS;
    private int defaultMaxCount = 100;
    private String algorithm = "default";
}
