package com.demo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * Created by shuoshuo on 2017/9/9.
 */
public class BaseRedis {
    @Resource
    private JedisPool pool;
    @Resource
    private RedisTemplate template;

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public RedisTemplate getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate template) {
        this.template = template;
    }
}
