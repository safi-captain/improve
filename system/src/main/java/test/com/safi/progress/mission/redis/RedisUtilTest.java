package test.com.safi.progress.mission.redis;

import com.safi.progress.mission.redis.RedisUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import redis.clients.jedis.Jedis;

/**
 * RedisUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 18, 2016</pre>
 */
public class RedisUtilTest {
    Jedis jedis;

    @Before
    public void before() throws Exception {
        System.out.println("测试开始。。。");
    }

    @After
    public void after() throws Exception {
        System.out.println("测试结束。。。");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("开始初始化");
        jedis = RedisUtil.getJedis();
        System.out.println("初始化结束");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("开始回收redis连接");
        RedisUtil.returnResource(jedis);
        System.out.println("回收redis连接结束");
    }

    @Test
    public void testKey() throws Exception {
        jedis.set("name", "safi");
        System.out.println(jedis.get("name"));
        assert "safi".equals(jedis.get("name"));
        jedis.del("name");
        assert jedis.get("name") == null;
    }

    @Test
    public void testString() throws Exception {
        jedis.set("name", "safi");
        jedis.append("name", " xie");
        System.out.println(jedis.get("name"));
        assert "safi xie".equals(jedis.get("name"));
        jedis.del("name");
    }

    @Test
    public void testEX() throws Exception {
        jedis.setex("name", 2, "safi");
        Thread.sleep(2000);
        System.out.println(jedis.get("name"));
        assert !"safi".equals(jedis.get("name"));
    }
} 
