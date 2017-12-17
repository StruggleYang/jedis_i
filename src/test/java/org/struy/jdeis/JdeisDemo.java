package org.struy.jdeis;


import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * test jedis
 */
public class JdeisDemo {


    /**
     * 单实例测试
     */
    @Test
    public void demo1() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name", "Stuy");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }


    /**
     * 连接池测试
     */
    @Test
    public void demo2() {
        // 连接池配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大空闲连接数
        config.setMaxIdle(10);

        // 获得连接池
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);

        // 获得核心对象jedis
        Jedis jedis = null;
        try{
            // 通过连接池获得连接
            jedis = jedisPool.getResource();
            // 操作数据
            jedis.set("age","30");
            String age =  jedis.get("age");
            System.out.println(age);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null!=jedis){
                jedis.close();
            }if (null!=jedisPool){
                jedisPool.close();
            }
        }
    }
}
