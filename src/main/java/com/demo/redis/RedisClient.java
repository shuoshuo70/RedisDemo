package com.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Created by shuoshuo on 2017/9/9.
 */
public class RedisClient extends BaseRedis {
    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);
    private JedisPool pool;
    private StringRedisTemplate template;

    public RedisClient() {
        template = getTemplate();
        pool = getPool();
    }

    public String set(String key, String value) {
        Jedis jedis = pool.getResource();

        try {
            return jedis.set(key, value);
        } catch (Exception e) {
            pool.returnBrokenResource(jedis);
        } finally {
            returnResource(pool, jedis);
        }

        return null;
    }


    public String mset(String ... keys) {
        Jedis jedis = pool.getResource();

        try {
            return jedis.mset(keys);
        } finally {
            pool.returnResource(jedis);
        }
    }

    public String get(String key) {
        Jedis jedis = pool.getResource();

        try {
            return jedis.get(key);
        } finally {
            pool.returnResource(jedis);
        }
    }

    /**
     * 获取jedis实例时，可能会有两种类型的错误，一种是pool里去redis时未取到可用的jedis实例，另一种是jedis在set，
     * get等操作时抛出的异常。 对于未取到jedis实例的，只要判断jedis是否为空，为空就不用返回给pool了
     * 抛出异常一定要用returnBrokenResource返回给pool，不然下次getResource时缓存区还存在数据，
     * 出现问题。
     * @param pool
     * @param jedis
     */
    private void returnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
}
