package com.rate-limiter.algorithm;

import com.rate-limiter.core.RateLimiter;
import com.rate-limiter.core.Storage;
import com.rate-limiter.core.model.BucketState;
import com.rate-limiter.core.model.RateLimitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 1. Identify user → generate bucket key 
 * 2. Fetch tokens + last refill timestamp from Redis
 * 3. Compute elapsed time
 * 4. Compute refill token amount
 * 5. Update current token count
 * 6. Check if enough tokens exist
 * 7. If allowed → consume tokens
 * 8. Save updated token count + timestamp
 * 9. Return allowed/denied
*/

public class TokenBucket implements RateLimiter{
    private static final Logger logger=LoggerFactory.getLogger(TokenBucket.class);
    private final Storage storage;
    private final int capacity;
    private final double refillRatePerSound;
    private final int tokensRequired;
    public TokenBucket(
        Storage storage,int capacity, double refillRatePerSecond, int tokensRequired
    ){
    if (storage == null) {
        throw new IllegalArgumentException("Storage cannot be null");
    }
    if (capacity <= 0) {
        throw new IllegalArgumentException("Capacity must be positive");
    }
    if (refillRatePerSecond <= 0) {
        throw new IllegalArgumentException("Refill rate must be positive");
    }
    if (tokensRequired <= 0) {
        throw new IllegalArgumentException("Tokens required must be positive");
    }
    this.storage = storage;
    this.capacity = capacity;
    this.refillRatePerSecond = refillRatePerSecond;
    this.tokensRequired = tokensRequired;
    logger.info("TokenBucket initialized with capacity={}, refillRatePerSecond={}, tokensRequired={}", capacity, refillRatePerSecond, tokensRequired);
    
}
}