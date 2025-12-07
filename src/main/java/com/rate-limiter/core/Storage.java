package com.rate-limiter.core;

import com.rate-limiter.model.BucketState;
public interface Storage {
    BucketState getBucketState(String identifier);
    void updateBucketState(String identifier, BucketState state);
    void close();
    
}
