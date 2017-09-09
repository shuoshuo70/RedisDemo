package com.demo.redis;

/**
 * Created by shuoshuo on 2017/9/9.
 */
public class SingletonRedisClient {
    private static RedisClient client = new RedisClient();

    private SingletonRedisClient() {
    }

    public static RedisClient getClient() {
        return client;
    }
}
