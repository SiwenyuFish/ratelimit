package com.ratelimit.config;


import com.ratelimit.algorithm.AnotherRateLimitAlgorithm;
import com.ratelimit.algorithm.DefaultRateLimitAlgorithm;
import com.ratelimit.algorithm.RateLimitAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

@Configuration
@AllArgsConstructor
public class RateLimitAutoConfiguration {


    private final RateLimitProperties rateLimitProperties;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Bean
    @Primary
    public RateLimitAlgorithm rateLimitAlgorithm() {
        String algorithm = rateLimitProperties.getAlgorithm();
        if (StringUtils.hasText(algorithm)) {
            switch (algorithm) {
                case "default":
                    return new DefaultRateLimitAlgorithm(redisTemplate);
                case "another":
                    return new AnotherRateLimitAlgorithm(redisTemplate);
                default:
                    throw new IllegalArgumentException("Unsupported rate limit algorithm: " + algorithm);
            }
        }
        return new DefaultRateLimitAlgorithm(redisTemplate);
    }

}
