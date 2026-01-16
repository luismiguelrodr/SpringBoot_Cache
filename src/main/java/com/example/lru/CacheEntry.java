package com.example.lru;

public class CacheEntry<V> {
    private final V value;
    private final Instant createdAt;    // Entry creation time
    private final long ttlMillis;       // TTL in milliseconds
    private Instant lastAccessed;       // Last access time

    public CacheEntry(V value, long ttlMillis) {
        this.value = value;
        this.ttlMillis = ttlMillis;
        this.createdAt = Instant.now();
        this.lastAccessed = Instant.now();
    }

    public V getValue() {
        this.lastAccessed = Instant.now();
        return value;
    }

    public boolean isExpired() {
        Instant now = Instant.now();
        return now.isAfter(createdAt.plusMillis(ttlMillis));
    }

    public Instant getLastAccessed() {
        return lastAccessed;
    }
}