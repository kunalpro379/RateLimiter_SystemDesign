package com.rate-limiter.core;

public interface RateLimiter {
    RateLimitResult tryConsume(String identifier);
    int getCapacity();
    double getRefillRatePerSecond();
}
