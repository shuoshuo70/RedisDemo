package com.demo.util;

import com.demo.redis.RedisClient;
import com.demo.redis.SingletonRedisClient;

/**
 * Created by shuoshuo on 2017/9/9.
 */
public class RedisUtil {
    RedisClient client = SingletonRedisClient.getClient();

    public void test() {
        client.set("key", "hello");
        client.get("key");
    }
}
