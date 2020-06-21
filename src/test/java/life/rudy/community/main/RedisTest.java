package life.rudy.community.main;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

    /**
     * Java 通过Jedis操作Redis服务器
     * @param args
     */
    public static void main(String[] args) {

        Jedis jedis = RedisPoolUtil.getJedis();
        System.out.println(jedis.ping());

        jedis.setnx("String", "字符串");
        System.out.println("Redis中的数据：" + jedis.get("String"));
        RedisPoolUtil.close(jedis);
    }


}
