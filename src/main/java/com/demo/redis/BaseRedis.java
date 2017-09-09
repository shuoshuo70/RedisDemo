package com.demo.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;

/**
 * Created by shuoshuo on 2017/9/9.
 */
public class BaseRedis {
    private JedisPool pool;
    private StringRedisTemplate template;

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public StringRedisTemplate getTemplate() {
        return template;
    }

    public void setTemplate(StringRedisTemplate template) {
        this.template = template;
    }
}
