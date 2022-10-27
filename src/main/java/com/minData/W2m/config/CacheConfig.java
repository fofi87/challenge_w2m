package com.minData.W2m.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheConfig() {
        return (builder) -> builder
                .withCacheConfiguration("superHeros",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(java.time.Duration.ofMinutes(5)));
    }
}
