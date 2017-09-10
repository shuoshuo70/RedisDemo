package com.demo.redis;

import com.demo.util.RedisUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by shuoshuo on 2017/9/10.
 */
public class RedisTemplateClient {
    @Resource
    private RedisTemplate template;

    /**
     * 向redis添加元素
     * @param key
     * @param value
     */
    public void set(final Serializable key, final Serializable value) {
//        template.opsForHash().put("REDIS", key, value);

        template.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key_ = template.getKeySerializer().serialize(key);
                byte[] value_ = template.getKeySerializer().serialize(value);

                connection.set(key_, value_);
                return true;
            }
        });
    }

    /**
     * 从redis中根据key取数据
     * @param key
     * @return
     */
    public Serializable get(final String key) {
//        return (Serializable) template.opsForHash().get("REDIS", key);

        return (Serializable) template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key_ = template.getKeySerializer().serialize(key);
                byte[] value_ = connection.get(key_);
                return RedisUtil.byteToObject(value_);
            }
        });
    }

    /**
     * 删除一个元素
     */
    public void delete(final Serializable key) {
//        template.opsForHash().delete("REDIS", key);

        template.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key_ = template.getKeySerializer().serialize(key);

                return connection.del(key_);
            }
        });
    }
}
