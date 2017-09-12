package com.demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * Created by shuoshuo on 2017/9/12.
 */
public class PipeLineRedis {
    private static final int COUNT = 1000;
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        withOutPipeLine(COUNT);
        long endTime = System.currentTimeMillis();

        System.out.println("without: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        withPipeLine(COUNT);
        endTime = System.currentTimeMillis();

        System.out.println("with: " + (endTime - startTime));
    }

    private static void withPipeLine(int count) {
        Jedis jedis = null;

        try {
            jedis = new Jedis("127.0.0.1", 6379);
            Pipeline pl = jedis.pipelined();

            for(int i=0; i<count; i++) {
                pl.incr("a");
            }

            pl.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(jedis != null) {
                jedis.disconnect();
            }
        }
    }

    private static void withOutPipeLine(int count) {
        Jedis jr = null;

        try {

            jr = new Jedis("10.10.224.44", 6379);

            for(int i =0; i<count; i++){

                jr.incr("testKey1");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        finally{

            if(jr!=null){

                jr.disconnect();

            }

        }
    }
}
