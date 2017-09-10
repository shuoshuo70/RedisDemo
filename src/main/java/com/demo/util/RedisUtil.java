package com.demo.util;

import com.demo.redis.RedisClient;
import com.demo.redis.SingletonRedisClient;
import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

import java.io.*;

/**
 * Created by shuoshuo on 2017/9/9.
 */
public class RedisUtil {
    public static Object byteToObject(byte[] bytes) {
        Object obj = null;

        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return obj;
    }

    public static byte[] objectToByte(Object obj){
        byte[] bytes = null;

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);

            oo.writeObject(obj);
            bytes = bo.toByteArray();

            bo.close();
            oo.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        return bytes;
    }
}
