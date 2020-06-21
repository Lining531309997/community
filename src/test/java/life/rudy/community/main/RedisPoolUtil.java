package life.rudy.community.main;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolUtil {
    // Redis服务器IP
    private static final String HOST = "127.0.0.1";
    // Redis服务器暴露的端口
    private static final int PORT = 6379;
    // Redis服务器连接密码
    private static final String AUTH = "redisPassword";
    // Redis服务器最大连接数
    private static final int MAX_ACTIVE = 1024;
    // Redis服务器最大空闲数
    private static final int MAX_IDLE = 8;
    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static final int MAX_WAIT = 10 * 1000;
    // 连接超时的时间
    private static final int MAX_TIMEOUT = 2 * 1000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    // 数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;

    // 单机模式--连接池对象
    private static JedisPool jedisPool;
    // 集群模式
    private static JedisCluster jedisCluster;

    /**
     * 单机模式--初始化连接池
     */
    static {
        // 1.连接池Redis Pool 基本配置信息
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(MAX_ACTIVE);
        poolConfig.setMaxIdle(MAX_IDLE);
        poolConfig.setMaxWaitMillis(MAX_WAIT);
        poolConfig.setTestOnBorrow(TEST_ON_BORROW);
        // 2.初始化连接池对象
        jedisPool = new JedisPool(poolConfig, HOST, PORT);
//        jedisPool = new JedisPool(poolConfig, HOST, PORT, MAX_TIMEOUT, AUTH, DEFAULT_DATABASE);


    }

    /**
     * 从连接池获取Jedis
     * @return
     */
    public static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                System.out.println("redis--服务正在运行: " + jedis.ping());
                return jedis;
            } else {
                System.out.println("redis--服务未运行");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭Jedis连接
     * @param jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
