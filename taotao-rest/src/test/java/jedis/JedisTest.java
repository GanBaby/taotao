package jedis;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import java.util.HashSet;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-8 下午 14:04
 */
@Ignore
public class JedisTest {

   /* @Test
    public void testJedisSingle(){
        //创建一个jedis的对象
        Jedis jedis = new Jedis("10.99.54.152",6379);
        //调用jedis对象的方法，方法名称和jedis的命令一致
        jedis.set("key1","jedis test");
        String str = jedis.get("key1");
        System.out.printf(str);
        //关闭jedis
        jedis.close();
    }

    *//**
     * 使用连接池
     *//*
    @Test
    public void testJedisPool(){
        //创建jedis连接池
        JedisPool pool = new JedisPool("10.99.54.152",6379);
        //从连接池中获得jedis对象
        Jedis jedis = pool.getResource();
        String str = jedis.get("key1");
        System.out.printf(str);
        //关闭jedis对象
        jedis.close();
        //关闭连接池
        pool.close();
    }

    *//**
     * 集群版测试
     *//*
    @Test
    public void testJedisCluster(){

        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.99.54.152",7001));
        nodes.add(new HostAndPort("10.99.54.152",7002));
        nodes.add(new HostAndPort("10.99.54.152",7003));
        nodes.add(new HostAndPort("10.99.54.152",7004));
        nodes.add(new HostAndPort("10.99.54.152",7005));
        nodes.add(new HostAndPort("10.99.54.152",7006));
        
        JedisCluster cluster = new JedisCluster(nodes);
        
        cluster.set("key1","1000");
        String str = cluster.get("key1");
        System.out.printf(str);
        try{
            //关闭集群
            cluster.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    *//**
     * spring管理的单机jedis测试
     *//*
    @Test
    public void testSpringJedisSingle(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String str = jedis.get("key1");
        System.out.printf(str);
        jedis.close();
        pool.close();
    }*/

    /**
     * spring管理的集群jedis测试
     */
/*    @Test
    public void testSpringJedisCluter(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
        String str = jedisCluster.get("key1");
        System.out.printf(str);
        try{
            //关闭集群
            jedisCluster.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/

}
